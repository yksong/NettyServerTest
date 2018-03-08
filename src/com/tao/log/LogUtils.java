package com.tao.log;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Author: Douglass
 * Date: 2016/4/5
 * E-mail: xuetao_ni@163.com
 */
public class LogUtils {
    //控制打印级别，在level级别之上才可以被打印出来
    private static int level = 1;
    //打印级别为W
    public static int W = 2;
    //打印级别为I
    public static int I = 3;
    //打印级别为E
    public static int E = 4;

    //针对天数级别的。如果当天已经存在一个LOG了，而使用者需要新开一个LOG，那么将计数
    private static int FILE_LOG_COUNT = 0;
    //单个日志的最大的容量,如果一个日志太大了，打开会影响效率
    private static int LOG_MAX_SIZE = 6 * 1024 * 1024;

    //打印修饰符号
    private final static String _L = "[";
    private final static String _R = "]";

    //是否同步输出到本地日志文件
    private static boolean IS_SYNC = false;
    //设置是否打印到控制台
    private static boolean DEBUG_MODE = true;
    //日志保存目录，默认为当前文件夹
    private static String LOG_FILE_DIR = ".";
    //生成一个日期文件名格式的日式格式对象
    private final static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd",
            Locale.getDefault());
    private final static SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
            Locale.getDefault());

    public static void closeLog(){
        /**
         * closeLog 关闭日志功能
         * @author: Douglass
         * @time: 2016/4/5 21:05
         * @params: []
         * @return: void
         * @throw:
         */
        LogUtils.level = 10;
    }

    public static void openLog(){
        /**
         * openLog 开启日志功能
         * @author: Douglass
         * @time: 2016/4/5 21:06
         * @params: []
         * @return: void
         * @throw:
         */
        LogUtils.level = 1;
    }

    public static void closeDebugLog(){
        /**
         * closeDebugLog 关闭控制台输出
         * @author: Douglass
         * @time: 2016/4/5 21:31
         * @params: []
         * @return: void
         * @throw:
         */
        DEBUG_MODE = false;
    }

    public static void setLogPath(String logPath){
        /**
         * setLogPath 设置日志路径
         * @author: Douglass
         * @time: 2016/4/5 20:51
         * @params: [logPath]
         * @return: void
         * @throw:
         */
        File file = new File(logPath);
        if (file.mkdir()){
            LOG_FILE_DIR = logPath;
        }else {
            LOG_FILE_DIR = ".";
        }
    }

    public static void setSync(boolean flag){
        /**
         * setSync 设置是否同步到日志文件
         * @author: Douglass
         * @time: 2016/4/5 21:21
         * @params: [flag]
         * @return: void
         * @throw:
         */
        IS_SYNC = flag;
    }

    public static void I(String msg){
        /**
         * I I级别打印日志
         * @author: Douglass
         * @time: 2016/4/6 19:12
         * @params: [msg]
         * @return: void
         * @throw:
         */
        if (I >= level){
            print(msg);
        }
    }

    public static void E(String msg){
        /**
         * E E级别打印日志
         * @author: Douglass
         * @time: 2016/4/6 19:12
         * @params: [msg]
         * @return: void
         * @throw:
         */
        if (E >= level){
            print(msg);
        }
    }

    public static void W(String msg){
        /**
         * W W级别打印日志
         * @author: Douglass
         * @time: 2016/4/6 19:12
         * @params: [msg]
         * @return: void
         * @throw:
         */
        if (W >= level){
            print(msg);
        }
    }

    private static void print(String msg){
        /**
         * print 打印日志
         * @author: Douglass
         * @time: 2016/4/6 19:19
         * @params: [msg]
         * @return: void
         * @throw:
         */
        if (DEBUG_MODE){
            System.out.println(getCurrentTime() + msg);
        }

        if (IS_SYNC){
            writeLog(getCurrentTime() + msg);
        }
    }

    private static void writeLog(String msg){
        /**
         * writeLog 将日志信息写入文件
         * @author: Douglass
         * @time: 2016/4/6 19:10
         * @params: [msg]
         * @return: void
         * @throw:
         */
        File file = getFile();
        if (file != null){
            try {
                FileWriter fileWriter = new FileWriter(file, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.append("\n");
                printWriter.append(msg);
                printWriter.append("\n");
                printWriter.flush();
                printWriter.close();
                printWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }else {
            System.out.println(getCurrentTime() + "Write log error! Due to the file dir error!");
        }
    }

    private static File getFile(){
        /**
         * getFile 返回日志文件的File对象
         * @author: Douglass
         * @time: 2016/4/5 22:23
         * @params: []
         * @return: java.io.File
         * @throw:
         */
        //判断今天的日志文件夹是否存在，不存在就创建
        File file = new File(LOG_FILE_DIR + File.separator + getCurrTimeDir());
        if (!file.exists()){
            file.mkdir();
            FILE_LOG_COUNT = 0;
        }

        synchronized (LogUtils.class){
            //判断今天最新的日志是否存在，不存在就新建
            File f = new File(LOG_FILE_DIR + File.separator + getCurrTimeDir() + File.separator
                    + FILE_LOG_COUNT + ".log");
            if (f.exists()){
                //判断今天最新的日志大小是否超出规定值，超出就新建下一个日志
                if (f.length() >= LOG_MAX_SIZE){
                    FILE_LOG_COUNT++;
                    File nextFile = new File(LOG_FILE_DIR + File.separator + getCurrTimeDir() + File.separator
                            + FILE_LOG_COUNT + ".log");
                    try {
                        nextFile.createNewFile();
                        return nextFile;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    return f;
                }
            }else {
                try {
                    f.createNewFile();
                    return f;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private static String getCurrentTime(){
        /**
         * getCurrentTime 获取当前时间字符串
         * @author: Douglass
         * @time: 2016/4/5 21:32
         * @params: []
         * @return: java.lang.String
         * @throw:
         */
        return _L + time.format(new Date()) + _R;
    }

    private static String getCurrTimeDir(){
        /**
         * getCurrTimeDir 获取时间组成的路径
         * @author: Douglass
         * @time: 2016/4/5 21:43
         * @params: []
         * @return: java.lang.String
         * @throw:
         */
        return sdf.format(new Date());
    }
}
