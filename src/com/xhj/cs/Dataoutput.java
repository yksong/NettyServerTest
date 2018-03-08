package com.xhj.cs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Dataoutput {
	
    private String path;
    private String filename;
    public  Dataoutput(){
    	//path��ʾ���������ļ���·��
    	path = "C:/Users/xhj/WorkSpace/ListenerC";
    }
	public boolean createFile(String file_name){
		File f = new File(path);
		if(!f.exists()){
		f.mkdirs();
		} 
	    filename = file_name+".txt";
		File file = new File(f,filename);
		if(!file.exists()){
		  try {
		  file.createNewFile();
		  } catch (IOException e) {
		  e.printStackTrace();
		  return false;
		  }
		}
		return true;	
    }
	
	public void outputdata(double[] x,String datasource) throws IOException{
		createFile(datasource);
		File file = new File(path+"/"+filename); 
		FileWriter out = new FileWriter(file);  //�ļ�д����
		for(int i=0;i<x.length;i++){
			    out.write(x[i]+"\n");
			  }
			  out.close();
	}
	
}