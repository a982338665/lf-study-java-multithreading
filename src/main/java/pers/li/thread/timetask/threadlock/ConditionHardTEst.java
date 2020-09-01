package pers.li.thread.timetask.threadlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 类似于wait，notify,用来实现通信:
 * 使用condition的优势：（可阻塞队列:队列起到缓冲效果，先进先出 ）
 * synchronized或lock用来实现互斥
 * @author lishengbo
 * @Time 2017年12月2日下午5:11:21
 */
public class ConditionHardTEst {
	final Lock lock =new ReentrantLock();
	/**使用两个Condition的原因:
	 * 		当前condition值已经放满时，用另一个condition去唤醒其他线程，（不包括当前condition）
	 * 		这样，唤醒就具有了针对性，可以唤醒指定的线程
	 * 		避免了，存值时，满了，去通知唤醒线程时，再次的唤醒了存值的其他线程
	 * **/
	final Condition notFull=lock.newCondition();
	final Condition notEmpty=lock.newCondition();
	/**创建一个可以存放100个对象的消息队列********/
	final Object[] items=new Object[100];
	int putptr;
	int takeptr;
	int count;//队列中已经存在值的总数
	
	/**
	 * 队列存值方法
	 * @param x
	 * @throws InterruptedException
	 */
	public void put(Object x) throws InterruptedException{
		lock.lock();
		try {
			//如果值已经放满了，就等待被取出,直到有空置时，继续向下执行
			while(count==items.length) notFull.await();
			//按顺序存值，存进第一个，putptr=0
			items[putptr]=x;
			//当存进最后一个时，putptr=putptr+1
			//所以当最后一个被存进去之后，重新赋值开始从0继续存值，
			if(++putptr==items.length) putptr=0;
			//进入队列一个，就加1，表示队列中已经存在值的个数
			++count;
			//存值完成之后通知，可以进行取值--唤醒的是去取值的那些线程，不包括存值的线程
			notEmpty.signal();
		}finally{
			lock.unlock();
		}
	}
	/**
	 * 队列取值方法
	 * @return
	 * @throws InterruptedException
	 */
	public Object take() throws InterruptedException{
		lock.lock();
		try {
			while(count==0) notEmpty.await();
			Object x=items[takeptr];
			if(++takeptr==items.length) putptr=0;
			--count;
			notFull.signal();
			return x;
		}finally{
			lock.unlock();
		}
	}
	
	
	
	
}
