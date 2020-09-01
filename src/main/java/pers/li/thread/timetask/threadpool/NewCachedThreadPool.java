package pers.li.thread.timetask.threadpool;

/**
 * newCachedThreadPool
 * 创建一个可缓存线程池，应用中存在的线程数可以无限大
 *
 */
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by Alivn on 2017/3/19.
 */
public class NewCachedThreadPool {

    /**
     * 线程数无限制
     * 
     * 我们获取四次次线程，观察4个线程地址
     * 输出结果是：可以有无限大的线程数进来（线程地址不一样）
     * @param args
     * 
     * ****************************newCachedThreadPool*******************************
     * 开始处理线程！！！1494846768489
     * 开始处理线程！！！1494846768489
     * 开始处理线程！！！1494846768489
     * 开始处理线程！！！1494846768489
     * 我的线程标识是：Executors.ThreadForpools@4130fafb  
     * 我的线程标识是：Executors.ThreadForpools@486f8860
     * 我的线程标识是：Executors.ThreadForpools@12627bc7
     * 我的线程标识是：Executors.ThreadForpools@7fac4f3e
     * 
     */
    public static  void main(String[]args)
    {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        System.out.println("****************************newCachedThreadPool*******************************");
        for(int i=0;i<4;i++)
        {
          final int index=i;
          newCachedThreadPool.execute(new ThreadForpools(index));
        }
    }
}
