package com.jumpcutfindo.happyholidays.common.block.entity.christmas;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.Holiday;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasStarBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.ChristmasStarTier;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;
import com.jumpcutfindo.happyholidays.common.container.christmas.star.ChristmasStarContainer;
import com.jumpcutfindo.happyholidays.common.entity.christmas.IChristmasEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.happy.HappySantaEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.ChristmasStarEvent;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasBlockEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEffects;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasParticles;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.utils.StringUtils;
import com.jumpcutfindo.happyholidays.common.utils.message.GameplayMessage;
import com.jumpcutfindo.happyholidays.common.utils.message.MessageType;
import com.jumpcutfindo.happyholidays.common.utils.message.Messenger;
import com.jumpcutfindo.happyholidays.server.data.HolidayAvailabilityData;
import com.jumpcutfindo.happyholidays.server.data.SantaSavedData;
import com.jumpcutfindo.happyholidays.server.data.structs.Availability;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.MinecraftForge;

public class ChristmasStarBlockEntity extends BaseContainerBlockEntity implements ChristmasEntityBlock {
    public static final String BLOCK_ENTITY_ID = "christmas_star";
    public static final int SLOTS = 6;

    public static final int BONUS_SLOT_INDEX = 5;

    public static final int MAX_POINTS = 100;

    public static final int MAX_RADIUS_INDEX = 5;
    public static final int[] PLAYER_EFFECT_RADIUS = { 0, 8, 16, 24, 32, 40 };
    public static final int[] ENTITY_EFFECT_RADIUS = { 0, 8, 16, 24, 32, 40 };
    public static final int[] BLOCK_EFFECT_RADIUS = { 0, 4, 8, 12, 16, 20 };

    private int currentTier;
    private int currentPoints;

    private boolean isSummoningSanta;
    private boolean isGoodSanta;
    private long santaNextSummonTime;
    private int summonSantaProgress;

    private AABB santaAOE;
    private final ServerBossEvent summonEvent = (ServerBossEvent) (new ServerBossEvent(new TranslatableComponent(
            "entity.happyholidays.santa_is_coming"),
            BossEvent.BossBarColor.WHITE, BossEvent.BossBarOverlay.PROGRESS));

