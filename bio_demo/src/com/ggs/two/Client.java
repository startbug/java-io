package com.ggs.two;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

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
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("输入:");
            String msg = sc.nextLine();
            ps.println(msg);    //记得使用println，如果发的不是一行数据，服务端会阻塞
            ps.flush();
        }
    }

}
