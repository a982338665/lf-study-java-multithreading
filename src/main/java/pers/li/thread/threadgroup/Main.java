package pers.li.thread.threadgroup;

import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {

		// 创建线程组
		ThreadGroup threadGroup = new ThreadGroup("Searcher");
		Result result=new Result();

		// 创建一个任务，10个线程完成
		Searcher searchTask=new Searcher(result);
		for (int i=0; i<10; i++) {
			Thread thread=new Thread(threadGroup, searchTask);
			thread.start();
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("========华丽丽0=======");
		
		// 查看线程组消息
		System.out.printf("active 线程数量: %d\n",threadGroup.activeCount());
		System.out.printf("线程组信息明细\n");
		threadGroup.list();
		System.out.println("========华丽丽1=======");

		// 遍历线程组
		Thread[] threads=new Thread[threadGroup.activeCount()];
		threadGroup.enumerate(threads);
		for (int i=0; i<threadGroup.activeCount(); i++) {
			System.out.printf("Thread %s: %s\n",threads[i].getName(),threads[i].getState());
		}
		System.out.println("========华丽丽2=======");

		// Wait for the finalization of the Threadds
		waitFinish(threadGroup);
		
		// Interrupt all the Thread objects assigned to the ThreadGroup
		threadGroup.interrupt();
	}

	public static void waitFinish(ThreadGroup threadGroup) {
		while (threadGroup.activeCount()>9) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
