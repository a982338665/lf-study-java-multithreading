package pers.li.thread.timetask.threadpool;

public class A_ implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"|||需要执行的业务逻辑------");
	}

}
