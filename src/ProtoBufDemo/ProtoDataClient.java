package ProtoBufDemo;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

/**
 * Created by DXS on 2017/3/27.
 */
public class ProtoDataClient {
    public static Socket socket;
    public static void main(String[] args){
        try{
            socket = new Socket("127.0.0.1", 10010);
            byte[] data = new byte[1024];
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            InputStream inputStream = socket.getInputStream();
            int length = -1;
            while ((length = inputStream.read(data)) != -1)
            {
                output.write(data, 0, length);
            }
            System.out.println(new String(output.toByteArray()));

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
