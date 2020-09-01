package pers.li.thread.timetask.threadlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 实现线程同步通信，等同于synchronized，使用lock更加体现了面向对象的思想
 * @author lishengbo
 * @Time 2017年12月2日下午3:18:47
 */
public class testSimple {

	public static void main(String[] args) {
		new testSimple().service();
	}
	public void service(){

        //同一個對象，不同的參數，為防止數據問題，需要加同步代碼款來互斥，防止數據錯亂
        final outputer outer=new outputer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outer.outpute("qwekjhkhkrty");
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outer.outpute1("123999999945");
                }
            }
        }).start();
	}
	
    static class outputer{
    	/**创建锁对象*********************************************/
    	Lock lock=new ReentrantLock();
    	/**创建锁对象*********************************************/
    	public void outpute(String name){
            int len=name.length();
//            synchronized (this) {//this是指此类实例化后的对象,针对局部代码互斥
            /**********锁对象********************************/
            lock.lock();
            /**********锁对象********************************/
                try {
					for (int i = 0; i < len; i++) {
					    System.out.print(name.charAt(i));
					}
					System.out.println();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
					/**********释放锁对象：无论程序是否报错均要释放锁********************************/
					lock.unlock();
					/**********释放锁对象：无论程序是否报错均要释放锁********************************/
				}
//            }
        }
        //方法上的synchronized也是针对于同一个该类实例化的对象，故与上一个方法互斥，同一把锁
        public  void outpute1(String name){
            int len=name.length();
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
        }
    }
}
