package pers.li.thread.timetask.endmianshi;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.SynchronousQueue;

/**
 * 生产者-消费者
 * 生产十条数据，10个消费者按顺序消费，每次消费耗时1秒
 * @author lishengbo
 * @Time   2017年12月9日下午11:06:38
 *
 */
public class maianshiTest2 {

	/**
	 * 此种方式可互斥，但是并不能按顺序消费--线程池方式
	 * @Time 2017年12月9日下午11:34:18
	 *
	 */
	public static void main2(String[] args) {
		ExecutorService ser=Executors.newFixedThreadPool(10);
		final Semaphore sp=new 	Semaphore(1,true);//true保证顺序执行 
		System.out.println("begin:"+System.currentTimeMillis()/1000);
		for (int i = 0; i <10; i++) {//不准改动
			String in=i+"";//不准改动
			final String t=in;
			ser.execute(new Runnable() {
				@Override
				public void run() {
					try {
						sp.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
						String out=doex.dosome(t);
						System.out.println("|"+out);
						sp.release();
				}
			});
		}
		
	}
	/**
	 * 队列方式
	 * @throws InterruptedException 
	 * @Time 2017年12月9日下午11:34:18
	 *
	 */
	public static void main(String[] args) throws InterruptedException {
		/**
		 * 读写互斥，放十个同时取出
		 */
		final SynchronousQueue<String> que=new SynchronousQueue<String>();
		/**
		 * 保证顺序执行
		 */
		final Semaphore sp=new 	Semaphore(1,true);//true保证顺序执行 
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					String in = null;
					try {
						sp.acquire();
						in = que.take();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					String out=doex.dosome(in);
					System.out.println(Thread.currentThread().getName()+"|"+out);
					sp.release();
				}
			}).start();
		}
		System.out.println("begin:"+System.currentTimeMillis()/1000);
		for (int i = 0; i <10; i++) {//不准改动
			String in=i+"";//不准改动
			que.put(in);
//					String out=doex.dosome(in);
//					System.out.println("|"+out);
		}
		
	}
	/**
	 * 原题
	 * @Time 2017年12月9日下午11:14:40
	 *
	 */
	public static void main4(String[] args) {
		System.out.println("begin:"+System.currentTimeMillis()/1000);
		for (int i = 0; i <10; i++) {//不准改动
			String in=i+"";//不准改动
			String out=doex.dosome(in);
			System.out.println(out);
		}
		
	}
	
}

class doex{//此类不准改
	public static String dosome(String in){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String out=in+":"+(System.currentTimeMillis()/1000);
		return out;
	}
}
