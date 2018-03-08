package cn.test.uncompress;

import com.chart.CTest;
import com.google.protobuf.MessageLite;
import com.protocol.DataFromServertoClient;
import com.protocol.FeedbackInfoFromClienttoServer;
import com.protocol.InfoConfirm;
import com.tao.netty_server_test.NettyServerTest;
import com.tao.netty_server_test.ReceiveDataFromAS;
import com.tao.protocol.*;
import com.util.RMFileUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import sun.nio.ch.Net;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by DXS on 2017/3/23.
 */
public class BufferDataThread implements Runnable{

    public static boolean updateFrame = true;
    public static int XCOUNT = 1500;  // 显示的数据个数
    public static int tdataArrayToInt[] = new int[XCOUNT];
//    public static double[] result = new double[500];
    private int dataCount = 0;	// 累计数据计算

    @Override
    public void run() {
        while (true){
//            if(InitialDataHandler.taskQueue.size() > 0){
//                try{
//                    if(updateFrame == true){
//                        int dataCount = 0;
//                        for (int i = 0; i < 3; i++){
//                            Future future = InitialDataHandler.taskQueue.take();
//                            double[] result = (double[]) future.get();
//                            // 绘制第一帧数据
//                            for (int j = 0; j < result.length; j++){
//                                tdataArrayToInt[dataCount++] = (int)result[j];
//                            }
//                        }
//                        NettyServerTest.rchanner.pipeline().writeAndFlush(buildUncompressedDataMessage());
//                        NettyServerTest.rchanner.pipeline().writeAndFlush(buildConfirmInfoMessage());
//                        CTest.getInstance().updateFrame(tdataArrayToInt);
//                        updateFrame = false;
//                    }
////                  Frame.setUncompressData(tdataArrayToInt);
//                }catch (Exception e){
//                    System.out.println("数据缓存线程异常");
//                    e.printStackTrace();
//                }
//            }

            if(InitialDataHandler.taskQueue.size() > 0){
                try{
                    Future future = InitialDataHandler.taskQueue.poll();
                    if(updateFrame == true){
                        double[] result = (double[]) future.get();
                        if(dataCount == XCOUNT){
                            NettyServerTest.rchanner.pipeline().writeAndFlush(buildUncompressedDataMessage());
                            NettyServerTest.rchanner.pipeline().writeAndFlush(buildConfirmInfoMessage());
                            for (int i = 0; i < tdataArrayToInt.length; i++){
//                                System.out.println("输出tdataArray的值为： " + tdataArrayToInt[i]);
                            }
                            CTest.getInstance().updateFrame(tdataArrayToInt);
                            RMFileUtil.getDateTime();
                            updateFrame = false;
                            dataCount = 0;
                        }else {
                            for (int i = 0; i < result.length; i++) {
                                tdataArrayToInt[dataCount] = (int)result[i];
                                dataCount++;
                            }
                        }
                    }
//                  Frame.setUncompressData(tdataArrayToInt);
                }catch (Exception e){
                    System.out.println("数据缓存线程异常");
                    e.printStackTrace();
                }
            }

        }
    }

    private MiMessageLite buildUncompressedDataMessage(){
        DataFromServertoClient.UncompressData.Builder dataFromServerBuilder = DataFromServertoClient.UncompressData.newBuilder();
        dataFromServerBuilder.setLastframe(0);
        dataFromServerBuilder.setCurrentframe(1);
        List<Integer> list = new ArrayList<>();
        list.clear();
        for (int i = 0;i < XCOUNT; i++){
            list.add(tdataArrayToInt[i]);
        }
        dataFromServerBuilder.addAllUncompressDatas(list);
        DataFromServertoClient.UncompressData dataEntity = dataFromServerBuilder.build();
//        System.out.println("返回给客户端的数据包为： " + dataEntity.toString());

        MiMessageLite miMessageLite = new MiMessageLite();
        miMessageLite.setMessageType((byte)0x02);
        miMessageLite.setMessageLite((MessageLite)dataEntity);
        return miMessageLite;
    }

    private MiMessageLite buildConfirmInfoMessage(){
        InfoConfirm.ComfirmInfo.Builder builder = InfoConfirm.ComfirmInfo.newBuilder();
        builder.setAndroidFrame(true);
        InfoConfirm.ComfirmInfo dataEntity = builder.build();

        MiMessageLite miMessageLite = new MiMessageLite();
        miMessageLite.setMessageType((byte)0x03);
        miMessageLite.setMessageLite((MessageLite)dataEntity);
        return miMessageLite;
    }

}
