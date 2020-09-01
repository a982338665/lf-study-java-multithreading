package pers.li.thread.timetask.threadlocal;

import java.util.Random;
/**
 * 设计方式--重点：类部类的写法（平行类之间的写法）
 * 一个java文件可以并行写多个类，但是只能有一个类用public修饰，否则会报错，类与类之间为平行关系
 * ThreadLocal--存类（属性）变量（数据共享变量）--防止属性赋值错乱
 * @author lishengbo
 * @Time   2017年11月21日下午11:10:40
 *
 */
public class ThreadLocalFinal3_end {
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
					/*MyThread hh=new MyThread();
					hh.setAge(data+"");
					hh.setName("name:"+data);
					mapTh.set(hh);*/
					/************************************/
					MyThread2.getInstance().setAge(data+"");
					MyThread2.getInstance().setName("name:"+data);
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
			 //MyThread hh=mapTh.get();
			 //System.out.println("A__"+Thread.currentThread().getName()+"||"+hh.getAge()+"|||"+hh.getName());
			 /***************/
			 System.out.println("A_***"+Thread.currentThread().getName()+"||"+
			 MyThread2.getInstance().getAge()+"|||"+
			 MyThread2.getInstance().getName());
		
		}
	}	
	static class B{
		public void get(){
			int data=map.get();
			System.out.println("B__"+Thread.currentThread().getName()+"|"+data);
			 /*************************/
			 //MyThread hh=mapTh.get();
			 //System.out.println("B__"+Thread.currentThread().getName()+"||"+hh.getAge()+"|||"+hh.getName());
			 System.out.println("B_***"+Thread.currentThread().getName()+"||"+
					 MyThread2.getInstance().getAge()+"|||"+
					 MyThread2.getInstance().getName());
		}
	}	
	
}


 class MyThread2{
	/**无参构造方法私有化:
	 * 1.无法创建对象：new对象--私有化的目的，将程序默认提供的无参数构造私有化
	 * 2.不提供有参构造方法，杜绝对象创建
	 * **/
	 private MyThread2() {
		// TODO Auto-generated constructor stub
	}
	/****************************************************
	 * 私有化的静态属性不能够被类直接调用--
	 *private static MyThread2 mysr=new MyThread2();
	 *
	 * 对外提供一个获取实例对象的方法:
	 * 1.不論什麼時候调用都只返回这一个实例化对象
	 * 2.即使不调用，这个对象也早就创建好了（饱汉式单例模型）
	 *public static MyThread2 getInstance(){
	 *	return mysr;
	 *}
	/****************************************************/
	 /**
	  * 懒汉式：调用getInstance时创建对象：
	  * 要想实例化对象只有一个并数据准确：需要加synchronized
	  * 原因：
	  * 如果第一个线程进入if判断条件，但还未赋值的时候，cpu'切换至第二个线程，也进入if条件
	  * 这样赋值成功的将会是第二个
	  * 所以需要加关键字：--
	  * private static  MyThread2 nnn=null;
	  * public synchronized static MyThread2 getInstance(){
	  * if(nnn==null){
	  * nnn=new MyThread2();
	  * }
	  * return nnn;
	  * }
	  * **************************************************
	  */
	 //创建共享变量--
	 private static  ThreadLocal<MyThread2> nnn=new ThreadLocal<MyThread2>();
	 public /*synchronized*/ static MyThread2 getInstance(){
		 //取当前线程实例变量，若不存在，则创建该对象并将其置于共享变量中
		 //所指为当前线程，故不需要同步关键字---
		 MyThread2 myThread2 = nnn.get();
		 if(myThread2==null){
			 myThread2=new MyThread2();
			 nnn.set(myThread2);
		 }
		return myThread2;
	 }
	
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
