package com.ggs.four;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author lianghaohui
 * @Date 2022/3/20 15:04
 * @Description
 */
public class HandlerSocketServerPool {

    //1.创建一个线程池成员变量
    private ExecutorService executorService;

    /**
     * 2.创建这个类的对象得到时候需要初始化线程池对象
     * public ThreadPoolExecutor(int corePoolSize, 核心线程，长久保存在线程池中，不会销毁
     * int maximumPoolSize,   线程最大数： 其他工作线程数=线程最大数-核心线程数
     * long keepAliveTime,    线程最多空闲时间： 其他工作线程空闲时间超过该设置，就会被销毁
     * TimeUnit unit,         时间单位
     * BlockingQueue<Runnable> workQueue)   任务队列，当核心线程池不够用，就会将任务放到队列中等待，如果队列满了，就会创建其他工作线程进行处理，如果处理其他工作线程也满了，默认就会抛出异常并丢弃任务
     */
    public HandlerSocketServerPool(int maxThreadNum, int queueSize) {
        executorService = new ThreadPoolExecutor(3, maxThreadNum, 120, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    /**
     * 3.提供一个方法提交任务给线程池的队列任务来暂存，等着线程池来处理
     */
    public void execute(Runnable target) {
        executorService.submit(target);
    }


}
