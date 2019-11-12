package pers.li.thread.share;

/**
 * 共享变量示例：
 */
public class ShareStaticValueTest {

    public static void main(String[] args) {
        new TestThread0().start();
        new TestThread0().start();
        new TestThread0().start();
        new TestThread0().start();
    }

}


class TestThread0 extends Thread {

    /**
     * 私有变量，每个线程100，无共享,共400
     */
        private  int tickets = 100;
    /**
     * 静态变量：所有线程共享,但是票数>100
     */
//    private static int tickets = 100;

    @Override
    public void run() {
        while (true) {
            if (tickets > 0) {
                System.err.println("还剩：" + tickets);
                tickets = tickets - 1;
            }else{
                return;
            }
        }
    }
}
