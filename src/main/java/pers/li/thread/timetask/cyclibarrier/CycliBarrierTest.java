package pers.li.thread.timetask.cyclibarrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程工具类之   集合-分散-集合
 * @author lishengbo
 * @Time   2017年12月3日下午8:42:07
 *
 */
public class CycliBarrierTest {

	/**
	 * 假設三個人執行任務，执行完后集合...
	 * @Time 2017年12月3日下午8:47:55
	 *
	 */
	public static void main(String[] args) {
		ExecutorService service=Executors.newCachedThreadPool();
		final CyclicBarrier cb=new 	CyclicBarrier(3);
		//十个任务放池中，产生十个线程
		for (int i = 0; i < 3; i++) {
			Runnable runnable=new Runnable() {

				@Override
				public void run() {
					try {
						Thread.sleep((long) (Math.random()*10000));
//						Thread.sleep((long) (1000));
						System.out.println("线程"+Thread.currentThread().getName()+"进入进入第一次任务,当前已有"
								+(cb.getNumberWaiting()+1)+"集合");
						cb.await();//当三个线程都到集合点时，继续向下执行
						Thread.sleep((long) (Math.random()*10000));
//						Thread.sleep((long) (1000));

						System.out.println("线程"+Thread.currentThread().getName()+"进入进入第2次任务,当前已有"
								+(cb.getNumberWaiting()+1)+"集合");
						cb.await();
						Thread.sleep((long) (Math.random()*10000));
//						Thread.sleep((long) (1000));

						System.out.println("线程"+Thread.currentThread().getName()+"进入进入第3次任务,当前已有"
								+(cb.getNumberWaiting()+1)+"集合");
						cb.await();
						System.err.println("任务结束");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			};
			service.execute(runnable);
		}
		
	}

}
