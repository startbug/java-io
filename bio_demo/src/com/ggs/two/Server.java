package com.ggs.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author lianghaohui
 * @Date 2022/3/20 14:05
 * @Description 服务端
 * while循环，一直接受客户端发送的的消息
 * 但是有个问题，目前这种实现方式只能接受一个客户端的消息，不能接收多个客户端，可以开多个Client进行测试验证
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        InputStream is = socket.getInputStream();
        BufferedReader bis = new BufferedReader(new InputStreamReader(is));
        String msg;
        //如果客户端发送的数据不是一行数据(有换行),并且已经挂掉了，那么就会抛出异常Exception in thread "main" java.net.SocketException: Connection reset
        while ((msg = bis.readLine()) != null) {
            System.out.println("接收到消息:" + msg);
        }
    }

}
