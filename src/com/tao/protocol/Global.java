package com.tao.protocol;

import java.util.HashMap;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by DXS on 2017/3/29.
 */
public class Global {
    public static HashMap<Integer, MainHandler> handlerHashMap = new HashMap<>();
    public static BlockingQueue<PackMessage> messageQueue = new LinkedBlockingQueue<>();
    public static void init(){
        handlerHashMap.put(0, new InitialDataHandler());
        handlerHashMap.put(1, new ConfirmInfoHandler());
    }
}
