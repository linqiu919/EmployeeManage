package com.java.sm.util;

import java.sql.Time;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 722A-08-CXB
 * @version 1.0.0
 * @ClassName AsyncManage.java
 * @DescriPtion 异步线程池的编写
 * @CreateTime 2021年06月19日 17:13:00
 */
public class AsyncThreadPool {
    public static ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    private static AsyncThreadPool asyncThreadPool;
    private AsyncThreadPool(){
        scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(50);
    }

    public static AsyncThreadPool getInstance(){
        if(asyncThreadPool==null){
            return  new AsyncThreadPool();
        }
        return asyncThreadPool;
    }
    /**
     * 使用线程池执行内容（没有延时时间）
     */
    public void execute(Runnable runnable){
        scheduledThreadPoolExecutor.execute(runnable);
    }

    /**
     * 使用线程池执行内容（有延迟时间）
     */
//    public void schedule(Runnable runnable,long seconds){
//        scheduledThreadPoolExecutor.execute(runnable,seconds,TimeUnit.SECONDS);
//    }

    /**
     * 关闭线程池
     */
    public void release(){
        scheduledThreadPoolExecutor.shutdownNow();
    }

}
