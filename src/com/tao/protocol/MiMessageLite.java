package com.tao.protocol;

import com.google.protobuf.MessageLite;

/**
 * Created by DXS on 2017/3/29.
 */
public class MiMessageLite {
    private MessageLite messageLite = null;

    private byte messageType = 0x00;

    public MessageLite getMessageLite() {
        return messageLite;
    }

    public void setMessageLite(MessageLite messageLite) {
        this.messageLite = messageLite;
    }

    public byte getMessageType() {

        return messageType;
    }

    public void setMessageType(byte messageType) {
        this.messageType = messageType;
    }
}
