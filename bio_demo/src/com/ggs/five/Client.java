package com.ggs.five;

import java.io.*;
import java.net.Socket;

/**
 * @Author lianghaohui
 * @Date 2022/3/20 15:39
 * @Description 客户端上传任意文件类的文件数据
 */
public class Client {

    public static void main(String[] args) {
        try (
                //把文件数据发送到接收端
                FileInputStream fis = new FileInputStream("bio_demo/img/yileina.jpg")
        ) {
            Socket socket = new Socket("127.0.0.1", 8888);
            //这种输出流可以分段发送数据，例如可以先发送字符串(文件类型)，再发送文件数据
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            //先发送文件类型给服务器
            dos.writeUTF(".jpg");
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fis.read(bytes)) != -1) {
                dos.write(bytes, 0, len);
            }
            dos.flush();
            socket.shutdownOutput();    //告诉服务端输出完成，否则服务端会一直等待，客户端线程结束，服务端就会抛出异常，文件上传就失败了
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
