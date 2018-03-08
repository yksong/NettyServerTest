package com.xhj.cs;

import java.io.IOException;
import java.util.Date;

public class Mfocuss {
	static{
		System.loadLibrary("DLL_MFocuss");
	}
	public native double[] MFOCUSS(double[][] Phi, double[][] y, double lambda, double p,
			double PRUNE_GAMMA, int MAX_ITERS, double EPSILON, Boolean PRINT);
	
	public static void main (String args[]) throws IOException{
		   long begin,end,time;
		   Date mydate=new Date();
		   begin=mydate.getTime();
		  
		   Mfocuss Mf_Test = new Mfocuss();
		   Filedata datainput = new Filedata();
		   Dataoutput dataoutput = new Dataoutput();
		   double[] x = new double[1000];
		   double lambda= 0.1;
		   double p= 0.8;
		   double PRUNE_GAMMA =1e-3;
		   int MAX_ITERS =20; 
		   double EPSILON =1e-8; 
		   Boolean PRINT = false;
		   double[][] ori = datainput.inputdata(System.getProperty("user.dir")+"/"+"ecg2.txt");
		   double[][] phi = datainput.inputdata(System.getProperty("user.dir")+"/"+"phi_500_1000.txt");

		   x = Mf_Test.MFOCUSS(phi, ori, lambda, p, PRUNE_GAMMA, MAX_ITERS, EPSILON, PRINT);
	       dataoutput.outputdata(x, "ecg2_500_1000_mfocuss");
	       
		   System.out.println("done");
		   Date mydate2=new Date();
		   end=mydate2.getTime();
		   time=end-begin;
		   System.out.println("运行时间为："+time);
	}
}
