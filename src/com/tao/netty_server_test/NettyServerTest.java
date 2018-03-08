package com.tao.netty_server_test;

import cn.test.uncompress.BufferDataThread;
import cn.test.uncompress.PaintThread;
import cn.test.uncompress.UnCompressTask;
import com.tao.DynamicHW;
import com.tao.parser.DocterParser;
import com.tao.parser.ParserMap;
import com.tao.parser.PatientParser;
import com.tao.parser.UserParser;
import com.tao.protocol.*;
import com.xhj.cs.Bsbl;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import javax.swing.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.*;

/**
 * Author: Douglass
 * Date: 2016/3/28
 * E-mail: xuetao_ni@163.com
 */
public class NettyServerTest {
    public static int PortNumber = 10086;
    public static List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
    public static Vector<Integer> bdata = new Vector<Integer>();
    public static int i = 0;
    static DynamicHW Dc = new DynamicHW();
    public static Channel rchanner = null;
    public static ChannelHandlerContext channelHandlerContext = null;

    public NettyServerTest(){
    }

    public static void main(String[] args) {
        UserParser userParser = new UserParser();
        ParserMap.put('l', userParser);//处理登陆

        DocterParser docterParser = new DocterParser();
        ParserMap.put('b', docterParser); //增加医生
        ParserMap.put('f', docterParser);//删除医生
        ParserMap.put(4, docterParser);//更新医生
        ParserMap.put('c', docterParser); //根据姓名查询医生
        ParserMap.put('e', docterParser);//查询所有医生

        PatientParser patientParser = new PatientParser();
        ParserMap.put('g', patientParser);//增加病人
        ParserMap.put(8, patientParser);//根据姓名查询病人
        ParserMap.put(9, patientParser);//更新病人
        ParserMap.put('d', patientParser); //删除病人记录
        ParserMap.put('a', patientParser);//查询所有病人

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        init();
        try {
//            System.out.println("初始化服务器端" +" ");
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline().addLast("decoder", new CustomDecoder());
                            ch.pipeline().addLast("encoder", new CustomEncoder());
                            ch.pipeline().addLast("handler", new ReqMessageHandler());
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(PortNumber);
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }

    public static void initMessageTask(){
        MessageDealTask messageDealTask = new MessageDealTask();
        Thread thread = new Thread(messageDealTask);
        thread.start();
    }

    public static void initBufferDataThread(){
        BufferDataThread bufferDataThread = new BufferDataThread();
        Thread thread = new Thread(bufferDataThread);
        thread.start();
    }

    public static void init(){
//        System.out.println("此消息来自netty请求里NettyServerTest的init()方法" +" ");
        initMessageTask();
        Global.init();
        initBufferDataThread();
    }

    private static void createGuiAndShow() {
        JFrame frame = new JFrame("");
        frame.getContentPane().add(Dc);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1016, 400);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
