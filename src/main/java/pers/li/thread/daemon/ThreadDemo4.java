package pers.li.thread.daemon;

public class ThreadDemo4
{
	public static void main(String args[]) throws InterruptedException
	{
		TestThread4 t = new TestThread4();
		//是否设置为守护线程，守护线程结束的两种方式：run结束，main结束
		//此示例中，线程内是无限循环，当设置此线程为守护线程时，main结束，则线程结束
		//当此线程为普通线程时，则main函数结束，线程也不会结束
//		t.setDaemon(true);
		t.start();
		Thread.sleep(2000);
		System.out.println("main thread is exiting");
	}
}
 class TestThread4 extends Thread
{
	public void run() 
	{
		while(true)
		{
			System.out.println("TestThread4" + 
			"　is running");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
} 
