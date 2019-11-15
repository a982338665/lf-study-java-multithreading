package pers.li.thread.datastructure.map;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MapTest{    
 
    public static void main(String[] args) throws InterruptedException{

        //线程不安全
        Map<Integer,String> unsafeMap = new HashMap<Integer,String>();
        //线程安全
        Map<Integer,String> safeMap1 = Collections.synchronizedMap(new HashMap<Integer,String>());
        //线程安全
        ConcurrentHashMap<Integer,String> safeMap2 = new ConcurrentHashMap<Integer,String>();

        MapThread t1 = new MapThread(unsafeMap);
        MapThread t2 = new MapThread(safeMap1);
        MapThread t3 = new MapThread(safeMap2);

        //unsafeMap的运行测试
        for(int i = 0; i < 10; i++){
        	Thread t = new Thread(t1);
        	t.start();
        }       
        for(int i = 0; i < 10; i++) {
        	Thread t = new Thread(t2);
            t.start();
        }
        for(int i = 0; i < 10; i++) {
        	Thread t = new Thread(t3);
            t.start();
        }

        //等待子线程执行完
        Thread.sleep(2000);
 
        System.out.println("mapThread1.map.size() = " + t1.map.size());
        System.out.println("mapThread2.map.size() = " + t2.map.size());
        System.out.println("mapThread3.map.size() = " + t3.map.size());

        //输出set中的值
        System.out.println("unsafeMap：");
        Iterator iter = t1.map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>)iter.next();
            // 获取key
            System.out.print(entry.getKey() + ":");
            // 获取value
            System.out.print(entry.getValue() + " ");
        }
        System.out.println();
        
        System.out.println("safeMap1：");
        iter = t2.map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>)iter.next();
            // 获取key
            System.out.print(entry.getKey() + ":");
            // 获取value
            System.out.print(entry.getValue() + " ");
        }

        System.out.println();
        System.out.println("safeMap2：");
        iter = t3.map.entrySet().iterator();
        while(iter.hasNext()) {
            Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>)iter.next();
            // 获取key
            System.out.print(entry.getKey() + ":");
            // 获取value
            System.out.print(entry.getValue() + " ");
        }
        System.out.println();
        System.out.println("mapThread1.map.size() = " + t1.map.size());
        System.out.println("mapThread2.map.size() = " + t2.map.size());
        System.out.println("mapThread3.map.size() = " + t3.map.size());
    }
}

class MapThread implements Runnable
{
	public Map<Integer,String> map;

    public MapThread(Map<Integer,String> map){
        this.map = map;
    }

    @Override
    public void run() {
        int i=0;
        
        while(i<100)
        {
        	//把当前线程名称加入map中
            map.put(i++,Thread.currentThread().getName());
            try {
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }        
    }
}