    public final ContainerData dataAccess = new ContainerData() {
        @Override
        public int get(int code) {
            switch (code) {
            case 0:
                return ChristmasStarBlockEntity.this.currentTier;
            case 1:
                return ChristmasStarBlockEntity.this.currentPoints;
            default:
                return 0;
            }
        }

        @Override
        public void set(int code, int value) {
            switch (code) {
            case 0:
                ChristmasStarBlockEntity.this.currentTier = value;
                break;
            case 1:
                ChristmasStarBlockEntity.this.currentPoints = value;
                break;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    protected NonNullList<ItemStack> items = NonNullList.withSize(SLOTS, ItemStack.EMPTY);

    public ChristmasStarBlockEntity(BlockEntityType<?> entityType, BlockPos pos, BlockState state) {
        super(entityType, pos, state);
    }

    public ChristmasStarBlockEntity(BlockPos pos, BlockState state) {
        this(ChristmasBlockEntities.CHRISTMAS_STAR_ENTITY_TYPE.get(), pos, state);
    }

    @Override
    public void clearRemoved() {
        // FIXME: This is to be called by onLoad(), but the method is not being called (Forge issue)
        // Update: Forge has actually fixed this, but it's only in the later versions, will be fixed in 1.18
        ChristmasStarHelper.cacheStarLocation(this.getBlockPos());

        super.clearRemoved();
    }

    @Override
    protected Component getDefaultName() {
        return new TranslatableComponent("container." + HappyHolidaysMod.MOD_ID + "." + BLOCK_ENTITY_ID);
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory playerInv) {
        return new ChristmasStarContainer(id, playerInv, this);
    }

    @Override
    public int getContainerSize() {
        return SLOTS;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public ItemStack getItem(int index) {
        return this.items.get(index);
    }

    @Override
    public ItemStack removeItem(int start, int end) {
        return ContainerHelper.removeItem(this.items, start, end);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ContainerHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.items.set(index, itemStack);
    }

    @Override
    public boolean stillValid(Player playerEntity) {
        if (this.level.getBlockEntity(this.worldPosition) != this) {
            return false;
        } else {
            return playerEntity.distanceToSqr((double)this.worldPosition.getX() + 0.5D, (double)this.worldPosition.getY() + 0.5D, (double)this.worldPosition.getZ() + 0.5D) <= 64.0D;
        }
    }

    @Override
    public void clearContent() {
        this.items.clear();
    }

    public void applyPlayerEffects() {
        if (!this.level.isClientSide() && this.currentTier > 0) {
            AABB axisAlignedBB = new AABB(this.worldPosition).inflate(PLAYER_EFFECT_RADIUS[this.currentTier]);
            List<Player> playerList = this.level.getEntitiesOfClass(Player.class, axisAlignedBB);

            for (Player playerEntity : playerList) {
                if (playerEntity.getEffect(ChristmasEffects.SPIRIT_OF_CHRISTMAS_EFFECT.get()) == null) {
                    // Fresh application of effect, play sound
                    ((ServerLevel) this.level).playSound(null, playerEntity.blockPosition(),
                            ChristmasSounds.CHRISTMAS_STAR_EFFECT_APPLY.get(), SoundSource.NEUTRAL, 1.0f, 1.0f);
                }

                playerEntity.addEffect(new MobEffectInstance(ChristmasEffects.SPIRIT_OF_CHRISTMAS_EFFECT.get(),
                        200, this.currentTier - 1, true, true));
            }
        }
    }

    public void applyEntityEffects() {
        if (!this.level.isClientSide() && this.currentTier > 0) {
            AABB axisAlignedBB = new AABB(this.worldPosition).inflate(ENTITY_EFFECT_RADIUS[this.currentTier]);

            List<LivingEntity> christmasEntities = this.level.getEntitiesOfClass(LivingEntity.class,
                    axisAlignedBB, entity -> entity instanceof IChristmasEntity);

            for (LivingEntity entity : christmasEntities) {
                entity.addEffect(new MobEffectInstance(ChristmasEffects.DEBUFF_OF_CHRISTMAS_EFFECT.get(), 200,
                        this.currentTier - 1, true, true));
            }

        }
    }

    public void summonSanta() {
        if (this.level != null && !this.level.isClientSide()) {
            ServerLevel serverWorld = (ServerLevel) this.level;

            if (!Availability.isAvailable(serverWorld, Holiday.CHRISTMAS, HolidayAvailabilityData.CHRISTMAS_SPAWN_SANTA_STAR)) {
                GameplayMessage message = new GameplayMessage(MessageType.ERROR, "chat.happyholidays.christmas_star.santa_unavailable");
                Messenger.sendChatMessage(message, serverWorld.getPlayers(playerEntity -> this.santaAOE.contains(playerEntity.position())));
                return;
            }

            SantaSavedData santaData = SantaSavedData.retrieve(serverWorld);

            this.santaAOE = new AABB(this.getBlockPos()).inflate(HappySantaEntity.NAUGHTY_NICE_CONSIDERATION_RADIUS);

            boolean isTierOK = this.getCurrentTier() >= 5;
            boolean isValidTime = santaData.canSummon(serverWorld.getGameTime());

            if (!isTierOK) return;
            if (!isValidTime) {
                long timeRemaining = santaData.getNextSummonTime() - serverWorld.getGameTime();

                GameplayMessage message = new GameplayMessage(MessageType.ERROR, "chat.happyholidays.christmas_star.santa_not_ready", StringUtils.convertTicksToString(timeRemaining));
                Messenger.sendChatMessage(message, serverWorld.getPlayers(playerEntity -> this.santaAOE.contains(playerEntity.position())));

                return;
            }

            // Set summon time
            santaData.summoned(this.level.getGameTime());
            santaData.setDirty();

            // Reset naughty nice meter of players in radius
            int totalValue = 0, totalPlayers = 0;
            for (ServerPlayer serverPlayerEntity : serverWorld.getPlayers(playerEntity -> this.santaAOE.contains(playerEntity.position()))) {
                int value = NaughtyNiceMeter.getMeterValue(serverPlayerEntity);
                totalValue += value;

                totalPlayers++;

                NaughtyNiceMeter.resetMeter(serverPlayerEntity);

                // Show boss bar to players around
                this.summonEvent.setProgress((float) this.summonSantaProgress / (float) (BaseSantaEntity.SUMMON_SANTA_DURATION));
                this.summonEvent.addPlayer(serverPlayerEntity);
            }

            // Determine type of santa
            if (totalPlayers == 0) return;

            int averageValue = totalValue / totalPlayers;
            if (averageValue >= NaughtyNiceMeter.VALUE_NICE_MIN) {
                this.isGoodSanta = true;
            } else if (averageValue <= NaughtyNiceMeter.VALUE_NAUGHTY_MIN) {
                this.isGoodSanta = false;
            } else {
                this.isGoodSanta = Math.random() < 0.5;
            }

            // Play relevant sound effects
            if (this.isGoodSanta) {
                this.level.playSound(null, this.getBlockPos(), ChristmasSounds.SANTA_SPAWNING_GOOD.get(),
                        SoundSource.NEUTRAL, 1.0f, 1.0f);
            } else {
                this.level.playSound(null, this.getBlockPos(), ChristmasSounds.SANTA_SPAWNING_BAD.get(),
                        SoundSource.NEUTRAL, 1.0f, 1.0f);
            }

            // Start summoning santa
            this.isSummoningSanta = true;
            this.summonSantaProgress = BaseSantaEntity.SUMMON_SANTA_DURATION;
        }
    }

    public void finishSummonSanta() {
        // Summon the appropriate santa
        BaseSantaEntity santaEntity = null;
        GameplayMessage summonMessage = null;
        if (this.isGoodSanta) {
            santaEntity = ChristmasEntities.HAPPY_SANTA.get().create(this.level);
            summonMessage = new GameplayMessage(MessageType.NICE, "entity.happyholidays.santa_arrival_happy");
        } else {
            santaEntity = ChristmasEntities.ANGRY_SANTA.get().create(this.level);
            summonMessage = new GameplayMessage(MessageType.NAUGHTY, "entity.happyholidays.santa_arrival_angry");
        }

        List<Player> playerList = this.level.getEntitiesOfClass(Player.class, this.santaAOE);
        Messenger.sendChatMessage(summonMessage, playerList);

        playerList.forEach(playerEntity -> {
                ChristmasStarEvent summonEvent = new ChristmasStarEvent.SummonSanta(this, playerEntity);
                MinecraftForge.EVENT_BUS.post(summonEvent);
            }
        );

        santaEntity.moveTo(this.getBlockPos().getX() + 0.5D, this.getBlockPos().getY() + 1.0D,
                this.getBlockPos().getZ());

        this.level.addFreshEntity(santaEntity);
        this.isSummoningSanta = false;

        // Play santa spawn sound
        this.level.playSound(null, this.getBlockPos(), ChristmasSounds.SANTA_SPAWN.get(), SoundSource.NEUTRAL,
                1.0f, 1.0f);

        this.summonEvent.removeAllPlayers();

    }

    public void setSantaNextSummonTime(long santaNextSummonTime) {
        this.santaNextSummonTime = santaNextSummonTime;
    }

    public long getSantaNextSummonTime() {
        return this.santaNextSummonTime;
    }

    public int getCurrentTier() {
        return currentTier;
    }

    public boolean isBonusActive() {
        return !this.items.get(BONUS_SLOT_INDEX).isEmpty();
    }

    public boolean isPosAffected(BlockPos pos) {
        return new AABB(this.getBlockPos()).inflate(BLOCK_EFFECT_RADIUS[this.currentTier]).contains(pos.getX(), pos.getY(), pos.getZ());
    }

    /**
     * Updates points and tiers. Items are split into a few tiers:
     * 0. Trash tier - any basic item gives zero points.
     * 1. Basic tier - any basic Christmas items / ornaments / blocks will give 5 points each
     * 2. Pro tier - any rare Christmas ornaments
     */
    private void updatePoints() {
        // Checks basic item points
        int newPoints = 0;
        List<ItemStack> itemStackList = Lists.newArrayList();
        for (int i = 0; i < 5; i++) {
            ItemStack itemStack = this.items.get(i);

            if (itemStackList.stream().anyMatch(itemStack1 -> ItemStack.isSame(itemStack, itemStack1))) {
                // Checks if the new item added is unique (avoid double counting)
                continue;
            } else {
                itemStackList.add(itemStack);
            }

            if (ChristmasItems.isBasicOrnamentItem(itemStack)) {
                newPoints += 5;
            } else if (ChristmasItems.isRareOrnamentItem(itemStack)) {
                newPoints += 10;
            } else if (ChristmasItems.isLegendaryOrnamentItem(itemStack)) {
                newPoints += 20;
            }
        }

        this.currentPoints = Math.min(newPoints, MAX_POINTS);

        // Update tier
        if (this.currentPoints < 20) {
            changeTier(0);
        } else if (this.currentPoints < 40) {
            changeTier(1);
        } else if (this.currentPoints < 60) {
            changeTier(2);
        } else if (this.currentPoints < 80) {
            changeTier(3);
        } else if (this.currentPoints < 100) {
            changeTier(4);
        } else {
            changeTier(5);
        }
    }

    private void changeTier(int newTier) {
        int oldTier = this.currentTier;
        this.currentTier = newTier;

        if (oldTier != newTier && this.level != null) {
            ChristmasStarTier starTier = newTier == 0 ? ChristmasStarTier.TIER_0
                    : newTier == 1 ? ChristmasStarTier.TIER_1
                    : newTier == 2 ? ChristmasStarTier.TIER_2
                    : newTier == 3 ? ChristmasStarTier.TIER_3
                    : newTier == 4 ? ChristmasStarTier.TIER_4
                    : ChristmasStarTier.TIER_5;

            this.level.setBlock(this.worldPosition, this.getBlockState().setValue(ChristmasStarBlock.STAR_TIER,
                    starTier), 0);
            this.level.playSound(null, this.worldPosition.getX(), this.worldPosition.getY(),
                    this.worldPosition.getZ(), SoundEvents.NOTE_BLOCK_BELL, SoundSource.BLOCKS, 1.0F,
                    1.0F + newTier * 0.1F);

            if (!this.level.isClientSide) this.level.sendBlockUpdated(this.getBlockPos(), this.getBlockState(), this.getBlockState(), 2);
        }

        if (oldTier < newTier && !this.level.isClientSide()) {
            BlockPos blockPos = this.getBlockPos();
            Player playerEntity = level.getNearestPlayer(blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                    10.0D, false);

            if (playerEntity != null) {
                ChristmasStarEvent event = new ChristmasStarEvent.IncreaseTier(this, playerEntity);
                MinecraftForge.EVENT_BUS.post(event);
            }

            this.setChanged();
        }

        if (oldTier < newTier && this.level.isClientSide()) {
            this.level.sendBlockUpdated(this.worldPosition, this.getBlockState(), this.getBlockState(), 2);
        }
    }

    @Nullable
    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this, ChristmasStarBlockEntity::createUpdateTag);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        CompoundTag tag = pkt.getTag();

        if (tag.contains("StarTier")) changeTier(tag.getInt("StarTier"));
    }

    public static CompoundTag createUpdateTag(BlockEntity blockEntity) {
        CompoundTag tag = new CompoundTag();

        if (blockEntity instanceof ChristmasStarBlockEntity christmasStarBlockEntity) {
            tag.putInt("StarTier", christmasStarBlockEntity.currentTier);
        }

        return tag;
    }

    public static void serverTick(Level level, BlockPos pos, BlockState blockState, ChristmasStarBlockEntity blockEntity) {
        if (level != null && !level.isClientSide()) {
            if (level.getGameTime() % 80L == 0L && Availability.isAvailable((ServerLevel) level, Holiday.CHRISTMAS, HolidayAvailabilityData.CHRISTMAS_STAR_GIVE_BUFF)) {
                blockEntity.applyPlayerEffects();
                blockEntity.applyEntityEffects();
            }

            if (blockEntity.isSummoningSanta) {
                double d0 = (Math.random() * 0.1D) + 0.25D;
                double d1 = (Math.random() * 0.1D) + 0.25D;
                double d2 = (Math.random() * 0.1D) + 0.25D;

                double d = Math.random();
                SimpleParticleType particleType = d < 0.5 ? ChristmasParticles.CHRISTMAS_SANTA_GREEN_SPAWN_PARTICLE.get() :
                        ChristmasParticles.CHRISTMAS_SANTA_RED_SPAWN_PARTICLE.get();

                ((ServerLevel) level).sendParticles(particleType,
                        blockEntity.getBlockPos().getX() + 0.5D,
                        blockEntity.getBlockPos().getY() + d1 + 1.5D,
                        blockEntity.getBlockPos().getZ() + 0.5D,
                        2, d0, d1, d2, 0.0D);

                if (level.getGameTime() % 60L == 0) {
                    List<Player> playerList = level.getEntitiesOfClass(Player.class, blockEntity.santaAOE);

                    // Remove players outside the AOE
                    for (ServerPlayer serverPlayerEntity : blockEntity.summonEvent.getPlayers()) {
                        if (!playerList.contains(serverPlayerEntity)) blockEntity.summonEvent.removePlayer(serverPlayerEntity);
                    }

                    // Add players inside the AOE
                    for (Player playerEntity : playerList) {
                        ServerPlayer serverPlayerEntity = (ServerPlayer) playerEntity;
                        if (!blockEntity.summonEvent.getPlayers().contains(serverPlayerEntity)) blockEntity.summonEvent.addPlayer((ServerPlayer) playerEntity);
                    }
                }

                blockEntity.summonEvent.setProgress((float) (BaseSantaEntity.SUMMON_SANTA_DURATION - blockEntity.summonSantaProgress) / (float) (BaseSantaEntity.SUMMON_SANTA_DURATION));
            }

            if (blockEntity.isSummoningSanta && --blockEntity.summonSantaProgress <= 0) {
                blockEntity.finishSummonSanta();
            }

            if (blockEntity.isBonusActive() && level.getGameTime() % 80L == 0) {

                ((ServerLevel) level).sendParticles(ChristmasParticles.CHRISTMAS_STAR_PARTICLE.get(),
                        blockEntity.getBlockPos().getX() + 0.5,
                        blockEntity.getBlockPos().getY() + 0.5,
                        blockEntity.getBlockPos().getZ() + 0.5,
                        3, 0.5, 0.0, 0.5, 0.0D);
            }
        }

        blockEntity.updatePoints();
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);

        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ContainerHelper.loadAllItems(nbt, this.items);

        this.currentTier = nbt.getInt("CurrentTier");
        this.currentPoints = nbt.getInt("CurrentPoints");
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);

        ContainerHelper.saveAllItems(tag, this.items);

        tag.putInt("CurrentTier", this.currentTier);
        tag.putInt("CurrentPoints", this.currentPoints);
    }
}
