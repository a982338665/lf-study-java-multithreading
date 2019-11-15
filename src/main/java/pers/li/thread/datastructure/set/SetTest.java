package pers.li.thread.datastructure.set;

import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;

public class SetTest{  
 
    public static void main(String[] args) throws InterruptedException{

        //线程不安全
        Set<String> unsafeSet = new HashSet<String>();
        //线程安全
        Set<String> safeSet1 = Collections.synchronizedSet(new HashSet<String>());
        //线程安全
        CopyOnWriteArraySet<String> safeSet2 = new CopyOnWriteArraySet<String>();

        SetThread t1 = new SetThread(unsafeSet);
        SetThread t2 = new SetThread(safeSet1);
        SetThread t3 = new SetThread(safeSet2);

        //unsafeSet的运行测试
        for(int i = 0; i < 10; i++){
        	Thread t = new Thread(t1, String.valueOf(i));
        	t.start();
        }
        for(int i = 0; i < 10; i++) {
        	Thread t = new Thread(t2, String.valueOf(i));
            t.start();
        }
        for(int i = 0; i < 10; i++) {
        	Thread t = new Thread(t3, String.valueOf(i));
            t.start();
        }

        //等待子线程执行完
        Thread.sleep(2000);
 
        System.out.println("setThread1.set.size() = " + t1.set.size());
        System.out.println("setThread2.set.size() = " + t2.set.size());
        System.out.println("setThread3.set.size() = " + t3.set.size());

        //输出set中的值
        System.out.println("unsafeSet：");
        for(String element:t1.set){
            if(element == null){
            	System.out.print("null  ");
            }
            else
            {
            	System.out.print(element + "  ");
            }
        }
        System.out.println();
        System.out.println("safeSet1：");
        for(String element:t2.set){
        	if(element == null){
            	System.out.print("null  ");
            }
            else
            {
            	System.out.print(element + "  ");
            }
        }
        System.out.println();
        System.out.println("safeSet2：");
        for(String element:t3.set){
        	if(element == null){
            	System.out.print("null  ");
            }
            else
            {
            	System.out.print(element + "  ");
            }
        }
    }
}

class SetThread implements Runnable{
	public Set<String> set;

    public SetThread(Set<String> set){
        this.set = set;
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
            set.add(Thread.currentThread().getName() + i);
    	}        
    }
}
