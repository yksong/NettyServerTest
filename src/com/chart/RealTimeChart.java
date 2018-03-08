package com.chart;

import cn.test.uncompress.BufferDataThread;
import cn.test.uncompress.PaintThread;
import com.xhj.cs.Bsbl;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;

/**
 * 心电图显示
 * Created by Administrator on 2016/11/21 0021.
 */
public class RealTimeChart extends ChartPanel{
    public static JFreeChart jfreechart = null;
    private static TimeSeries timeSeries;
    private static XYPlot plot = null;
    private static XYSeries firefox = null;
    public RealTimeChart(String chartContent, String title, String yaxisName)
    {
        super(createChart(chartContent,title,yaxisName));
    }
    private static JFreeChart createChart(String chartContent,String title,String yaxisName){
        //创建时序图对象
        timeSeries = new TimeSeries(chartContent,Millisecond.class);
        jfreechart = ChartFactory.createXYLineChart(
                chartContent ,
                yaxisName ,
                title ,
                createDataset() ,
                PlotOrientation.VERTICAL ,
                true , true , false);
        ChartConfig.fontConfig(jfreechart);
        plot = jfreechart.getXYPlot();
        // 设置纵坐标范围
        ValueAxis valueAxis = plot.getRangeAxis();
        valueAxis.setRange(0, 4000);
        valueAxis.setAutoRange(false);
        plot.setRangeAxis(valueAxis);
        // 设置横坐标单元
        NumberAxis numberaxis1 = (NumberAxis)plot.getDomainAxis();
        numberaxis1.setUpperBound(BufferDataThread.XCOUNT);//最大值
        numberaxis1.setLowerBound(0D);//最小值
        // 设置绘图的属性，比如颜色等
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer( );
        renderer.setSeriesPaint( 0 , Color.RED ); // 设置绘图颜色
        renderer.setSeriesStroke( 0 , new BasicStroke( 1.0f ) ); // 设置线条粗细
        renderer.setBaseShapesVisible(false);// 设置拐点是否可见
        plot.setRenderer( renderer );
        return jfreechart;
    }
    private static XYDataset createDataset()
    {
        firefox = new XYSeries( "心电数据" );

        int len = PaintThread.XCOUNT;
        for (int i = 1; i <= len; i++ ){
            firefox.add(i, 500);
        }
        final XYSeriesCollection dataset = new XYSeriesCollection( );
        dataset.addSeries(firefox);
        return dataset;
    }

    public void run()
    {

        while(true)
        {
//            try
//            {
//                // 心电时序图
//                XYSeriesCollection dataset = (XYSeriesCollection)plot.getDataset();
//                XYSeries series = dataset.getSeries(0);
//                series.clear();
//                for (int i = 1; i <= 500; i++){
//                    double two = randomNum();
//                    series.add(i, two);
//                }
//                Thread.sleep(1000);
//            }
//            catch (InterruptedException e)  {   }
        }
    }

}
