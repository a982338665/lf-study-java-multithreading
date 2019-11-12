package pers.li.thread.share;

/**
 * 共享变量示例：
 */
public class ShareStaticValueTest2 {

    public static void main(String[] args) throws InterruptedException {
        //同一个对象开启4次，共享同一个对象的私有变量
        TestThread2 testThread1 = new TestThread2();
        new Thread(testThread1).start();
        Thread.sleep(2000);
        testThread1.flag = false;
        System.err.println("main is exiting");

    }

}


class TestThread2 implements Runnable {
//    boolean flag = true;//子线程不会停止,因为他不会收到flag = false的消息
    volatile boolean flag = true;//用volatile修饰的变量可以及时在各线程里通知，通知他们更新缓存区

    @Override
    public void run() {
        int i=0;
        while (flag) {
            i++;
        }
        System.err.println("thread is exiting");
    }
}
