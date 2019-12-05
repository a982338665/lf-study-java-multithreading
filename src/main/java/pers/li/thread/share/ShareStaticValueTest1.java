package pers.li.thread.share;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 共享变量示例：
 */
public class ShareStaticValueTest1 {

    public static void main(String[] args) {
        //同一个对象开启4次，共享同一个对象的私有变量
        TestThread1 testThread1 = new TestThread1();
        new Thread(testThread1).start();
        new Thread(testThread1).start();
        new Thread(testThread1).start();
        new Thread(testThread1).start();
    }

}


class TestThread1 implements Runnable {

    /**
     * 私有变量，共享 >100
     */
    private int tickets = 100;
    public static AtomicInteger x = new AtomicInteger(0);


    @Override
    public void run() {
        while (true) {
            if (tickets > 0) {
                x.addAndGet(1);
                System.err.println("还剩：" + tickets);
                tickets = tickets - 1;
            } else {
                System.err.println(x.toString());
                return;
            }
        }
    }
}
