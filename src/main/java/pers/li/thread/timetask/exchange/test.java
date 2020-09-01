package pers.li.thread.timetask.exchange;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
/**
 * 把主线程中的数据换成子线程数据
 * @author lishengbo
 * @Time   2017年12月3日下午11:01:22
 *
 */
public class test {
	public static void main(String[] args) throws InterruptedException {
		final Exchanger< String> ex=new Exchanger<String>();
		final CountDownLatch start=new 	CountDownLatch(1);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					start.await();//等通知中--
					String das="123";
					String data2=ex.exchange(das);
					System.out.println(data2);
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
		
		String data="kkkk";
		
		
		start.countDown();//通知线程启动
		String data1=ex.exchange(data);	
		System.out.println(Thread.currentThread().getName()+data1);


	}
	
}
