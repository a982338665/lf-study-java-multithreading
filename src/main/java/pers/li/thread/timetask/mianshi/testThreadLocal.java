package pers.li.thread.timetask.mianshi;

import java.util.Random;

/**
 * 线程的共享变量
 * 类似单例模式--只针对当前线程
 * 可作为，变量安全======共享与传递(线程安全的)
 * @author lishengbo
 * @Time 2017年11月22日下午2:39:57
 */
public class testThreadLocal {

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					int data=new Random().nextInt(100);
					System.out.println(data+"---"+Thread.currentThread().getName());
					Mydata.getInstance().setAge(data+"||"+Thread.currentThread().getName());
					Mydata.getInstance().setName(data+"||--name"+Thread.currentThread().getName());
					new share().get1();
					new share().get2();
				}
			}).start();
		}
	}
}

class Mydata{
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
	private static ThreadLocal<Mydata> map=new ThreadLocal<Mydata>();
	private Mydata(){
		
	}
//	private static Mydata my=null;
	public static Mydata getInstance(){
		Mydata my = map.get();
		if(my==null){
			my=new Mydata();
			map.set(my);
		}
		return my;
	}
}

class share{
	  
	public void get1(){
		System.out.println(Thread.currentThread().getName()+"--1||"+Mydata.getInstance().getAge()+"||"+Mydata.getInstance().getName());
	}
	public void get2(){
		System.out.println(Thread.currentThread().getName()+"--2||"+Mydata.getInstance().getAge()+"||"+Mydata.getInstance().getName());
	}
}
