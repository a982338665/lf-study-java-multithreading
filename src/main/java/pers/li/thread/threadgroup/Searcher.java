package pers.li.thread.threadgroup;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Searcher implements Runnable {

	private Result result;
	
	public Searcher(Result result) {
		this.result=result;
	}

	@Override
	public void run() {
		String name=Thread.currentThread().getName();
		System.out.printf("Thread %s: 启动\n",name);
		try {
			doTask();
			result.setName(name);
		} catch (InterruptedException e) {
			System.out.printf("Thread %s: 被中断\n",name);
			return;
		}
		System.out.printf("Thread %s: 完成\n",name);
	}
	
	private void doTask() throws InterruptedException {
		Random random=new Random((new Date()).getTime());
		int value=(int)(random.nextDouble()*100);
		System.out.printf("Thread %s: %d\n",Thread.currentThread().getName(),value);
		TimeUnit.SECONDS.sleep(value);
	}
}
