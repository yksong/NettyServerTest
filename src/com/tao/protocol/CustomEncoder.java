package com.tao.protocol;

import com.google.protobuf.MessageLite;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by DXS on 2017/3/29.
 */
public class CustomEncoder extends MessageToByteEncoder<MiMessageLite> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MiMessageLite miMessageLite, ByteBuf byteBuf) throws Exception {
//        System.out.println("正在封装数据报文");
        MessageLite messageLite = miMessageLite.getMessageLite();
        byte messageType = miMessageLite.getMessageType();
        byte[] body = messageLite.toByteArray();
        byte[] header = encoderHeader(messageType, messageLite, (short)body.length);
        byteBuf.writeBytes(header);
        byteBuf.writeBytes(body);
    }

    /**
     * 判断消息的类型，可以将消息的
     * @param messageLite
     * @param bodyLength
     * @return
     */
    private byte[] encoderHeader(byte messageType, MessageLite messageLite, short bodyLength){
        byte header[] = new byte[4];
        header[0] = (byte) (bodyLength & 0xff);
        header[1] = (byte)((bodyLength >> 8) & 0xff);
        header[2] = 0;
        header[3] = messageType;

        return header;
    }
}
