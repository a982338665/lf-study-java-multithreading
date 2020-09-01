package pers.li.thread.timetask;

import org.junit.Test;

public class Testsleep {

	
	/**
	 * 线程锁
	 */
	private final Object object = new Object();
	/**
	 * 判别是sleep还是wait
	 */
	private boolean is;
	
	
	public boolean isIs() {
		return is;
	}
	public void setIs(boolean is) {
		this.is = is;
	}
	public Testsleep() {
		// TODO Auto-generated constructor stub
	}
	public Testsleep( boolean is) {
		
	}
	/**
	 * 启动线程
	 */
	public void startThread() {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("开始执行线程。。。");
				System.out.println("进入等待状态。。。");
				synchronized (object) {//持有object对象的monitor
					try {
						if(is==true){
							object.wait(); //线程就会释放monitor的持有对象并让自己处于等待状态
							//此时必须调用notify()或者notifyAll()去唤醒在等待的其他线程，否则该线程将一直在等待中
						}else{
							Thread.sleep(3000); //线程就会释放monitor的持有对象并让自己处于等待状态
							//此时必须调用notify()或者notifyAll()去唤醒在等待的其他线程，否则该线程将一直在等待中
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				System.out.println("线程结束。。。");
			}
		});
		t.start();
	}

	@Test
	public void sdfsf() throws InterruptedException {
		Testsleep main = new Testsleep(false);
		main.startThread();
	};

}
