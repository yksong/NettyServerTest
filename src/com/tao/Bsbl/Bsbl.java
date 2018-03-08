package com.tao.Bsbl;

import com.tao.netty_server_test.NettyServerTest;

import java.io.IOException;
import java.util.Date;
import java.util.List;


public class Bsbl {
	static{
		System.loadLibrary("MyDLL");
	}
	private static final int MAX_COUNT_OF_VALUES = 500; // 最多保存数据的个数.

	public native double[] BSBL_EM(double[][] Phi, double[][] y, int[] blkStartLoc, int LearnLambda,
			   int LEARNTYPE, int MAX_ITERS, double EPSILON, Boolean PRINT);
	
	public  static double[] Bsbl(List<Integer> data) throws IOException{
        long begin,end,time;
        Date mydate=new Date();
        begin=mydate.getTime();
		  
        Bsbl Bsbl_Test = new Bsbl();
        Filedata datainput = new Filedata();
        double[][] ori=new double[500][500];
        double[] x = new double[1000];
        int[] blkStartLoc = new int[40];
        int LearnLambda =0;
        int LEARNTYPE =1;
        int MAX_ITERS =20;
        double EPSILON =1e-8;
        Boolean PRINT = false;
        int m_b =1000;
        blkStartLoc = new int[m_b];
        for(int i=0;i<MAX_COUNT_OF_VALUES;i++){
            ori[i][0]= data.get(i);
        }
        double[][] phi = datainput.inputdata(System.getProperty("user.dir")+"/"+"phi_500_1000.txt");
        for(int i=0;i<m_b/25;i++){
            blkStartLoc[i] = 25*i;
        }
        x = Bsbl_Test.BSBL_EM(phi, ori, blkStartLoc, LearnLambda, LEARNTYPE, MAX_ITERS, EPSILON, PRINT);
        System.out.println("done");
        Date mydate2=new Date();
        end=mydate2.getTime();
        time=end-begin;
        System.out.println("运行时间为:"+time);
        return x;
	}
}
