package pers.li.thread.timetask.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 计数线程：线程准备(n)--发布命令一起执行--等待都执行完--报告结果
 * 处于准备状态，待主线程开始调用时，开始处理hou
 * @author lishengbo
 * @Time   2017年12月3日下午9:15:05
 *
 */
public class CountDownLatchTest {

	public static void main(String[] args) {

		ExecutorService service=Executors.newCachedThreadPool();
		final CountDownLatch start=new 	CountDownLatch(1);
		final CountDownLatch end=new 	CountDownLatch(3);
		//十个任务放池中，产生十个线程
		for (int i = 0; i < 3; i++) {
			Runnable runnable=new Runnable() {

				@Override
				public void run() {
					try {
						/**第一步：for循环中初始化3个线程并执行此命令**/
						System.out.println("线程准备接受命令："+Thread.currentThread().getName());
						/**第二步：CountDownLatch对象start开始等待计数（构造方法countDown一次后，变为零即向下执行）**/
						start.await();
						/**并行第五步：子线程等待失效，继续向下执行，接受命令**/
						System.out.println("线程已经接受命令：--"+Thread.currentThread().getName());
						Thread.sleep((long) (Math.random()*10000));
						System.out.println("回应命令处理结果");
						/**三个线程接收到任务，每个任务经过时countDown-1,3次之后变为0，此时第五步结束，end.await()失效**/
						end.countDown();
						System.out.println("---");
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			};
			service.execute(runnable);
		}
		/**主线程任务***/
		try {
				Thread.sleep((long) (Math.random()*10000));
			/**第三步：所有子线程countDown(start)等待阻塞过程中，主线程继续执行**/
			System.out.println("命令即将发布："+Thread.currentThread().getName());
			/**第四步：countDown-1为零时，通知阻塞的子线程继续执行**/
			start.countDown();
			System.out.println("命令已发送，等待结果："+Thread.currentThread().getName());
			/**第五步：countDown(end=3),当countDown3次之后，该等待失效继续向下执行**/
			end.await();
			/**第六步：计数3次结束，继续向下执行**/
			System.out.println("已收到所有响应结果"+Thread.currentThread().getName());
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		service.shutdown();
	}
}
