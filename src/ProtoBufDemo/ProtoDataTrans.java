package ProtoBufDemo;

import com.google.protobuf.ByteString;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by DXS on 2017/3/27.
 */
public class ProtoDataTrans {
    public static Socket socket;
    public static void main(String[] args) throws IOException{
        DataMsg.Data.Builder dataBuilder = DataMsg.Data.newBuilder();
        dataBuilder.setFrame(1);
        dataBuilder.setIsDrawed(false);
        dataBuilder.addDatas(2081);
        dataBuilder.addDatas(2076);
        dataBuilder.addDatas(1987);
        DataMsg.Data data = dataBuilder.build();
        String string = "message";
        try{
            ServerSocket serverSocket = new ServerSocket(10010);
            socket = serverSocket.accept();
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            data.writeTo(byteArrayOutputStream);
            byteArrayOutputStream.writeTo(dataOutputStream);
            dataOutputStream.close();
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
