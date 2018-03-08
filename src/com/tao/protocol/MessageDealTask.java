package com.tao.protocol;

/**
 * Created by DXS on 2017/3/29.
 */
public class MessageDealTask implements Runnable {
    @Override
    public void run() {
        while (true){
            try {
//                System.out.println("此消息来自netty请求里MessageDealTask的run()方法" +" ");
                PackMessage packMessage = Global.messageQueue.take();
                MainHandler handler = Global.handlerHashMap.get(packMessage.getMessageType());
                handler.handlerMessage(packMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
