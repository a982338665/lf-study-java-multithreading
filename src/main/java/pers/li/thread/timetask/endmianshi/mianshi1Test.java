package pers.li.thread.timetask.endmianshi;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 日志打印--面试题:
 * 16条日志--打印16秒
 * 改为
 * 四条线程--4秒打印完成
 * @author lishengbo
 * @Time   2017年12月9日下午10:10:16
 *
 */
public class mianshi1Test {

	/**
	 * 线程池写法
	 * @Time 2017年12月9日下午10:34:02
	 *
	 */
	public static void main1(String[] args) throws InterruptedException {
		ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(4);
		for (int i = 0; i < 16; i++) {
			final String log="第几条日志"+(i+1);
			newFixedThreadPool.execute(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						mianshi1Test.paselog(Thread.currentThread().getName()+"|"+log);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}
		newFixedThreadPool.shutdown();
	}
	/**
	 * 队列写法
	 * @Time 2017年12月9日下午10:34:18
	 *
	 */
	public static void main(String[] args) throws InterruptedException {
		final BlockingQueue<String> duilie=new ArrayBlockingQueue<String>(16);//此处为1也可以，不会阻塞
		for (int i = 0; i < 4; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						try {
							paselog(duilie.take());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}// TODO Auto-generated method stub
					
				}
			}).start();
		}
		for (int i = 0; i < 16; i++) {
			final String log="第几条日志"+(i+1);
			duilie.put(Thread.currentThread().getName()+"|"+log);
//				mianshi1Test.paselog(Thread.currentThread().getName()+"|"+log);
		}
	}

	private static void paselog(String log) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println(log+":"+System.currentTimeMillis());
		Thread.sleep(1000);
	}
/*	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < 16; i++) {
			final String log="第几条日志"+(i+1);
			mianshi1Test.paselog(log);
		}
	}
	
	private static void paselog(String log) throws InterruptedException {
		// TODO Auto-generated method stub
		System.out.println(log);
		Thread.sleep(1000);
	}
*/}
