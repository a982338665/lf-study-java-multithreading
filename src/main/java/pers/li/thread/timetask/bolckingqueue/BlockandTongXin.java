package pers.li.thread.timetask.bolckingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;



/**
 * 使用可阻塞队列来实现互斥，通信（condition,wait,notify）
 * 可以实现多方通知：
 * 
 * @author lishengbo
 * @Time   2017年12月3日下午11:59:01
 *
 */
public class BlockandTongXin {
	
	public static void main(String[] args) {
		final shareData share=new shareData();
		new Thread(){public void run() {
			for (int i = 0; i < 20; i++) {
				share.do1();
			}
		};}.start();
		new Thread(){public void run() {
			for (int i = 0; i < 20; i++) {
				share.do2();

			}
		};}.start();
		new Thread(){public void run() {
			for (int i = 0; i < 20; i++) {
				share.do3();

			}
		};}.start();
	}
	static class shareData{
		BlockingQueue<Integer> one=new ArrayBlockingQueue<Integer>(1);
		BlockingQueue<Integer> two=new ArrayBlockingQueue<Integer>(1);
		BlockingQueue<Integer> three=new ArrayBlockingQueue<Integer>(1);
		/**静态代码块和匿名构造方法区别：
		 * 前者在类加载时候调用，仅调用一次
		 * 后者在创建对象时调用，在构造方法之前，每创建一个对象，调用一次
		 * */
		{
			try {
				/**哪个先执行哪个就先阻塞满*/
				one.put(1);//阻塞队列可放一个数据，已装满，阻塞不执行
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void do1(){
			try {
				one.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < 2; i++) {
				System.out.println("do1--"+(i+1)+"=="+2);
			}
			try {
				two.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		public void do2(){
			try {
				two.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < 5; i++) {
				System.out.println("do2----##--"+(i+1)+"=="+5);
			}
			try {
				three.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void do3(){
			try {
				three.take();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int i = 0; i < 10; i++) {
				System.out.println("do3--$$$$$$$$$$$$"+(i+1)+"=="+10);
			}
			try {
				one.put(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
}
