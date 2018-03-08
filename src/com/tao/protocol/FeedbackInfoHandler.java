package com.tao.protocol;

import com.google.protobuf.InvalidProtocolBufferException;
import com.protocol.FeedbackInfoFromClienttoServer;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by DXS on 2017/3/29.
 */
public class FeedbackInfoHandler extends MainHandler{
    public static FeedbackInfoFromClienttoServer.FeedbackInfo feedbackInfo;
    public static MiMessageLite miMessageLite;
    @Override
    public void handlerMessage(PackMessage packMessage) {
        super.handlerMessage(packMessage);
        byte array[] = packMessage.getArray();
        int offset = packMessage.getOffset();
        int len = packMessage.getLen();
        ChannelHandlerContext channelHandlerContext = packMessage.getChannelHandlerContext();
        try {
            feedbackInfo = FeedbackInfoFromClienttoServer.FeedbackInfo.getDefaultInstance().getParserForType().parseFrom(packMessage.getArray(), offset, len);

            // 封装socket传输消息
            miMessageLite = new MiMessageLite();
            miMessageLite.setMessageType((byte)(packMessage.getMessageType()&0xff));
            miMessageLite.setMessageLite(feedbackInfo);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
