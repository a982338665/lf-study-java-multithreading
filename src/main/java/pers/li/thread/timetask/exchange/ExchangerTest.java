package pers.li.thread.timetask.exchange;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程之间的数据交换:两个数据之间，若有其中一个数据未查出则等待
 * @author lishengbo
 * @Time   2017年12月3日下午10:39:41
 *
 */
public class ExchangerTest {


	public static void main(String[] args) {

		ExecutorService service=Executors.newCachedThreadPool();
		final Exchanger start=new 	Exchanger();
		/*****一个任务分配给线程池*******/
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				String data="lishengbo";
				try {
					Thread.sleep((long) (Math.random()*10000));
					String datachange=(String)start.exchange(data);
					System.err.println("lishengbo"+datachange);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		service.execute(new Runnable() {
			
			@Override
			public void run() {
				String data="龙";
				try {
					Thread.sleep((long) (Math.random()*10000));
					String datachange=(String)start.exchange(data);
					System.out.println(datachange);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		
		
		service.shutdown();
	}

}
