package com.ggs.four;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Author lianghaohui
 * @Date 2022/3/20 15:11
 * @Description
 */
public class ServerRunnableTarget implements Runnable{

    private Socket socket;

    public ServerRunnableTarget(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            //处理接收到的socket
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String msg;
            while((msg=br.readLine())!=null){
                System.out.println("接收消息:"+msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
