package pers.li.thread.share;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 共享变量示例：互斥锁，与volatile保证数据缓存更新
 */
public class ShareStaticValueTest5 {

    public static void main(String[] args) {
        //同一个对象开启4次，共享同一个对象的私有变量
        TestThread5 testThread1 = new TestThread5();
        new Thread(testThread1,"thread-0").start();
        new Thread(testThread1,"thread-1").start();
        new Thread(testThread1,"thread-2").start();
        new Thread(testThread1,"thread-3").start();
    }

}


class TestThread5 implements Runnable {

    /**
     * 私有变量，共享 >100
     */
    private volatile int tickets = 100;
    public static AtomicInteger x = new AtomicInteger(0);

    @Override
    public void run() {
        while (true) {
//            synchronized(this) {
                if (tickets > 0) {
                    x.addAndGet(1);
                    System.err.println("还剩：" + tickets);
                    tickets = tickets - 1;
                } else {
                    System.err.println(tickets+"|||"+x.toString());
                    return;
                }
//            }
//            sale();
        }
    }

    /*public synchronized void sale(){
        if (tickets > 0) {
            System.err.println("还剩：" + tickets);
            tickets = tickets - 1;
        } else {
            return;
        }
    }*/
}
