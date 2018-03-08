package com.tao.netty_server_test;

import com.tao.log.LogUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.net.InetAddress;

/**
 * Author: Douglass
 * Date: 2016/3/28
 * E-mail: xuetao_ni@163.com
 */
public class ServerHandler extends SimpleChannelInboundHandler<String> {
    public static ReceiveDataFromAS receiveDataFromAS;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        LogUtils.I("RemoteAddress : " + ctx.channel().remoteAddress() + " active !");
        //String msg = "Welcome to " + InetAddress.getLocalHost().getHostName() + " service!\r\n";
        //ctx.writeAndFlush(msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //FunctionParser.parseFunction(ctx, msg);
//        receiveDataFromAS = new ReceiveDataFromAS(ctx, msg);
//        Thread thread = new Thread(receiveDataFromAS);
//        thread.start();
    }
}
