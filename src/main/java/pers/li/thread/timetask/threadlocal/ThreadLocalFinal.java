package pers.li.thread.timetask.threadlocal;

import java.util.Random;
/**
 * 设计方式--重点：内部类的写法
 * map存值
 * 一个java文件可以并行写多个类，但是只能有一个类用public修饰，否则会报错，类与类之间为平行关系
 * ThreadLocal--存类（属性）变量（数据共享变量）--防止属性赋值错乱
 * @author lishengbo
 * @Time   2017年11月21日下午11:10:40
 *
 */
public class ThreadLocalFinal {
	/**当前线程数据共享变量*/
	private static ThreadLocal<Integer> map=new ThreadLocal<Integer>();
	private static ThreadLocal<MyThread> mapTh=new ThreadLocal<MyThread>();
	
	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					int data=new Random().nextInt(); 
					System.err.println("当前线程生成的数："+data+"|"+Thread.currentThread().getName());
					map.set(data);
					/************************************/
					MyThread hh=new MyThread();
					hh.setAge(data+"");
					hh.setName("name:"+data);
					mapTh.set(hh);
					/************************************/
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
			 /*************************/
			 MyThread hh=mapTh.get();
			 System.out.println("A__"+Thread.currentThread().getName()+"||"+hh.getAge()+"|||"+hh.getName());
		}
	}	
	static class B{
		public void get(){
			int data=map.get();
			System.out.println("B__"+Thread.currentThread().getName()+"|"+data);
			 /*************************/
			 MyThread hh=mapTh.get();
			 System.out.println("B__"+Thread.currentThread().getName()+"||"+hh.getAge()+"|||"+hh.getName());
		
		}
	}	
	
	static class MyThread{
		private String name;
		private String age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		
	}
}
