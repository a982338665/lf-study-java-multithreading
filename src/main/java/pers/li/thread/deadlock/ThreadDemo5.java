package pers.li.thread.deadlock;

import java.util.concurrent.TimeUnit;

/**
 *  -每个线程互相持有别人需要的锁
 *  -预防死锁，对资源进行等级排序
 *  线程查看工具jvisualvm：java自带的分析工具
 *    -打开cmd --> 输入jvisualvm进行线程监测
 */
public class ThreadDemo5 {
    public static Integer r1 = 1;
    public static Integer r2 = 2;

    public static void main(String args[]) throws InterruptedException {
        TestThread51 t1 = new TestThread51();
        t1.start();
        TestThread52 t2 = new TestThread52();
        t2.start();
    }
}

class TestThread51 extends Thread {
    public void run() {
        //先要r1,再要r2
        synchronized (ThreadDemo5.r1) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (ThreadDemo5.r2) {
                System.out.println("TestThread51 is running");
            }
        }
    }
}

class TestThread52 extends Thread {
    public void run() {
        //先要r2,再要r1
        synchronized (ThreadDemo5.r2) {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (ThreadDemo5.r1) {
                System.out.println("TestThread52 is running");
            }
        }
    }
} 
