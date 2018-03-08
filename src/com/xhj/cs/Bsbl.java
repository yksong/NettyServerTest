package com.xhj.cs;

import com.chart.CTest;
import com.util.RMFileUtil;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 对接收的数据进行解压缩
 * 在此类中调用了DLL库，由于导出的JNI函数的包路径是固定的，所以该类的路径和名字必须保持不变
 * 不能将此类移动到其他路径，否则会出现Native method调用错误
 */
public class Bsbl {
	// 加载dll库
	static{
		System.loadLibrary("MyDLL500"); // 如果压缩解压缩的数据个数不同，应该使用不同的dll库文件
	}
	// Java JNI函数声明
	public static native double[] BSBL_EM(double[][] Phi, double[][] y, int[] blkStartLoc, int LearnLambda, int LEARNTYPE, int MAX_ITERS, double EPSILON, Boolean PRINT);

	/**
	 * 数据解压缩以及波形更新
	 * @param dataArray 接收的到的数据
	 * @param rlen 解压缩的结果长度
     * @return 返回解压缩之后的结果集
     */
	public static double[] unCompressData(double dataArray[], int rlen){
		double result[] = new double[rlen];
		int inputArrayLen = dataArray.length;
		Filedata datainput = new Filedata(); // 读取
		// 固定参数
		int LearnLambda =0;
		int LEARNTYPE =1;
		int MAX_ITERS =20;
		double EPSILON =1e-8;
		Boolean PRINT = false;
		// 解压缩辅助矩阵
		int blkStartLoc[] = new int[rlen];
		// 本身是一维矩阵200*1
		double[][] ori = new double[inputArrayLen][1];
		for (int i = 0; i < inputArrayLen; i++ ){
			ori[i][0] = dataArray[i];
		}
		double[][] phi = datainput.inputdata500(System.getProperty("user.dir")+"/res/"+"phi_200_500_ansi.txt", 500);
//		System.out.println("压缩矩阵行、列("+phi.length+","+phi[0].length+")");
//		System.out.println("解压缩矩阵行、列("+ori.length+","+ori[0].length+")");
		// 解压缩计算
		for(int i=0;i<rlen/25;i++){
			blkStartLoc[i] = 25*i;
		}
		result = Bsbl.BSBL_EM(phi, ori, blkStartLoc, LearnLambda, LEARNTYPE, MAX_ITERS, EPSILON, PRINT);
		System.out.println("解压缩矩阵长度："+result.length);
		return result;
	}

	private void testUnCompress() throws Exception{
		//		Filedata datainput = new Filedata();
//		double[][] phi = datainput.inputdata500(System.getProperty("user.dir")+"/res/"+"phi_200_500_ansi.txt", 500);
////		double[][] phi = datainput.inputdata(System.getProperty("user.dir")+"/res/"+"phi_500_1000.txt");
//		System.out.println("压缩矩阵行、列("+phi.length+","+phi[0].length+")");
		long begin,end,time;
		Date mydate=new Date();
		begin=mydate.getTime();

		Bsbl Bsbl_Test = new Bsbl();
		Filedata datainput = new Filedata();
		Dataoutput dataoutput = new Dataoutput();
		double[] x = new double[1000];
		int[] blkStartLoc = new int[40];
		int LearnLambda =0;
		int LEARNTYPE =1;
		int MAX_ITERS =20;
		double EPSILON =1e-8;
		Boolean PRINT = false;
		int m_b =1000;
		blkStartLoc = new int[m_b];
		double[][] ori = datainput.inputdata(System.getProperty("user.dir")+"/res/"+"ecg4_5.txt");
		double[][] phi = datainput.inputdata(System.getProperty("user.dir")+"/res/"+"phi_500_1000.txt");
		System.out.println("压缩矩阵行、列("+phi.length+","+phi[0].length+")");
		System.out.println("解压矩阵行、列("+ori.length+","+ori[0].length+")");
		for(int i=0;i<m_b/25;i++){
			blkStartLoc[i] = 25*i;
		}
		x = Bsbl_Test.BSBL_EM(phi, ori, blkStartLoc, LearnLambda, LEARNTYPE, MAX_ITERS, EPSILON, PRINT);
		dataoutput.outputdata(x, "ecg4_5_500_1000");
		System.out.println("done");
		Date mydate2=new Date();
		end=mydate2.getTime();
		time=end-begin;
		System.out.println("运行时间为："+time);
	}
	public static void main (String args[]) throws IOException {
		try{
			String fileName = RMFileUtil.getDateTime()+".txt";
			String saveTodir = System.getProperty("user.dir")+"/res/datafile/";
			File file = new File(saveTodir+fileName);
			file.createNewFile();
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}
