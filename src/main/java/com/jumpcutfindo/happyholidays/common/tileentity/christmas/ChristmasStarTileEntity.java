package com.jumpcutfindo.happyholidays.common.tileentity.christmas;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.jumpcutfindo.happyholidays.HappyHolidaysMod;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.ChristmasStarBlock;
import com.jumpcutfindo.happyholidays.common.block.christmas.misc.ChristmasStarTier;
import com.jumpcutfindo.happyholidays.common.capabilities.christmas.NaughtyNiceMeter;
import com.jumpcutfindo.happyholidays.common.container.christmas.star.ChristmasStarContainer;
import com.jumpcutfindo.happyholidays.common.entity.christmas.ChristmasEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.BaseSantaEntity;
import com.jumpcutfindo.happyholidays.common.entity.christmas.santa.happy.HappySantaEntity;
import com.jumpcutfindo.happyholidays.common.events.christmas.ChristmasStarEvent;
import com.jumpcutfindo.happyholidays.common.item.christmas.ChristmasItem;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEffects;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasEntities;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasItems;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasParticles;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasSounds;
import com.jumpcutfindo.happyholidays.common.registry.christmas.ChristmasTileEntities;
import com.jumpcutfindo.happyholidays.common.utils.HappyHolidaysUtils;
import com.jumpcutfindo.happyholidays.server.data.SantaSummonSavedData;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.IIntArray;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.MinecraftForge;

public class ChristmasStarTileEntity extends LockableTileEntity implements IChristmasTileEntity, ITickableTileEntity {
    public static final String TILE_ENTITY_ID = "christmas_star";
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
    private int summonSantaProgress;

    private AxisAlignedBB areaOfEffect;
    private final ServerBossInfo summonEvent = (ServerBossInfo) (new ServerBossInfo(new TranslationTextComponent(
            "entity.happyholidays.santa_is_coming"),
            BossInfo.Color.WHITE, BossInfo.Overlay.PROGRESS));

    public final IIntArray dataAccess = new IIntArray() {
        @Override
        public int get(int code) {
            switch (code) {
            case 0:
                return ChristmasStarTileEntity.this.currentTier;
            case 1:
                return ChristmasStarTileEntity.this.currentPoints;
            default:
                return 0;
            }
        }

        @Override
        public void set(int code, int value) {
            switch (code) {
            case 0:
                ChristmasStarTileEntity.this.currentTier = value;
                break;
            case 1:
                ChristmasStarTileEntity.this.currentPoints = value;
                break;
            }
        }

        @Override
        public int getCount() {
            return 2;
        }
    };

    protected NonNullList<ItemStack> items = NonNullList.withSize(SLOTS, ItemStack.EMPTY);

    public ChristmasStarTileEntity(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public ChristmasStarTileEntity() {
        this(ChristmasTileEntities.CHRISTMAS_STAR_ENTITY_TYPE.get());
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("container." + HappyHolidaysMod.MOD_ID + "." + TILE_ENTITY_ID);
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInv) {
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
        return ItemStackHelper.removeItem(this.items, start, end);
    }

    @Override
    public ItemStack removeItemNoUpdate(int index) {
        return ItemStackHelper.takeItem(this.items, index);
    }

    @Override
    public void setItem(int index, ItemStack itemStack) {
        this.items.set(index, itemStack);
    }

    @Override
    public boolean stillValid(PlayerEntity playerEntity) {
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
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(this.worldPosition).inflate(PLAYER_EFFECT_RADIUS[this.currentTier]);
            List<PlayerEntity> playerList = this.level.getEntitiesOfClass(PlayerEntity.class, axisAlignedBB);

            for (PlayerEntity playerEntity : playerList) {
                if (playerEntity.getEffect(ChristmasEffects.SPIRIT_OF_CHRISTMAS_EFFECT.get()) == null) {
                    // Fresh application of effect, play sound
                    ((ServerWorld) this.level).playSound(null, playerEntity.blockPosition(),
                            ChristmasSounds.CHRISTMAS_STAR_EFFECT_APPLY.get(), SoundCategory.NEUTRAL, 1.0f, 1.0f);
                }

                playerEntity.addEffect(new EffectInstance(ChristmasEffects.SPIRIT_OF_CHRISTMAS_EFFECT.get(),
                        200, this.currentTier - 1, true, true));
            }
        }
    }

