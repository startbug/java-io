package com.ggs.five;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author lianghaohui
 * @Date 2022/3/20 15:46
 * @Description 接受客户端任意类型文件，并保存到服务端
 */
public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                new ServerReaderThread(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
