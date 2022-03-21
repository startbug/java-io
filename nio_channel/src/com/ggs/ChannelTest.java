package com.ggs;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

/**
 * @Author lianghaohui
 * @Date 2022/3/21 22:50
 * @Description
 */
public class ChannelTest {

    @Test
    public void test02() throws IOException {
        FileInputStream fis = new FileInputStream("data01.txt");
        FileOutputStream fos = new FileOutputStream("data03.txt");
        //1.获取通道
        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();
        //2.从一个通道复制到另一个通道
//        fisChannel.transferTo(fisChannel.position(), fisChannel.size(), fosChannel);
        fosChannel.transferFrom(fisChannel, fisChannel.position(), fisChannel.size());
        fisChannel.close();
        fosChannel.close();
    }

    @Test
    public void test() throws Exception {
        //1.字节输入管道
        FileInputStream fis = new FileInputStream("data01.txt");
        FileOutputStream fos = new FileOutputStream("data02.txt");
        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();
        //3.定义多个缓冲区做数据分散
        ByteBuffer buffer1 = ByteBuffer.allocate(4);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        ByteBuffer[] buffers = {buffer1, buffer2};
        //4.将从通道中读取的数据分散到各个缓冲区中
        fisChannel.read(buffers);
        //5.从每个缓冲区中查询是否有数据读取到了
        for (ByteBuffer buffer : buffers) {
            buffer.flip();
            System.out.println(new String(buffer.array(), 0, buffer.remaining()));
        }
        //6.聚集写入到通道
        fosChannel.write(buffers);
        fisChannel.close();
        fosChannel.close();
    }

    @Test
    public void copy() throws IOException {
        FileInputStream fis = new FileInputStream("img/Anna.jpg");
        FileOutputStream fos = new FileOutputStream("img/output/Anna_output.jpg");
        //得到输入输出流的channel
        FileChannel fisChannel = fis.getChannel();
        FileChannel fosChannel = fos.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        //分配缓冲区
        while (true) {
            //开始读取一次数据
            int flag = fisChannel.read(buffer);
            if (flag == -1) {
                break;
            }
            //切换成读模式(从缓存中读取，写到磁盘上)
            buffer.flip();
            fosChannel.write(buffer);
            buffer.clear();
        }
        fis.close();
        fos.close();
        System.out.println("复制完成!");
    }

    @Test
    public void read() {
        //读取文件
        try {
            FileInputStream fis = new FileInputStream("data01.txt");
            FileChannel channel = fis.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            channel.read(buffer);
            buffer.flip();
            //buffer.remaining()剩余可读长度
            System.out.println(new String(buffer.array(), 0, buffer.remaining()));
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void write() {
        try {
            //1.字节输出流通向目标文件
            FileOutputStream os = new FileOutputStream("data01.txt");
            //2.得到字节输出流对应的通道Channel
            FileChannel channel = os.getChannel();
            //3.分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello,Starbug,with you forever!".getBytes(StandardCharsets.UTF_8));
            buffer.flip();  //切换成写模式
            channel.write(buffer);
            channel.close();
            System.out.println("写出成功!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
