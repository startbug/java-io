package com.ggs.four;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author lianghaohui
 * @Date 2022/3/20 15:03
 * @Description 伪异步通信架构
 * 小结：
 * 1.伪异步IO采用了线程池实现，因此避免了为每一个请求创建一个独立线程造成线程资源耗尽的问题，但是由于
 * 底层依然采用同步阻塞模型，因此无法从根本上解决问题
 * 2.如果单个消息对象处理的缓慢，或者服务器线程池中全部的线程都被阻塞，那么后续socket的io消息都将在队列中
 * 排队，新的Socket请求将会被拒绝，客户端发生大量连接超时。
 */
public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            //创建线程池
            HandlerSocketServerPool handlerSocketServerPool = new HandlerSocketServerPool(5, 2);
            while (true) {
                Socket socket = serverSocket.accept();
                Runnable runnable = new ServerRunnableTarget(socket);
                handlerSocketServerPool.execute(runnable);
            }
        }
    }

}
