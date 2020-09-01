package pers.li.thread.timetask.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池介绍Execution
 * @author lishengbo
 * @Time 2017年12月2日下午12:37:03
 */
public class newFixedThreadPoolTest {

	public static void main(String[] args) {
		/**创建线程池,包含3个线程 ****/
		ExecutorService threadPool = Executors.newFixedThreadPool(3);
		/**三个线程用来分配10个任务**/
		for (int j = 0; j < 10; j++) {
			final int task=j;
		threadPool.execute(new Runnable() {
			@Override
			public void run() {
//				for (int i = 0; i < 10; i++) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+"第"+(task+1)+"次任务");
//					System.out.println(Thread.currentThread().getName()+"正在循坏第几次："+(i+1)+"|||第"+(task+1)+"次任务");
//				}
			}
		});
	}
		System.err.println("执行完成，但是至此程序未结束---线程处于等待状态，未被唤醒而已");
		System.err.println("关闭线程池：threadPool.shutdown()");
		threadPool.shutdown();   //等任务执行完事，关闭线程池
//		threadPool.shutdownNow();//不等执行完，就关闭线程池
	}
}
