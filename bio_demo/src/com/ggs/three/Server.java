package com.ggs.three;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author lianghaohui
 * @Date 2022/3/20 14:30
 * @Description 实现服务端可以接收多个客户端的Socket通信请求
 * 服务端每接收到一个客户端socket请求对象之后都交给一个独立的 线程来处理客户端的数据交互需求
 *
 * 小结：
 * 1.每个接收到一个Socket都需要创建一个线程，线程的竞争和上下文切换影响性能
 * 2.每个线程都会占用栈空间和CPU资源
 * 3.并不是每个socket都进行IO操作，无意义的线程处理
 * 4.客户端的并发访问增加时，服务端将呈现1：1的线程开销，访问量越大，系统将发生线程栈溢出，
 * 线程创建失败，最终导致进程宕机或僵死，从而不能对外提供服务
 */
public class Server {

    public static void main(String[] args) throws IOException {
        //1.注册端口
        ServerSocket serverSocket = new ServerSocket(9999);
        //2.定义一个死循环不断的接收客户端的Socket创建请求
        while (true) {
            //这里一旦获得一个socket就代表连接上了一个客户端
            Socket socket = serverSocket.accept();
            //3.创建一个独立的线程来处理这个客户端的socket通信需求
            new ServerThreadReader(socket).start();
        }
    }

}
