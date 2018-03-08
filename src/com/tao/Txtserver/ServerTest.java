package com.tao.Txtserver;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerTest {
    int port = 8821;

    public void start() {
        Socket s = null;
        try {
            ServerSocket ss = new ServerSocket(port);
            while (true) {
                // 选择进行传输的文件
                String filePath = "D://Heart//2016-11-15.txt";
                File fi = new File(filePath);
                System.out.println("文件长度:" + (int) fi.length());
                System.out.println("文件名称长度:" + fi.getName().length());
                System.out.println("文件名称:" + fi.getName());

                // public Socket accept() throws
                // IOException侦听并接受到此套接字的连接。此方法在进行连接之前一直阻塞。

                s = ss.accept();
                System.out.println("建立socket链接");
                //DataInputStream dis = new DataInputStream(new BufferedInputStream(s.getInputStream()));
                //dis.readByte();

                DataInputStream fis = new DataInputStream(new BufferedInputStream(new FileInputStream(filePath)));
                DataOutputStream ps = new DataOutputStream(s.getOutputStream());
                // 将文件名及长度传给客户端。这里要真正适用所有平台，例如中文名的处理，还需要加工，具体可以参见Think In Java
                // 4th里有现成的代码。
                // class protoHeader = new ProtoHeader();
                // protoheader.type = 1;
                // protoheader.plen = 1231;
                // byte headerAraay = getByteArray(protoheader);// 协议头
                // ps.wirte(headerAraay);
                // ps.write(包体);
//                String test = "asdf";
//                byte array[] = test.getBytes();


                //ps.writeLong((long) fi.length());
                //ps.flush();
               // ps.writeLong(fi.getName().length());
                //ps.flush();
                ps.write(fi.getName().getBytes());
                //ps.writeUTF("\r\n");
                //ps.flush();


                int bufferSize = 8192;
                byte[] buf = new byte[bufferSize];

                while (true) {
                    System.out.println("文件传输开始");
                    int read = 0;
                    if (fis != null) {
                        read = fis.read(buf);
                    }

                    if (read == -1) {
                        break;
                    }
                    ps.write(buf, 0, read);
                }
                ps.flush();
                // 注意关闭socket链接哦，不然客户端会等待server的数据过来，
                // 直到socket超时，导致数据不完整。
                fis.close();
                s.close();
                System.out.println("文件传输完成");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String arg[]) {
        new ServerTest().start();
    }
}
