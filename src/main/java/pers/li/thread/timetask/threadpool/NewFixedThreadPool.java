package pers.li.thread.timetask.threadpool;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * newFixedThreadPool
 * 创建一个定长线程池，可控制线程最大并发数，
 * 超出的线程会在队列中等待。示例代码如下：
 */
public class NewFixedThreadPool {

	/**
	 * 定长执行
	 * 
	 * 我们获取四次次线程，观察4个线程地址
	 * 输出结果：每次只有两个线程在处理，当第一个线程执行完毕后，新的线程进来开始处理（线程地址不一样）
	 * @param args
	 */
	/**
	 *****************************newFixedThreadPool*******************************
	 *开始处理线程！！！1494846988333
	 *开始处理线程！！！1494846988333
	 *我的线程标识是：Executors.ThreadForpools@41616dd6
	 *开始处理线程！！！1494846988333
	 *我的线程标识是：Executors.ThreadForpools@63721e22
	 *开始处理线程！！！1494846988442
	 *我的线程标识是：Executors.ThreadForpools@59187d2f
	 *我的线程标识是：Executors.ThreadForpools@4439bc82
	 */
	public static void main(String[] args) {
		// 线程池允许同时存在两个线程
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(2);
		System.out
				.println("****************************newFixedThreadPool*******************************");
		for (int i = 0; i < 4; i++) {
			final int index = i;
			newFixedThreadPool.execute(new ThreadForpools(index));
		}
	}
}
