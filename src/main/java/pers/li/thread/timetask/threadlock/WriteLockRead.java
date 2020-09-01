package pers.li.thread.timetask.threadlock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁介绍：--提高性能且互斥|
 * (若使用关键字就都互斥了,故使用Lock或synchronized均会使效率降低）
 * 	读锁之间不互斥，
 * 	写锁之间互斥，
 *  读写锁之间互斥
 *  ------------------------------------
 * @author lishengbo
 * @Time 2017年12月2日下午3:37:17
 */
public class WriteLockRead {

	public static void main(String[] args) {
		/**
		 * 设置共享变量，用于执行任务
		 */
		final readandWrite bean=new readandWrite();
		/**
		 * 创建四个线程：两个读数据，两个写数据
		 */
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					
				}
			}).start();
			new Thread(new Runnable() {
				@Override
				public void run() {
					
				}
			}).start();
		}
	}
}

class readandWrite{
	/**使用此可以实现读锁之间不互斥，故而提高效率********************/
	ReadWriteLock lock=new ReentrantReadWriteLock();
	private Object data;
	
	public void read(){
		/***********************/
		lock.readLock().lock();
		try {
			System.err.println("业务-------");
			System.out.println("read--"+Thread.currentThread().getName()+"|"+data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			/***********************/
			lock.readLock().unlock();
		}
	}
	public void write(Object data){
		lock.writeLock().lock();
		try {
			System.err.println("业务-------");
			this.data=data;
			System.out.println("write+++"+Thread.currentThread().getName()+"|"+data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.writeLock().unlock();
		}
	}
}
