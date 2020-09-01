package pers.li.thread.timetask.threadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时，定长执行
 * 
 * newScheduledThreadPool
 * 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
 * 可以用来执行定时任务
 */
public class NewScheduledThreadPool {


    /**
     * 我们获取四次次线程，观察4个线程地址
     * @param args
     */
	/**
	 * 执行结果：延迟三秒之后执行，除了延迟执行之外和newFixedThreadPool基本相同，可以用来执行定时任务
	 * @param args
	 */
    public static  void main(String[]args)
    {
//        ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(2);
        System.out.println("****************************newFixedThreadPool*******************************");
//        for(int i=0;i<4;i++)
//        {
//            final int index=i;
//            //延迟三秒执行
//            newScheduledThreadPool.schedule(new ThreadForpools(index),3, TimeUnit.SECONDS);
//        }
        
        /**3秒以后开始执行，之后每秒执行一次，以下事例中仅有一个线程*/
        Executors.newScheduledThreadPool(2).scheduleAtFixedRate(new ThreadForpools(3),3,1,TimeUnit.SECONDS);
        
    }
}
