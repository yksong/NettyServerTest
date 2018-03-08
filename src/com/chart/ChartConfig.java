package com.chart;

import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.title.TextTitle;

import java.awt.*;

/**
 * Created by Administrator on 2016/11/21 0021.
 */
public class ChartConfig {
    public static void fontConfig(JFreeChart chart){
        // 配置字体
        Font xfont = new Font("宋体", Font.PLAIN, 12);// X轴
        Font yfont = new Font("宋体", Font.PLAIN, 12);// Y轴
        Font kfont = new Font("宋体", Font.PLAIN, 12);// 底部
        Font titleFont = new Font("隶书", Font.BOLD, 25); // 图片标题
        XYPlot plot = (XYPlot)chart.getPlot();// 图形的绘制结构对象
        // 图片标题
        chart.setTitle(new TextTitle(chart.getTitle().getText(), titleFont));
        // 底部
        chart.getLegend().setItemFont(kfont);
        // X 轴
        ValueAxis domainAxis = plot.getDomainAxis();
        domainAxis.setLabelFont(xfont);// 轴标题
        domainAxis.setTickLabelFont(xfont);// 轴数值
        domainAxis.setTickLabelPaint(Color.BLUE); // 字体颜色

        ValueAxis raix = plot.getRangeAxis();
        raix.setLabelFont(yfont);
        raix.setLabelPaint(Color.blue);
        raix.setTickLabelFont(yfont);
    }
}
