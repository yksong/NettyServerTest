package cn.test;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Administrator on 2016/11/30 0030.
 */
public class RemoteSocketConn {

    public static void main(String argc[]){
        String ipAddress = "192.168.1.117";
        int port = 10086;
        try {
            Socket socket = new Socket(ipAddress, port);
            System.out.println("连接到服务器！");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
