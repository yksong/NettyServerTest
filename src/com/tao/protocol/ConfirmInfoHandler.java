package com.tao.protocol;

import cn.test.uncompress.BufferDataThread;
import com.google.protobuf.InvalidProtocolBufferException;
import com.protocol.InfoConfirm;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by DXS on 2017/4/13.
 */
public class ConfirmInfoHandler extends MainHandler{
    public static InfoConfirm.ComfirmInfo confirmInfo;
    public static MiMessageLite miMessageLite;
    @Override
    public void handlerMessage(PackMessage packMessage) {
        super.handlerMessage(packMessage);
        byte array[] = packMessage.getArray();
        int offset = packMessage.getOffset();
        int len = packMessage.getLen();
        ChannelHandlerContext channelHandlerContext = packMessage.getChannelHandlerContext();
        try {
            confirmInfo = InfoConfirm.ComfirmInfo.getDefaultInstance().getParserForType().parseFrom(packMessage.getArray(), offset, len);
            System.out.println(channelHandlerContext.channel().remoteAddress() + " Say : " + confirmInfo.toString());
            if (confirmInfo.getAndroidFrame() == true){
                BufferDataThread.updateFrame = true;
//              rame.currentFrame++;
//              Frame.frontFrame++;
            }
            // 封装socket传输消息
            miMessageLite = new MiMessageLite();
            miMessageLite.setMessageType((byte)(packMessage.getMessageType()&0xff));
            miMessageLite.setMessageLite(confirmInfo);
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
