package com.ggs;

import org.junit.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @Author lianghaohui
 * @Date 2022/3/21 22:10
 * @Description Buffer的API演示
 * 直接内存：非堆内存，IO操作上有更高的性能，因为是直接作用于本地系统的IO操作，直接内存申请空间效率较慢
 * 非直接内存：而非直接内存，也就是堆内存，如果做IO操作，会先进行本进程内存复制到直接内存，再进行IO处理
 */
public class BufferTest {

    @Test
    public void test03() {
        //1.创建一个直接内存
//        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);  //申请获取1KB直接内存(非堆内存)
        ByteBuffer buffer = ByteBuffer.allocate(1024);  //申请获取1KB非直接内存(堆内存)
        System.out.println(buffer.isDirect());
    }

    @Test
    public void test02() {
        //1.分配一个缓冲区，容量设置为10
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());      // 0
        System.out.println(buffer.limit());         // 10
        System.out.println(buffer.capacity());      // 10

        System.out.println("-------------------------------------");
        //2.put往缓冲区中添加数据
        String name = "Starbug";
        buffer.put(name.getBytes(StandardCharsets.UTF_8));
        System.out.println(buffer.position());      // 7
        System.out.println(buffer.limit());         // 10
        System.out.println(buffer.capacity());      // 10

        System.out.println("-------------------------------------");
        //3.clear清楚缓冲区的数据
        buffer.clear(); //将position设置为0，但是并不会清除数据，添加数据覆盖了才会清除掉数据
        System.out.println(buffer.position());      // 0
        System.out.println(buffer.limit());         // 10
        System.out.println(buffer.capacity());      // 10
        System.out.println((char) buffer.get());      // 10


        //定义缓冲区
        ByteBuffer buffer1 = ByteBuffer.allocate(10);
        String n = "Starbug";
        buffer1.put(n.getBytes(StandardCharsets.UTF_8));

        buffer1.flip();

        //读取数据
        byte[] b = new byte[2];
        buffer1.get(b);
        String result = new String(b);
        System.out.println(result);
        System.out.println(buffer1.position());
        System.out.println(buffer1.limit());
        System.out.println(buffer1.capacity());
        System.out.println("-------------------------------------");

        //标记当前位置
        buffer1.mark(); //2

        byte[] b2 = new byte[3];
        buffer1.get(b2);
        System.out.println(new String(b2));
        System.out.println(buffer1.position());
        System.out.println(buffer1.limit());
        System.out.println(buffer1.capacity());

        System.out.println("-------------------------------------");
        //回到标记位置
        buffer1.reset();
        if (buffer1.hasRemaining()) {
            System.out.println("剩余数据(字节数):" + buffer1.remaining());
        }

    }

    @Test
    public void test01() {
        //1.分配一个缓冲区，容量设置为10
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position());      // 0
        System.out.println(buffer.limit());         // 10
        System.out.println(buffer.capacity());      // 10

        System.out.println("-------------------------------------");
        //2.put往缓冲区中添加数据
        String name = "Starbug";
        buffer.put(name.getBytes(StandardCharsets.UTF_8));
        System.out.println(buffer.position());      // 7
        System.out.println(buffer.limit());         // 10
        System.out.println(buffer.capacity());      // 10

        System.out.println("-------------------------------------");
        //3.切换成可读模式，将缓冲区界限设置为当前位置，将当前位置设置为0
        buffer.flip();
        System.out.println(buffer.position());      // 0
        System.out.println(buffer.limit());         // 7
        System.out.println(buffer.capacity());      // 10

        System.out.println("-------------------------------------");
        //4.get数据的读取
        char ch = (char) buffer.get();
        System.out.println(ch);
        System.out.println(buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

    }

}
