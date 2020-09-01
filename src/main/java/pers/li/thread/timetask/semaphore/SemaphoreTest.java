package pers.li.thread.timetask.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * semphore--用于控制并发线程个数
 * 可以控制当前访问自身线程的个数，并提供同步机制
 * (实现信号灯)
 * 控制同时访问资源的线程个数，实现一个文件允许并发的访问数
 * 
 * 例：
 * 一个接口，最多控制为10个线程可以同时访问，进入接口的线程可以是随机的，也可以是按先后顺序的，这取决于构造此对象
 * 时传入的参数
 * 
 * @author lishengbo
 * @Time   2017年12月3日下午3:55:36
 *
 *	******单个信号量的Semphore对象可以实现互斥锁的功能，并且可由一个线程获得锁
 *再由另一个线程释放锁，可以解决死锁的问题
 */
public class SemaphoreTest {

	public static void main(String[] args) {
		ExecutorService service=Executors.newCachedThreadPool();
		final Semaphore sp=new 	Semaphore(3,true);//true保证顺序执行
//		final Semaphore sp=new 	Semaphore(3);
		//十个任务放池中，产生十个线程
		for (int i = 0; i < 10; i++) {
			Runnable runnable=new Runnable() {
				public void run() {
					try {
						sp.acquire();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("线程"+Thread.currentThread().getName()+"进入,当前已有"
					+(3-sp.availablePermits())+"个并发");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("线程"+Thread.currentThread().getName()+"即将离开");
					sp.release();
					System.out.println("线程"+Thread.currentThread().getName()+"yijinglikai"
					+",当前已有"
					+(3-sp.availablePermits())+"个并发"
							);
				}
			};
			service.execute(runnable);
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
