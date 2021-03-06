package com.jumpcutfindo.happyholidays.common.utils.message;

import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;

public class GameplayMessage {
    private final MessageType messageType;
    private final TranslatableComponent textComponent;

    public GameplayMessage(MessageType messageType, String src, Object... args) {
        this.messageType = messageType;

        this.textComponent = new TranslatableComponent(src, args);
    }

    public String getRawMessage() {
        return textComponent.getContents();
    }

    public MutableComponent getStyledMessage() {
        return messageType.applyFormatting(textComponent);
    }
}
