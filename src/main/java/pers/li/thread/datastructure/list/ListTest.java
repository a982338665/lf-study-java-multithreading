package pers.li.thread.datastructure.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListTest {    
 
    public static void main(String[] args) throws InterruptedException{

        //线程不安全
        List<String> unsafeList = new ArrayList<String>();
        //线程安全
        List<String> safeList1 = Collections.synchronizedList(new ArrayList<String>());
        //线程安全
        CopyOnWriteArrayList<String> safeList2 = new CopyOnWriteArrayList<String>();

        ListThread t1 = new ListThread(unsafeList);
        ListThread t2 = new ListThread(safeList1);
        ListThread t3 = new ListThread(safeList2);

        for(int i = 0; i < 10; i++){
            //开启十个线程，名称为0-9，线程不安全的集合
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
 
        System.out.println("listThread1.list.size() = " + t1.list.size());
        System.out.println("listThread2.list.size() = " + t2.list.size());
        System.out.println("listThread3.list.size() = " + t3.list.size());

        //输出list中的值
        System.out.println("unsafeList：");
        for(String s : t1.list){
            if(s == null){
            	System.out.print("null  ");
            }
            else
            {
            	System.out.print(s + "  ");
            }
        }
        System.out.println();
        System.out.println("safeList1：");
        for(String s : t2.list){
        	if(s == null){
            	System.out.print("null  ");
            }
            else
            {
            	System.out.print(s + "  ");
            }
        }
        System.out.println();
        System.out.println("safeList2：");
        for(String s : t3.list){
        	if(s == null){
            	System.out.print("null  ");
            }
            else
            {
            	System.out.print(s + "  ");
            }
        }
    }
}

class ListThread implements Runnable{
	public List<String> list;

    public ListThread(List<String> list){
        this.list = list;
    }

    @Override
    public void run() {
    	int i = 0;
    	while(i<10)
    	{
    		try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            //把当前线程名称加入list中
            list.add(Thread.currentThread().getName());
            i++;
    	}        
    }
}
