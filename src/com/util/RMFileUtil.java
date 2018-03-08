package com.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/5 0005.
 */
public class RMFileUtil {

    public static int FILE_COUNT = 0;
    private static int DATA_PER_ROW = 50;
    public static void saveFileByTimecount(double dataArray[]){
        if(RMFileUtil.FILE_COUNT == Integer.MAX_VALUE){
            RMFileUtil.FILE_COUNT = 0;
        }
        String fileName = getDateTime()+ RMFileUtil.FILE_COUNT+".txt";
        String saveTodir = System.getProperty("user.dir")+"/res/datafile/";
        System.out.println("=====================将数据保存包文件"+saveTodir+fileName+"======================");
        File file = new File(saveTodir+fileName);
        try{
            while (file.exists()){
                fileName = getDateTime()+RMFileUtil.FILE_COUNT;
            }

            file.createNewFile(); // 需要创建一个新的文件
            FileWriter fileWriter = new FileWriter(file);
            String result = arrayToString(dataArray);
            fileWriter.write(result);
            fileWriter.flush();
            fileWriter.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        RMFileUtil.FILE_COUNT++;
    }

    public static String getDateTime(){
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_");
        String time = dateFormat.format(now);
        System.out.println(time);
        return time;
    }

    private static String arrayToString(double dataArray[]){
        StringBuilder builder = new StringBuilder();
        int len = dataArray.length;
        int row = len/DATA_PER_ROW;
        for (int i = 0 ; i < row; i++){
            for (int j = 0; j < DATA_PER_ROW; j++){
                builder.append(dataArray[i*50+j]);
                if(j != (DATA_PER_ROW-1)){
                    builder.append(" ");
                }
            }
            if(i != (row-1)){
                builder.append("\r\n");
            }
        }
        return builder.toString();
    }
}
