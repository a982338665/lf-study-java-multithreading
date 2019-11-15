package pers.li.thread.datastructure.queue;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class QueueTest {

    public static void main(String[] args) throws InterruptedException{

        //线程不安全
        Deque<String> unsafeQueue = new ArrayDeque<String>();
        //线程安全
        ConcurrentLinkedDeque<String> safeQueue1 = new ConcurrentLinkedDeque<String>();

        ArrayBlockingQueue<String> safeQueue2 = new ArrayBlockingQueue<String>(100);

        QueueThread t1 = new QueueThread(unsafeQueue);
        QueueThread t2 = new QueueThread(safeQueue1);
        QueueThread t3 = new QueueThread(safeQueue2);

        for(int i = 0; i < 10; i++){
            Thread thread1 = new Thread(t1, String.valueOf(i));
            thread1.start();
        }
        for(int i = 0; i < 10; i++) {
            Thread thread2 = new Thread(t2, String.valueOf(i));
            thread2.start();
        }
        for(int i = 0; i < 10; i++) {
            Thread thread3 = new Thread(t3, String.valueOf(i));
            thread3.start();
        }

        //等待子线程执行完
        Thread.sleep(2000);
 
        System.out.println("queueThread1.queue.size() = " + t1.queue.size());
        System.out.println("queueThread2.queue.size() = " + t2.queue.size());
        System.out.println("queueThread3.queue.size() = " + t3.queue.size());

        //输出queue中的值
        System.out.println("unsafeQueue：");
        for(String s:t1.queue)
        {
        	System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("safeQueue1：");
        for(String s:t2.queue)
        {
        	System.out.print(s + " ");
        }
        System.out.println();
        System.out.println("safeQueue2：");
        for(String s:t3.queue)
        {
        	System.out.print(s + " ");
        }
    }
}

class QueueThread implements Runnable{
	public Queue<String> queue;

    public QueueThread(Queue<String> queue){
        this.queue = queue;
    }

    @Override
    public void run() {
    	int i = 0;
    	while(i<10)
    	{
    		i++;
    		try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //把当前线程名称加入list中
            queue.add(Thread.currentThread().getName());
    	}        
    }
}
