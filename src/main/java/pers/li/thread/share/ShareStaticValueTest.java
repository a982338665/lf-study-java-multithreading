package pers.li.thread.share;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 共享变量示例：
 */
public class ShareStaticValueTest {

    public static void main(String[] args) {
        //4个对象开启4次，私有变量不共享
        new TestThread0().start();
        new TestThread0().start();
        new TestThread0().start();
        new TestThread0().start();
    }

}


class TestThread0 extends Thread {

    public static AtomicInteger x = new AtomicInteger(0);


    /**
     * 私有变量，每个线程100，无共享,共400
     */
//        private  int tickets = 100;
    /**
     * 静态变量：所有线程共享,但是票数>100
     */
    private static int tickets = 100;

    @Override
    public void run() {
        while (true) {
            if (tickets > 0) {
                x.addAndGet(1);
                System.err.println("还剩：" + tickets);
                tickets = tickets - 1;
            }else{
                System.err.println(x.toString());
                return;
            }
        }
    }
}
