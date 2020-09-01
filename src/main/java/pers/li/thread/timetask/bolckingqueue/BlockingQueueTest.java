package pers.li.thread.timetask.bolckingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 可阻塞队列：
 * 
 * @author lishengbo
 * @Time   2017年12月3日下午11:41:42
 *
 */
public class BlockingQueueTest {
	public static void main(String[] args) {
		/**可存放三个数据的可阻塞队列**/
		final BlockingQueue<Integer> block=new ArrayBlockingQueue<Integer>(3);
		for (int i = 0; i < 2; i++) {
			/**两个线程去存值**/
			new Thread(){
				public void run() {
					while(true){
						try {
							Thread.sleep((long) (Math.random()*1000));
							System.out.println(Thread.currentThread().getName() +" ready to add data");
							block.put(1);
							System.out.println(Thread.currentThread().getName() +" already exist data:---"+block.size());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch blockz
							e.printStackTrace();
						}
					}
				};
			}.start();
			/**两个线程去取值**/
			new Thread(){
				public void run() {
					while(true){
						try {
							/**
							 * 取值速度过快时，将永远无法填埋队列
							 * 取值速度过慢时，将会阻塞队列，当值取出时，立刻put进去
							 * sleep=100/1000为例
							 */
							Thread.sleep(100);
							System.out.println(Thread.currentThread().getName() +"take data");
							block.take();
							System.out.println(Thread.currentThread().getName() +"take already exist data:==="+block.size());
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				};
			}.start();
		}
	}
	
}
