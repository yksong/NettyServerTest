package com.tao.protocol;

import com.google.protobuf.MessageLite;
import com.protocol.FeedbackInfoFromClienttoServer;
import com.protocol.InitialDataFromClienttoServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by DXS on 2017/3/29.
 */
public class CustomDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        while (byteBuf.readableBytes() > 4){
            byteBuf.markReaderIndex();
            /* 获取包体长度 */
            byte low = byteBuf.readByte();
            byte high = byteBuf.readByte();
            short slow = (short)(low & 0xff);
            short shigh = (short)(high & 0xff);
            short bodyLen = (short)((shigh << 8)|slow);
            /* 读取保留位 */
            byteBuf.readByte();
            /* 解析包体 */
            byte bodyType = byteBuf.readByte();
            /* 根据当前buffer中可读的数据长度与包头的长度是否相等判断是否是一个完整的包 */
            if(byteBuf.readableBytes() < bodyLen){
                byteBuf.resetReaderIndex();
                return;
            }
            ByteBuf bodyBuffer = byteBuf.readBytes(bodyLen);
            byte bodyArray[];
            int offset;
            int readableLen = bodyBuffer.readableBytes();
            if(bodyBuffer.hasArray()){
                bodyArray = bodyBuffer.array();
                offset = bodyBuffer.arrayOffset() + bodyBuffer.readerIndex();
            }else{
                bodyArray = new byte[readableLen];
                bodyBuffer.getBytes(bodyBuffer.readerIndex(), bodyArray, 0, readableLen);
                offset = 0;
            }
//            MessageLite messageLite = decodeBody(bodyType, bodyArray, offset, readableLen);
            // 自定义应用消息封装，并添加到全局消息队列里面
//            System.out.println("此消息来自netty请求里的CustomDecoder" +" ");
            PackMessage packMessage = new PackMessage();
            packMessage.setMessageType((int)bodyType);
            packMessage.setArray(bodyArray);
            packMessage.setOffset(offset);
            packMessage.setLen(readableLen);
            packMessage.setChannelHandlerContext(channelHandlerContext);
            Global.messageQueue.add(packMessage);
        }
    }

    /**
     * 解码接收到的消息
     * @param dataType 消息类型
     * @param array 包体字节流
     * @param offset 偏移量
     * @param len 长度
     * @return
     * @throws Exception
     */
    private MessageLite decodeBody(byte dataType , byte[] array, int offset, int len) throws Exception{
        if(dataType == 0x00){
            return InitialDataFromClienttoServer.InitialData.getDefaultInstance().getParserForType().parseFrom(array, offset, len);
        }else if(dataType == 0x01){
            return FeedbackInfoFromClienttoServer.FeedbackInfo.getDefaultInstance().getParserForType().parseFrom(array, offset, len);
        }
        return null;
    }
}
