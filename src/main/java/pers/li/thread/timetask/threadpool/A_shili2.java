package pers.li.thread.timetask.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Runnable实现-------与Thread相似
 * @author lishengbo
 * @Time 2017年12月2日下午1:57:56
 */
public class A_shili2 implements Runnable{

	@Override
	public void run() {
		/**此任务一直会循坏检测执行-若以下条件满足才继续进行----*/
		while(true){
			System.out.println("开始执行子线程业务=============");
			ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(5);
			System.out.println("使用线程池开始任务分配：");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			/***此处可设置执行条件，若不满足，则等待****/
			if(true){
				/***
				 * 用五个线程去接十个任务，先5个，后谁等待谁接
				 */
				for (int i = 0; i < 10; i++) {
					newFixedThreadPool.execute(new A_());
				}
				
				for (int i = 0; i < 10; i++) {
					newFixedThreadPool.execute(new Runnable() {
						@Override
						public void run() {
							System.err.println(Thread.currentThread().getName()+"|||需要执行的业务逻辑------");
							
						}
					});
				}
			}
		}
	}

}
