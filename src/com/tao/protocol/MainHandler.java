package com.tao.protocol;

/**
 * Created by DXS on 2017/3/29.
 */
public class MainHandler {
    public void handlerMessage(PackMessage packMessage){
        if(!isLegalMessage(packMessage)){
            System.out.print("消息不合法");
            return;
        }
    }

    public boolean isLegalMessage(PackMessage packMessage){
        //System.out.println("判断消息是否合法");
        return true;
    }
}
