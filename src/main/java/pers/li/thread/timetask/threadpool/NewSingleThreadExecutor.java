package pers.li.thread.timetask.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单线程化，顺序执行（一个线程执行一条任务）
 * 该线程会永远保持，有一个线程，即使线程死掉，也会重新创建一个线程
 * newSingleThreadExecutor
 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：
 */
public class NewSingleThreadExecutor {



    /**
     * 我们获取四次次线程，观察4个线程地址
     * @param args
     */
    public static  void main(String[]args)
    {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        System.out.println("****************************newFixedThreadPool*******************************");
        for(int i=0;i<4;i++)
        {
            final int index=i;
            newSingleThreadExecutor.execute(new ThreadForpools(index));
        }
    }
}
