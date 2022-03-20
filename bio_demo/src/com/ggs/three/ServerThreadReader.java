package com.ggs.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * @Author lianghaohui
 * @Date 2022/3/20 14:37
 * @Description
 */
public class ServerThreadReader extends Thread {

    private Socket socket;

    public ServerThreadReader(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InputStream is = socket.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg;
            while ((msg = br.readLine()) != null) {
                System.out.println("接受消息:" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
