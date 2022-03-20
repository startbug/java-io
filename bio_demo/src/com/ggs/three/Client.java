package com.ggs.three;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @Author lianghaohui
 * @Date 2022/3/20 14:40
 * @Description
 * 测试：开多个Client进行测试
 */
public class Client {

    public static void main(String[] args) {
        try {
            //1.请求与服务端的Socket对象链接
            Socket socket = new Socket("127.0.0.1", 9999);
            //2.得到一个打印流
            PrintStream pr = new PrintStream(socket.getOutputStream());
            //3.不断发送消息给服务端
            Scanner sc = new Scanner(System.in);
            while (true) {
                System.out.print("请说:");
                String msg = sc.nextLine();
                pr.println(msg);
                pr.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
