package com.ggs.five;

import java.io.DataInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * @Author lianghaohui
 * @Date 2022/3/20 15:47
 * @Description
 */
public class ServerReaderThread extends Thread {

    private Socket socket;

    public ServerReaderThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        OutputStream os = null;
        try {
            //1.得到数据输入流
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            //2.读取文件类型
            String suffix = dis.readUTF();
            System.out.println("服务端接收到文件类型:"+suffix);
            //3.写出文件
            os = new FileOutputStream("bio_demo/output/" + UUID.randomUUID() + suffix);
            //4.从输入流中读取文件数据
            byte[] bytes = new byte[1024];
            int len;
            while ((len = dis.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            System.out.println("接收文件，保存成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
