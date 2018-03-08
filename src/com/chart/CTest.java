package com.chart;

import cn.test.uncompress.PaintThread;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.general.Dataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * 心电图测试
 * Created by Administrator on 2016/11/21 0021.
 */
public class CTest {

    private static CTest instance = null;
    private double dataArray[];
    JFrame frame = null;
    RealTimeChart rtcp = null;

    /**
     * 单例模式，可以使得数据更新更便捷
     * @return
     */
    public static CTest getInstance(){
        if (instance == null){
            synchronized (CTest.class){
                if(instance == null){
                    instance = new CTest();
                }
            }
        }
        return instance;
    }

    /**
     *  初始化心电绘制界面
     */
    public CTest(){
        frame=new JFrame("Test Chart");
        rtcp=new RealTimeChart("心电图显示","心电数据","数据点数");
        frame.getContentPane().add(rtcp,new BorderLayout().CENTER);
        frame.pack();
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent windowevent)
            {
                System.exit(0);
            }

        });
    }

    /**
     * 更新心电图
     * @param dataArray
     */
    public void updateFrame(int dataArray[]){
        try{
            if(rtcp != null){
                XYPlot plot = rtcp.jfreechart.getXYPlot();
                XYSeriesCollection dataset = (XYSeriesCollection)plot.getDataset();
                XYSeries series = dataset.getSeries(0);
                series.clear();
//                dataset.removeAllSeries();
                for (int i = 1; i <= dataArray.length; i++){
                    series.add(i, dataArray[i-1]);
                }
//                dataset.addSeries(series);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String argc[]){
        int[] result = new int[PaintThread.XCOUNT];
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        for (int i = 0; i < PaintThread.XCOUNT; i++){
                            result[i] = 1200+randomNum();
                        }
                        CTest.getInstance().updateFrame(result);
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                }
            }
        });
        thread.start();
    }


    private static int randomNum()
    {
        System.out.println((Math.random()*500));
        return (int)(Math.random()*500);
    }
}
