package pers.li.thread.share;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 共享变量示例：互斥锁，与volatile保证数据缓存更新
 */
public class ShareStaticValueTest6 {

    public static void main(String[] args) {
        //同一个对象开启4次，共享同一个对象的私有变量
        TestThread6 testThread1 = new TestThread6();
//        new Thread(testThread1,"thread-0").start();
//        new Thread(testThread1,"thread-1").start();
//        new Thread(testThread1,"thread-2").start();
        for (int i = 0; i < 100000; i++) {
            new Thread(testThread1).start();
        }
    }

}


class TestThread6 implements Runnable {

    /**
     * 私有变量，共享 >100
     */
//    private volatile int tickets = 100;
//    public static AtomicInteger x = new AtomicInteger(0);

    @Override
    public void run() {
        Integer x = 1; // 语句1
        Integer y = 2; // 语句2
        x = x + new Integer(5); // 语句3   6
        y = x * x; // 语句4   36
        x = y + new Integer(2);
        if (x != 38 || y != 36) {
            System.err.println(x + "------" + y);
        }
    }

}
