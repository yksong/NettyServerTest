package com.tao.netty_server_test;

import cn.test.uncompress.BufferDataThread;
import cn.test.uncompress.UnCompressTask;
import com.chart.CTest;
import com.tao.protocol.InitialDataHandler;
import com.tao.protocol.PackMessage;
import com.util.RMFileUtil;
import io.netty.channel.ChannelHandlerContext;
import net.sf.json.JSONObject;
import java.util.*;
import java.util.concurrent.*;

/**
 * Created by DXS on 2017/3/23.
 */
public class ReceiveDataFromAS implements Runnable{
    public static BlockingQueue<Future> taskQueue = new LinkedBlockingQueue<>();
    public static ExecutorService service = Executors.newScheduledThreadPool(10);
    public static int DATA_COUNT = 200;
    public static boolean IS_COMPRESS = true; // 是否解压缩，如果解压缩，那么DATA_COUNT必须200
    public static List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
    public static int initialdata = 0;
    public static String key = null;
    public static int value = 0;
    private static ChannelHandlerContext ctx;
    private static PackMessage packMessage;
    private static String msg = null;

    public  ReceiveDataFromAS(ChannelHandlerContext ctx, String msg, PackMessage packMessage){
        this.ctx = ctx;
        this.msg = msg;
        this.packMessage = packMessage;
//        System.out.println(ctx.channel().remoteAddress() + " Say : " + msg);
    }

    public void run() {
//        Thread thread = new Thread(new BufferDataThread(ctx, packMessage));
//        thread.start();
        int dataCount = 0;
        int dataArray[] = new int[DATA_COUNT];

        while (true){
            if (dataCount == DATA_COUNT) {
                double[] tempArray = new double[dataArray.length];
//                System.out.println("===========接收到的数据========"+dataCount);
                for (int i = 0; i < DATA_COUNT / 50; i++) {
                    String temp = "";
                    for (int j = 0; j < 50; j++) {
//                        temp += dataArray[i * 50 + j] + " ";
                        tempArray[i * 50 + j] = dataArray[i * 50 + j];
                    }
//                    System.out.println(temp);
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
//                System.out.println("===========end================");
                dataCount = 0;
            }
//            initialdata = InitialDataHandler.initialData.getInitialDatas(dataCount);
            dataArray[dataCount] = initialdata;
            dataCount++;
        }
    }
}
