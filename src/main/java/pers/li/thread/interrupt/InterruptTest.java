package pers.li.thread.interrupt;

/**
 * 线程1，通过interrupt信号对线程进行关闭：可能资源来不及释放
 * 线程2，通过共享变量将终止信号控制在自己手里
 */
public class InterruptTest {

	public static void main(String[] args) throws InterruptedException {
		TestThread1 t1 = new TestThread1();
		TestThread2 t2 = new TestThread2();

		t1.start();
		t2.start();

		// 让线程运行一会儿后中断
		Thread.sleep(2000);
		t1.interrupt();
		t2.flag = false;
		System.out.println("main thread is exiting");
	}

}

class TestThread1 extends Thread {
	public void run() {
		// 判断标志，当本线程被别人interrupt后，JVM会被本线程设置interrupted标记
		//当接收到打断信号时，程序会爆发异常，中断循环，导致部分资源来不及释放
		while (!interrupted()) {
			System.out.println("test thread1 is running");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		System.out.println("test thread1 is exiting");
	}
}

class TestThread2 extends Thread {
	public volatile boolean flag = true;
	public void run() {
		// 判断标志，当本线程被别人interrupt后，JVM会被本线程设置interrupted标记
		//当采用共享变量检测的方法去停止线程时，程序总会正常执行，直到下一次while，可以保障资源释放，线程安全结束
		while (flag) {
			System.out.println("test thread2 is running");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("test thread2 is exiting");
	}
}
