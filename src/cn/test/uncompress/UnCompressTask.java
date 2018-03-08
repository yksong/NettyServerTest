package cn.test.uncompress;

import com.chart.CTest;
import com.util.RMFileUtil;
import com.xhj.cs.Bsbl;
import com.xhj.cs.Filedata;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * Created by Administrator on 2016/12/13 0013.
 */
public class UnCompressTask implements Callable<double[]> {

    private double[] dataArray;
    private double[] filterResult;
    private double[] MediafilterResult;
    private int rlen;
    private boolean isCompress;

    public UnCompressTask(double dataArray[], int rlen, boolean isCompress){
        this.dataArray = dataArray;
        this.rlen = rlen;
        this.isCompress = isCompress;
    }
    @Override
    public double[] call() throws Exception {
//        double result[] = unCompressDataArraay(dataArray, rlen); // 保存解压缩结果
        if(this.isCompress){
            System.out.println("正在进行解压");
            double result[] = Bsbl.unCompressData(dataArray, rlen);
            filterResult = new double[result.length];
            MediafilterResult=new double[result.length];
            ButterworthLowpassFilter0100SixthOrder(result, filterResult, rlen);
            //由于前面几个数字导致波形异常，这里选择最后10个数据去代替开始的10个数据
            double temparray[]=new double[10];
            int j=0;
            for(int i=490;i<filterResult.length;i++){
                temparray[j++]=filterResult[i];
            }
            for(int i =9;i >=0;i--)
            {
                filterResult[9-i]=temparray[i];
            }
              MediaFilter(filterResult,MediafilterResult,rlen);
        }else{
            System.out.println("非压缩数据");
            filterResult = new double[dataArray.length];
            ButterworthLowpassFilter0100SixthOrder(dataArray, filterResult, dataArray.length);
        }

        return MediafilterResult;
    }

    //butterWorth滤波器
    public static void ButterworthLowpassFilter0100SixthOrder( double src[], double dest[], int size)
    {
        final int NZEROS = 6;
        final int NPOLES = 6;
        final double GAIN = 2.936532839e+03;
        double[]xv=new double[NZEROS+1];
        double[]yv=new double[NPOLES+1];

        for (int i = 0; i < size; i++)
        {
            xv[0] = xv[1]; xv[1] = xv[2]; xv[2] = xv[3]; xv[3] = xv[4]; xv[4] = xv[5]; xv[5] = xv[6];
            xv[6] = src[i] / GAIN;
            yv[0] = yv[1]; yv[1] = yv[2]; yv[2] = yv[3]; yv[3] = yv[4]; yv[4] = yv[5]; yv[5] = yv[6];
            yv[6] =   (xv[0] + xv[6]) + 6.0 * (xv[1] + xv[5]) + 15.0 * (xv[2] + xv[4])
                    + 20.0 * xv[3]
                    + ( -0.0837564796 * yv[0]) + (  0.7052741145 * yv[1])
                    + ( -2.5294949058 * yv[2]) + (  4.9654152288 * yv[3])
                    + ( -5.6586671659 * yv[4]) + (  3.5794347983 * yv[5]);
            dest[i] = yv[6];
        }
    }

    //中位值平滑滤波算法
    public static void MediaFilter( double src[], double dest[], int size){
        int N=12;
        int count=0;
        double[] array_temp=new double[N];
        for (int i = 0; i < size; i++){
            if(i>(500-N)){
                dest[i]=src[i];
            }else {
                for(int j=0;j<N;j++){
                    array_temp[j]=src[j+count];
                    dest[i]=fliter(array_temp);
                }
                count++;
            }
        }
    }

    public static double fliter(double[] array){
        int N=array.length;
        int i,j,count;
        double sum=0;
        double temp;
        double[] array_temp=new double[N];
        for(i=0;i<N;i++)
        {
            array_temp[i]=array[i];   //把数组中的值给临时数组.对临时数组进行排序.
        }
        for (j=0;j<N-1;j++)
        {
            for (i=0;i<N-j-1;i++)
            {
                //冒泡排序
                if (array_temp[i]>array_temp[i+1])
                {
                    temp = array_temp[i];
                    array_temp[i] = array_temp[i+1];
                    array_temp[i+1] = temp;
                }
            }
        }
        for(count=1;count<N-1;count++)             //这里做了修改
            sum += array_temp[count];            //去掉最大值和最小值
        return (sum/(N-2));
    }
}
