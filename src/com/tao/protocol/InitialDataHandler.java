package com.tao.protocol;

import cn.test.uncompress.UnCompressTask;
import com.google.protobuf.InvalidProtocolBufferException;
import com.protocol.InitialDataFromClienttoServer;
import com.tao.netty_server_test.ReceiveDataFromAS;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by DXS on 2017/3/29.
 */
public class InitialDataHandler extends MainHandler{
    public static BlockingQueue<Future> taskQueue = new LinkedBlockingQueue<>();
    public static ExecutorService service = Executors.newScheduledThreadPool(10);
    public static int DATA_COUNT = 200;
    public static boolean IS_COMPRESS = true; // 是否解压缩，如果解压缩，那么DATA_COUNT必须200
    public static InitialDataFromClienttoServer.InitialData initialData = null;
    @Override
    public void handlerMessage(PackMessage packMessage) {
        super.handlerMessage(packMessage);

        byte array[] = packMessage.getArray();
        int offset = packMessage.getOffset();
        int len = packMessage.getLen();
        ChannelHandlerContext channelHandlerContext = packMessage.getChannelHandlerContext();
        try {
            initialData = InitialDataFromClienttoServer.InitialData.getDefaultInstance().getParserForType().parseFrom(packMessage.getArray(), offset, len);
//            System.out.println(channelHandlerContext.channel().remoteAddress() + " Say : " + initialData.toString());

            // 封装socket传输消息
            MiMessageLite miMessageLite = new MiMessageLite();
            miMessageLite.setMessageType((byte)(packMessage.getMessageType()&0xff));
            miMessageLite.setMessageLite(initialData);
            Frame.setFrontFrame(0);
            Frame.setCurrentFrame(1);

            List<Integer> list = new ArrayList<>();
            double[] tempArray = new double[200];
//            list.clear();
            list = initialData.getInitialDatasList();
            for (int i = 0; i < DATA_COUNT; i++){
                tempArray[i] = list.get(i);
            }
            // 解压缩绘图
            UnCompressTask task = null;
            if(IS_COMPRESS){
                task = new UnCompressTask(tempArray, 500, true);
            }else{
                task = new UnCompressTask(tempArray, tempArray.length, false);
            }
            Future future = service.submit(task);
            taskQueue.add(future);

        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }
    }
}
