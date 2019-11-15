package pers.li.thread.lock.lock;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LockExample {

	private static final ReentrantLock queueLock = new ReentrantLock(); //可重入锁
	private static final ReentrantReadWriteLock orderLock = new ReentrantReadWriteLock(); //可重入读写锁
	
	/**
	 * 有家奶茶店，点单有时需要排队 
	 * 假设想买奶茶的人如果看到需要排队，就决定不买
	 * 又假设奶茶店有老板和多名员工，记单方式比较原始，只有一个订单本
	 * 老板负责写新订单，员工不断地查看订单本得到信息来制作奶茶，在老板写新订单时员工不能看订单本
	 * 多个员工可同时看订单本，在员工看时老板不能写新订单
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		//buyMilkTea();
		handleOrder(); //需手动关闭
	}
	
	public void tryToBuyMilkTea() throws InterruptedException {
		boolean flag = true;
		while(flag)
		{
			if (queueLock.tryLock()) {
				//queueLock.lock();
				long thinkingTime = (long) (Math.random() * 500);
				Thread.sleep(thinkingTime);
				System.out.println(Thread.currentThread().getName() + "： 来一杯珍珠奶茶，不要珍珠");
				flag = false;
				queueLock.unlock();
			} else {
				//System.out.println(Thread.currentThread().getName() + "：" + queueLock.getQueueLength() + "人在排队");
				System.out.println(Thread.currentThread().getName() + "： 再等等");
			}
			if(flag)
			{
				Thread.sleep(1000);
			}
		}
		
	}
	
	public void addOrder() throws InterruptedException {
		orderLock.writeLock().lock();
		long writingTime = (long) (Math.random() * 1000);
		Thread.sleep(writingTime);
		System.out.println("老板新加一笔订单");
		orderLock.writeLock().unlock();
	}
	
	public void viewOrder() throws InterruptedException {
		orderLock.readLock().lock();
			
		long readingTime = (long) (Math.random() * 500);
		Thread.sleep(readingTime);
		System.out.println(Thread.currentThread().getName() + ": 查看订单本");
		orderLock.readLock().unlock();			

	}
	
	public static void buyMilkTea() throws InterruptedException {
		LockExample lockExample = new LockExample();
		int STUDENTS_CNT = 10;
		
		Thread[] students = new Thread[STUDENTS_CNT];
		for (int i = 0; i < STUDENTS_CNT; i++) {
			students[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						long walkingTime = (long) (Math.random() * 1000);
						Thread.sleep(walkingTime);
						lockExample.tryToBuyMilkTea();
					} catch(InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
				
			}
			);
			
			students[i].start();
		}
		
		for (int i = 0; i < STUDENTS_CNT; i++)
			students[i].join();

	}
	
	
	public static void handleOrder() throws InterruptedException {
		LockExample lockExample = new LockExample();
		
		
		Thread boss = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						lockExample.addOrder();
						long waitingTime = (long) (Math.random() * 1000);
						Thread.sleep(waitingTime);
					} catch (InterruptedException e) {
						System.out.println(e.getMessage());
					}
				}
			}
		});
		boss.start();

		int workerCnt = 3;
		Thread[] workers = new Thread[workerCnt];
		for (int i = 0; i < workerCnt; i++)
		{
			workers[i] = new Thread(new Runnable() {

				@Override
				public void run() {
					while (true) {
						try {
								lockExample.viewOrder();
								long workingTime = (long) (Math.random() * 5000);
								Thread.sleep(workingTime);
							} catch (InterruptedException e) {
								System.out.println(e.getMessage());
							}
						}
				}
				
			});
			
			workers[i].start();
		}
		
	}
}
