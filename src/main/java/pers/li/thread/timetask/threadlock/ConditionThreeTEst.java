package pers.li.thread.timetask.threadlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 *利用condition唤醒指定线程:
 * 这是强于wait，notify的好处 
 *
 */
public class ConditionThreeTEst {

	
	public static void main(String[] args) {

        final ConditionT outer=new ConditionT();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=50; i++) {
                    try {
                        outer.outpute1(1,i);
                        outer.outpute2(5,i);
                        outer.outpute3(10,i);
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
	    private int is=1;
	    /**
	     *子线程循环次数
	     * @throws InterruptedException 
	     */
	    /**
	     * 定义锁
	     */
	    Lock lock =new ReentrantLock();
	    Condition con1=lock.newCondition();
	    Condition con2=lock.newCondition();
	    Condition con3=lock.newCondition();
	    public /*synchronized*/ void outpute1(Integer count,Integer count1) throws InterruptedException{
	    	lock.lock();
	        try {
				while(is!=1){
					con1.await();//注意是await(),非wait()|wait()是Object的方法，故不会报错
				}
				for (int i = 0; i < count; i++) {
				    Thread.sleep(10);
				    System.out.println("子线程："+(i+1)+"||-"+count1);
				}
				is=2;
			    con2.signal();	
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
		        while(is!=2){//默认为true，若不是则等待
		            con2.await();//注意是await(),非wait()|wait()是Object的方法，故不会报错
		        }
		        for (int i = 0; i < count; i++) {
		            Thread.sleep(10);
		            System.out.println("主线程："+i+"_---------"+"||-"+count1);
		        }
		        is=3;
		        con3.signal();
	        }finally{
				lock.unlock();
			}
	    }
	    /**
	     *主线程循环次数
	     * @throws InterruptedException 
	     */
	    public  /*synchronized*/ void outpute3(Integer count,Integer count1) throws InterruptedException{
	    	lock.lock();
	    	try {
	    		while(is!=3){//默认为true，若不是则等待
	    			con3.await();//注意是await(),非wait()|wait()是Object的方法，故不会报错
	    		}
	    		for (int i = 0; i < count; i++) {
	    			Thread.sleep(10);
	    			System.out.println("主线程："+i+"_---------------------"+"||-"+count1);
	    		}
	    		is=1;
	    		con1.signal();
	    	}finally{
	    		lock.unlock();
	    	}
	    }
	}
}
