package com.tao;

import com.tao.Bsbl.Bsbl;
import com.tao.netty_server_test.NettyServerTest;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.io.IOException;
import java.util.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;

/**
 * Created by LHL on 2016/9/2.
 */

public class DynamicHW extends JPanel{
    /**
     * Create the panel.
     */
    private static List<Integer> values;                      // 保存接收到的数据的容器.
    private static List<Double> values1;
    private static int valuesQueue[] = new int [1000];
    private static int qhead = 1;
    private static int qtail = 0;
    private static int qlen = 0;
    private static final int MAX_VALUE = 2700;          // 接收到的数据的最大值.
    private static final int MAX_COUNT_OF_VALUES = 1000; // 最多保存数据的个数.
    private static int index_value = 0;
    public DynamicHW() {
        setBackground(Color.WHITE);
        values = Collections.synchronizedList(new ArrayList<Integer>());

    }
    private void initData(){
        for (int i=0; i < 1000; i++){
            valuesQueue[i] = -1;
        }
    }
    public void Draw() {
        // 使用一个线程添加数据
        new Thread(new Runnable(){
            public void run() {
                //try {
//                    while (true) {
//                        if(NettyServerTest.list.size()!=0){
//                        addValue(NettyServerTest.list.get(0)); // 产生一个数据，并模拟接收并放到容器里.
//                        repaint();
//                        NettyServerTest.list.remove(0);
//                        //Thread.sleep(2);
//                        }
                while (true){

                    if(NettyServerTest.bdata.size() != 0){
                        if(values.size() < 500 ){
                            values.add(NettyServerTest.bdata.remove(0));
                        }else {
                            // 解壓縮成為1000
                            try {
                                for (int i=0;i<500;i++){
                                    values1.add(Bsbl.Bsbl(values)[i]);
                                }

                            }catch (IOException e){
                                e.printStackTrace();
                            }
                            repaint();
                        }
                        //System.out.println("paint time:"+ Calendar.getInstance().getTimeInMillis());
                    }

                }
            }
        }).start();
    }

    public void paintComponent(Graphics g) {
        if (values !=null) {
            // 判断是否已经接收到500个点
            int length = values1.size();
            System.out.println("======================"+length);
//            g2d.drawLine(0, 100, 500, 100);
            if(length >= 1000){
                //            double uncomPress[] =  //
                super.paintComponent(g);
                System.out.println("======================"+values);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int w = getWidth();
                int h = getHeight();
                int xDelta = w / MAX_COUNT_OF_VALUES;
                System.out.println("======================"+length);
                for (int i = 0; i <length-1 ; ++i) {
                    int x1 = (xDelta * i);
                    int x2 = xDelta * (i +1);
                    int y1 = 450-normalizeValueForYAxis(new Double(values1.get(i)).intValue(), h);
                    int y2 = 450-normalizeValueForYAxis(new Double(values1.get(i+1)).intValue(), h);
                    System.out.println("=============="+values1.get(i)+","+w+","+h+"("+x1+","+y1+","+x2+","+y2+")");
                    g2d.drawLine(x1, y1, x2, y2 );
                }
                values.clear();
            }

//            int temp = qhead;
//            if(qlen > 1){
//                for (int i = 1; i <= 999 ; i++) {
//                    int value = valuesQueue[i%MAX_COUNT_OF_VALUES];
//                    int value1 = valuesQueue[(i+1)%MAX_COUNT_OF_VALUES];
//                    //System.out.println("绘图的值："+value+","+value1);
//                    if((value != -1)&&(value1 != -1)){
//                        g2d.drawLine((xDelta * i), 450-normalizeValueForYAxis(value, h),
//                                xDelta * (i +1), 450-normalizeValueForYAxis(value1, h));
//                    }
//                    temp++;
//                }
//                temp = (qtail+1)%MAX_COUNT_OF_VALUES;
//                if(temp != qhead){
//                    qtail = temp;
//                    qlen--;
//                }
//            }
        }
    }
    /**
     * 接收到的数据放入内存.
     * @param value
     */
//    private void addValue(int value) {
//        // 循环的使用一个接收数据的空间.
//        // 最好是实现一个循环数组，而不是偷懒的使用ArrayList.
//        values.add(value);
//        if (values.size() < MAX_COUNT_OF_VALUES) {
//            values.add(value-1000);
//            //System.out.println(values.get(0));
//        }else{
//            values.set(index_value%MAX_COUNT_OF_VALUES, value-1000);
//            index_value++;
//        }
//        System.out.println("循环队列:"+qhead+","+qtail+","+value);
//
//        if(qhead == qtail){
//            System.err.println("循环队列溢出！"+qhead+","+qtail);
//            System.exit(-10);
//        }else{
//            valuesQueue[qhead] = value;
//            ++qlen;
//            qhead = (qhead+1)%MAX_COUNT_OF_VALUES;
//        }
//    }

    /**
     * 规一化y轴方向的值. 使得value在y轴的值为[0, height]之间.
     *
     * @param value
     * @param height
     * @return
     */
    private int normalizeValueForYAxis(int value, int height) {
        return (int) ((double) height / MAX_VALUE * value);
    }
}

