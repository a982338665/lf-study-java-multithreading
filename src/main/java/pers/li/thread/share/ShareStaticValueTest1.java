package pers.li.thread.share;

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

    @Override
    public void run() {
        while (true) {
            if (tickets > 0) {
                System.err.println("还剩：" + tickets);
                tickets = tickets - 1;
            } else {
                return;
            }
        }
    }
}
