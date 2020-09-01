package pers.li.thread.timetask.threadpool;

import java.util.Date;


public class ThreadForpools implements Runnable{

    private Integer index;
    public  ThreadForpools(Integer index)
    {
     this.index=index;
    }
    @Override
    public void run() {
        /**
         * 业务......省略
         */
        try {
            System.out.println("开始处理线程！！！"+new Date().getTime());
            Thread.sleep(index*1000);
            System.out.println("我的线程标识是："+this.toString()+"|"+Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