    public void applyEntityEffects() {
        if (!this.level.isClientSide() && this.currentTier > 0) {
            AxisAlignedBB axisAlignedBB = new AxisAlignedBB(this.worldPosition).inflate(ENTITY_EFFECT_RADIUS[this.currentTier]);

            List<ChristmasEntity> christmasEntities = this.level.getEntitiesOfClass(ChristmasEntity.class, axisAlignedBB);

            for (ChristmasEntity entity : christmasEntities) {
                entity.addEffect(new EffectInstance(ChristmasEffects.DEBUFF_OF_CHRISTMAS_EFFECT.get(), 200,
                        this.currentTier - 1, true, true));
            }

        }
    }

    public void summonSanta() {
        if (this.level != null && !this.level.isClientSide()) {
            ServerWorld serverWorld = (ServerWorld) this.level;
            SantaSummonSavedData santaData = serverWorld.getDataStorage().computeIfAbsent(SantaSummonSavedData::new,
                    SantaSummonSavedData.DATA_NAME);

            this.areaOfEffect = new AxisAlignedBB(this.getBlockPos()).inflate(HappySantaEntity.NAUGHTY_NICE_CONSIDERATION_RADIUS);

            boolean isTierOK = this.getCurrentTier() >= 5;
            boolean isValidTime = santaData.canSummon(serverWorld.getGameTime());

            if (!isTierOK) return;
            if (!isValidTime) {
                long timeRemaining = santaData.getNextSummonTime() - serverWorld.getGameTime();

                for (ServerPlayerEntity serverPlayerEntity : serverWorld.getPlayers(playerEntity -> this.areaOfEffect.contains(playerEntity.position()))) {
                    serverPlayerEntity.sendMessage(new TranslationTextComponent("chat.happyholidays.christmas_star"
                            + ".santa_not_ready", HappyHolidaysUtils.convertTicksToString(timeRemaining)).withStyle(TextFormatting.RED), UUID.randomUUID());
                }

                return;
            }

            // Set summon time
            santaData.setLastSummonTime(this.level.getGameTime());
            santaData.setDirty();

            // Reset naughty nice meter of players in radius
            int totalValue = 0, totalPlayers = 0;
            for (ServerPlayerEntity serverPlayerEntity : serverWorld.getPlayers(playerEntity -> this.areaOfEffect.contains(playerEntity.position()))) {
                int value = NaughtyNiceMeter.getMeterValue(serverPlayerEntity);
                totalValue += value;

                totalPlayers++;

                NaughtyNiceMeter.resetMeter(serverPlayerEntity);

                // Show boss bar to players around
                this.summonEvent.setPercent((float) this.summonSantaProgress / (float) (BaseSantaEntity.SUMMON_SANTA_DURATION));
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
                        SoundCategory.NEUTRAL, 1.0f, 1.0f);
            } else {
                this.level.playSound(null, this.getBlockPos(), ChristmasSounds.SANTA_SPAWNING_BAD.get(),
                        SoundCategory.NEUTRAL, 1.0f, 1.0f);
            }

            // Start summoning santa
            this.isSummoningSanta = true;
            this.summonSantaProgress = BaseSantaEntity.SUMMON_SANTA_DURATION;
        }
    }

    public void finishSummonSanta() {
        // Summon the appropriate santa
        BaseSantaEntity santaEntity = null;
        ITextComponent santaText = null;
        if (this.isGoodSanta) {
            santaEntity = ChristmasEntities.HAPPY_SANTA.get().create(this.level);
            santaText =
                    new TranslationTextComponent("entity.happyholidays.santa_arrival_happy").withStyle(TextFormatting.AQUA);
        } else {
            santaEntity = ChristmasEntities.ANGRY_SANTA.get().create(this.level);
            santaText =
                    new TranslationTextComponent("entity.happyholidays.santa_arrival_angry").withStyle(TextFormatting.RED);
        }

        List<PlayerEntity> playerList = this.level.getEntitiesOfClass(PlayerEntity.class, this.areaOfEffect);

        ITextComponent finalSantaText = santaText;
        playerList.forEach(playerEntity -> {
                playerEntity.sendMessage(finalSantaText, UUID.randomUUID());

                ChristmasStarEvent summonEvent = new ChristmasStarEvent.SummonSanta(this, playerEntity);
                MinecraftForge.EVENT_BUS.post(summonEvent);
            }
        );

        santaEntity.moveTo(this.getBlockPos().getX() + 0.5D, this.getBlockPos().getY() + 1.0D,
                this.getBlockPos().getZ());

        this.level.addFreshEntity(santaEntity);
        this.isSummoningSanta = false;

        // Play santa spawn sound
        this.level.playSound(null, this.getBlockPos(), ChristmasSounds.SANTA_SPAWN.get(), SoundCategory.NEUTRAL,
                1.0f, 1.0f);

        this.summonEvent.removeAllPlayers();

    }

    public int getCurrentTier() {
        return currentTier;
    }

    public boolean isBonusActive() {
        return !this.items.get(BONUS_SLOT_INDEX).isEmpty();
    }

    public boolean isPosAffected(BlockPos pos) {
        return new AxisAlignedBB(this.getBlockPos()).inflate(BLOCK_EFFECT_RADIUS[this.currentTier]).contains(pos.getX(), pos.getY(), pos.getZ());
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
                    this.worldPosition.getZ(), SoundEvents.NOTE_BLOCK_BELL, SoundCategory.BLOCKS, 1.0F,
                    1.0F + newTier * 0.1F);
        }

        if (oldTier < newTier && !this.level.isClientSide()) {
            BlockPos blockPos = this.getBlockPos();
            PlayerEntity playerEntity = level.getNearestPlayer(blockPos.getX(), blockPos.getY(), blockPos.getZ(),
                    10.0D, false);

            if (playerEntity != null) {
                ChristmasStarEvent event = new ChristmasStarEvent.IncreaseTier(this, playerEntity);
                MinecraftForge.EVENT_BUS.post(event);
            }
        }
    }

    @Override
    public void tick() {
        if (this.level != null && !this.level.isClientSide()) {

            if (this.level.getGameTime() % 80L == 0L) {
                this.applyPlayerEffects();
                this.applyEntityEffects();
            }

            if (isSummoningSanta) {
                double d0 = (Math.random() * 0.1D) + 0.25D;
                double d1 = (Math.random() * 0.1D) + 0.25D;
                double d2 = (Math.random() * 0.1D) + 0.25D;

                double d = Math.random();
                BasicParticleType particleType = d < 0.5 ? ChristmasParticles.CHRISTMAS_SANTA_GREEN_SPAWN_PARTICLE.get() :
                        ChristmasParticles.CHRISTMAS_SANTA_RED_SPAWN_PARTICLE.get();

                ((ServerWorld) this.level).sendParticles(particleType,
                        this.getBlockPos().getX() + 0.5D,
                        this.getBlockPos().getY() + d1 + 1.5D,
                        this.getBlockPos().getZ() + 0.5D,
                        2, d0, d1, d2, 0.0D);

                if (this.level.getGameTime() % 60L == 0) {
                    List<PlayerEntity> playerList = this.level.getEntitiesOfClass(PlayerEntity.class, this.areaOfEffect);

                    // Remove players outside the AOE
                    for (ServerPlayerEntity serverPlayerEntity : this.summonEvent.getPlayers()) {
                        if (!playerList.contains(serverPlayerEntity)) this.summonEvent.removePlayer(serverPlayerEntity);
                    }

                    // Add players inside the AOE
                    for (PlayerEntity playerEntity : playerList) {
                        ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity) playerEntity;
                        if (!this.summonEvent.getPlayers().contains(serverPlayerEntity)) this.summonEvent.addPlayer((ServerPlayerEntity) playerEntity);
                    }
                }

                this.summonEvent.setPercent((float) (BaseSantaEntity.SUMMON_SANTA_DURATION - this.summonSantaProgress) / (float) (BaseSantaEntity.SUMMON_SANTA_DURATION));
            }

            if (isSummoningSanta && --this.summonSantaProgress <= 0) {
                this.finishSummonSanta();
            }
        }

        this.updatePoints();
    }

    @Override
    public void load(BlockState blockState, CompoundNBT nbt) {
        super.load(blockState, nbt);

        this.items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
        ItemStackHelper.loadAllItems(nbt, this.items);

        this.currentTier = nbt.getInt("CurrentTier");
        this.currentPoints = nbt.getInt("CurrentPoints");
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt) {
        super.save(nbt);

        ItemStackHelper.saveAllItems(nbt, this.items);

        nbt.putInt("CurrentTier", this.currentTier);
        nbt.putInt("CurrentPoints", this.currentPoints);

        return nbt;
    }

    public static ChristmasStarTileEntity getStarInfluencingBlock(World world, BlockPos blockPos) {
        // Find all blocks that could possibly affect the block at blockPos
        int maxRadius = BLOCK_EFFECT_RADIUS[MAX_RADIUS_INDEX];
        AxisAlignedBB blockBoundingBox = new AxisAlignedBB(blockPos).inflate(maxRadius);
        List<TileEntity> tileEntityList = world.blockEntityList
                .stream().filter(tileEntity -> tileEntity instanceof ChristmasStarTileEntity
                        && blockBoundingBox.contains(tileEntity.getBlockPos().getX(),
                        tileEntity.getBlockPos().getY(),
                        tileEntity.getBlockPos().getZ()))
                .collect(Collectors.toList());

        // Iterate through the list and figure out the highest level
        ChristmasStarTileEntity influencingTileEntity = null;
        for (TileEntity tileEntity : tileEntityList) {
            if (tileEntity instanceof ChristmasStarTileEntity) {
                ChristmasStarTileEntity christmasStarTileEntity = (ChristmasStarTileEntity) tileEntity;
                int effectRadius = BLOCK_EFFECT_RADIUS[christmasStarTileEntity.getCurrentTier()];
                AxisAlignedBB effectBoundingBox = new AxisAlignedBB(tileEntity.getBlockPos()).inflate(effectRadius);

                // Check whether the tile entity being considered can influence the block, and whether it is at a higher
                // tier compared to the current tile entity
                if (effectBoundingBox.contains(blockPos.getX(), blockPos.getY(), blockPos.getZ())) {
                    if (influencingTileEntity == null || christmasStarTileEntity.getCurrentTier() > influencingTileEntity.getCurrentTier()) {
                        influencingTileEntity = christmasStarTileEntity;
                    }
                }
            }
        }

        return influencingTileEntity;
    }

    public static ChristmasStarTileEntity getStarInfluencingEntity(World world, Vector3d vector3d) {
        // Find all blocks that could possibly affect the block at blockPos
        int maxRadius = ENTITY_EFFECT_RADIUS[MAX_RADIUS_INDEX];
        AxisAlignedBB blockBoundingBox = new AxisAlignedBB(new BlockPos(vector3d)).inflate(maxRadius);
        List<TileEntity> tileEntityList = world.blockEntityList
                .stream().filter(tileEntity -> tileEntity instanceof ChristmasStarTileEntity
                        && blockBoundingBox.contains(tileEntity.getBlockPos().getX(),
                        tileEntity.getBlockPos().getY(),
                        tileEntity.getBlockPos().getZ()))
                .collect(Collectors.toList());

        // Iterate through the list and figure out the highest level
        ChristmasStarTileEntity influencingTileEntity = null;
        for (TileEntity tileEntity : tileEntityList) {
            if (tileEntity instanceof ChristmasStarTileEntity) {
                ChristmasStarTileEntity christmasStarTileEntity = (ChristmasStarTileEntity) tileEntity;
                int effectRadius = ENTITY_EFFECT_RADIUS[christmasStarTileEntity.getCurrentTier()];
                AxisAlignedBB effectBoundingBox = new AxisAlignedBB(tileEntity.getBlockPos()).inflate(effectRadius);

                // Check whether the tile entity being considered can influence the block, and whether it is at a higher
                // tier compared to the current tile entity
                if (effectBoundingBox.contains(vector3d)) {
                    if (influencingTileEntity == null || christmasStarTileEntity.getCurrentTier() > influencingTileEntity.getCurrentTier()) {
                        influencingTileEntity = christmasStarTileEntity;
                    }
                }
            }
        }

        return influencingTileEntity;
    }
}
