package com.ggs.one;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

/**
 * @Author lianghaohui
 * @Date 2022/3/20 14:05
 * @Description 客户端
 */
public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9999);
        OutputStream os = socket.getOutputStream();
        PrintStream ps = new PrintStream(os);
        ps.print("Hello BIO!!!!");
        ps.flush();
    }

}
