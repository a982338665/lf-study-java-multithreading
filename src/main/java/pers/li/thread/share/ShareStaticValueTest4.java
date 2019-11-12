package pers.li.thread.share;

/**
 * 共享变量示例：互斥锁，与volatile保证数据缓存更新
 */
public class ShareStaticValueTest4 {

    public static void main(String[] args) {
        //同一个对象开启4次，共享同一个对象的私有变量
        TestThread4 testThread1 = new TestThread4();
        new Thread(testThread1,"thread-0").start();
        new Thread(testThread1,"thread-1").start();
        new Thread(testThread1,"thread-2").start();
        new Thread(testThread1,"thread-3").start();
    }

}


class TestThread4 implements Runnable {

    /**
     * 私有变量，共享 >100
     */
    private volatile int tickets = 100;

    @Override
    public void run() {
        while (true) {
            synchronized(this) {
                if (tickets > 0) {
                    System.err.println("还剩：" + tickets);
                    tickets = tickets - 1;
                } else {
                    return;
                }
            }
//            sale();
        }
    }

    public synchronized void sale(){
        if (tickets > 0) {
            System.err.println("还剩：" + tickets);
            tickets = tickets - 1;
        } else {
            return;
        }
    }
}
