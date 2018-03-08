package com.tao.Bsbl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Filedata {
	private List<Double> values;
	private double[][] value ;
    private String filepath;
    private int linenumber;

    public List<Double> filereader(String route) {
    	//int i=1;//����
    	filepath = route;
    	values = Collections.synchronizedList(new ArrayList<Double>());
    	File txt =new File(filepath);
    	FileInputStream fis;
    	linenumber = 0;
    	try{
    		fis = new FileInputStream(txt);
    		InputStreamReader isr = new InputStreamReader(fis);
    		BufferedReader  br = new BufferedReader(isr);
    		String temp = null;
    		double a;
    		//double max= 0;
    		//double min= 0;
    		while((temp=br.readLine())!=null &&!"".equals(temp)){
    			//values.add(Integer.parseInt(temp.trim()));
    			//a= Double.valueOf(temp);
    			String[] temps = temp.split(" ");
    			 for(int j=0;j<temps.length;j++){
    				    values.add(Double.parseDouble(temps[j]));
    				   }
    			linenumber++;
    			//System.out.println(a);
    			//values.add(a);
    		}
    		isr.close();
    	}catch(Exception e1){
    		e1.printStackTrace();
    	}
    	return values;
    }
    public double[][] inputdata(String route){
    	filereader(route);
    	int length = values.size();
    	//value = new double[length];
    	int cow ;
    	if(length < 1001){
    	    value = new double[length][1];
    	for(int i=0;i<length;i++){
    		value[i][0] = values.get(i);
    	}
    	}else{
    		cow = length/1000;
    		value = new double[cow][1000];
    	    for(int i=0;i<cow;i++)
    	    {
    	    	for(int j=0 ;j<1000 ;j++){
    		         value[i][j] = values.get(i*1000+j); 	
    	    	}
    	    }
    	 }
    	return value;
    }
   }

