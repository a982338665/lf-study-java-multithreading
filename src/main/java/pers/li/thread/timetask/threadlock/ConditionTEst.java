package pers.li.thread.timetask.threadlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * 类似于wait，notify,用来实现通信:
 * 使用condition的优势：（阻塞队列:队列起到缓冲效果，先进先出 ）
 * synchronized或lock用来实现互斥
 * @author lishengbo
 * @Time 2017年12月2日下午5:11:21
 */
public class ConditionTEst {

	
	public static void main(String[] args) {

        final ConditionT outer=new ConditionT();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=50; i++) {
                    try {
                        outer.outpute1(5,i);
                        outer.outpute2(10,i);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                 
                }
            }
        }).start();
    
	} 
	
	
	
	static class ConditionT{
	    /**
	     * 通信协议：确定先后顺序
	     */
	    private boolean is=true;
	    /**
	     *子线程循环次数
	     * @throws InterruptedException 
	     */
	    /**
	     * 定义锁
	     */
	    Lock lock =new ReentrantLock();
	    Condition con=lock.newCondition();
	    public /*synchronized*/ void outpute1(Integer count,Integer count1) throws InterruptedException{
	    	lock.lock();
	        try {
				while(!is){
					con.await();//注意是await(),非wait()|wait()是Object的方法，故不会报错
				}
				for (int i = 0; i < count; i++) {
				    Thread.sleep(10);
				    System.out.println("子线程："+(i+1)+"||-"+count1);
				}
				is=false;
			    con.signal();	
	        }finally{
				lock.unlock();
			}
	    }
	    /**
	     *主线程循环次数
	     * @throws InterruptedException 
	     */
	    public  /*synchronized*/ void outpute2(Integer count,Integer count1) throws InterruptedException{
	    	lock.lock();
	        try {
		        while(is){//默认为true，若不是则等待
		            con.await();//注意是await(),非wait()|wait()是Object的方法，故不会报错
		        }
		        for (int i = 0; i < count; i++) {
		            Thread.sleep(10);
		            System.out.println("主线程："+i+"_---------"+"||-"+count1);
		        }
		        is=true;
		        con.signal();
	        }finally{
				lock.unlock();
			}
	    }
	}
}
