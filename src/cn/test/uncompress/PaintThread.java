package cn.test.uncompress;

import com.chart.CTest;
import com.tao.netty_server_test.NettyServerTest;
import com.tao.netty_server_test.ReceiveDataFromAS;
import com.util.RMFileUtil;
import io.netty.channel.ChannelHandlerContext;
import org.apache.ibatis.executor.Executor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.concurrent.Future;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class PaintThread implements Runnable {

    /*
		波形显示相关参数设置
	 */
    public static int XCOUNT = 512;  // 显示的数据个数
    private int dataCount = 0;	// 累计数据计算
    private double tdataArray[] = new double[XCOUNT]; // 显示数据数组
    private double filteddataArray[] = new double[XCOUNT]; // 显示数据数组
    /* 波形数据保存 */
    private boolean isSaveFile = false;
    public static int FILEDATA_COUNT = 5000;
    private int fileDataCount = 0;	// 累计的文件数据
    private double fileDataArray[] = new double[FILEDATA_COUNT];
    private static ChannelHandlerContext ctx;

    @Override
    public void run() {
//        while (true){
//            if(NettyServerTest.taskQueue.size() > 0){
//                System.out.println("=========================绘图线程绘图========================");
//                Future future = ReceiveDataFromAS.taskQueue.poll();
//                try{
//                    double[] result = (double[]) future.get();
//
//                    // 更新心电绘制界面
//                    if(dataCount == XCOUNT){
//                        CTest.getInstance().updateFrame(tdataArray);
//                        dataCount = 0;
//                    }else{
//                        for (int i = 0; i < result.length; i++){
//                            tdataArray[dataCount] = result[i];
//                            dataCount++;
//				/* 文件数据 */
//                            if(isSaveFile){
//                                fileDataArray[fileDataCount++] = result[i];
//                            }
//                        }
//                    }
//                    /* 保存到文件中 */
//                    if(fileDataCount == FILEDATA_COUNT){
//                        System.out.println("=====================将数据保存包文件夹======================");
//                        RMFileUtil.saveFileByTimecount(fileDataArray);
//                        fileDataCount = 0;
//                    }
//                }catch (Exception e){
//                    System.out.println("绘图线程异常");
//                    e.printStackTrace();
//                }
//            }
//        }
    }

    /**
     * 数据解压缩以及波形更新
     * @param ucdataArray 接收的到的数据
     * @param rlen 解压缩的结果长度
     * @return 返回解压缩之后的结果集
     */
//    public double[] noCompressData(int ucdataArray[], int rlen){
//        // 更新心电绘制界面
//        if(dataCount == XCOUNT){
//            System.out.println("===================绘制图像=====================");
//            UnCompressTask.ButterworthLowpassFilter0100SixthOrder(tdataArray, filteddataArray, XCOUNT);
//            CTest.getInstance().updateFrame(filteddataArray);
//            dataCount = 0;
//        }
//		/* 保存到文件中 */
//        if(fileDataCount == FILEDATA_COUNT){
//            System.out.println("=====================将数据保存包文件夹======================");
//            RMFileUtil.saveFileByTimecount(fileDataArray);
//            fileDataCount = 0;
//        }
//
//        for (int i = 0; i < ucdataArray.length; i++){
//            tdataArray[dataCount++] = (double) ucdataArray[i];
//			/* 文件数据 */
//            if(isSaveFile){
//                fileDataArray[fileDataCount++] = (double) ucdataArray[i];
//            }
//
//        }
//        return null;
//    }
}
