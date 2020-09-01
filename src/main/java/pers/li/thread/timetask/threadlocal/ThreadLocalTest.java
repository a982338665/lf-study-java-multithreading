package pers.li.thread.timetask.threadlocal;

import java.util.Random;
/**
 * ThreadLocal--存单一变量（数据共享变量）
 * @author lishengbo
 * @Time   2017年11月21日下午11:10:40
 *
 */
public class ThreadLocalTest {
	/**当前线程数据共享变量*/
	private static ThreadLocal<Integer> map=new ThreadLocal<Integer>();
	
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int data=new Random().nextInt();
					System.err.println("当前线程生成的数："+data+"|"+Thread.currentThread().getName());
					map.set(data);
					new A().get();
					new B().get();
				}
			}).start();
		}
	}
	static class A{
		public void get(){
			 int data=map.get();
			 System.out.println("A__"+Thread.currentThread().getName()+"|"+data);
		}
	}	
	static class B{
		public void get(){
			int data=map.get();
			System.out.println("B__"+Thread.currentThread().getName()+"|"+data);
		}
	}	
}
