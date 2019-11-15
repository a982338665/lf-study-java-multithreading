package pers.li.thread.schedule.schedule;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest {

    public static void main(String[] a) throws Exception {
//    	executeAtFixTime();
        //严格按照定时执行，不管上次的执行是否完成
    	executeFixedRate();  //3s
        //等待上次执行完成后，以结束时间为定时开始时间，总保证上次任务执行完毕
//        executeFixedDelay();  //4s
    }

    /**
     * 1秒后执行任务task
     *
     * @throws Exception
     */
    public static void executeAtFixTime() throws Exception {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.schedule(
                new MyTask(),
                1,
                TimeUnit.SECONDS);

        Thread.sleep(20000);
        executor.shutdown();
    }

    /**
     * 延迟1毫秒执行，每3000毫秒执行一次
     * 周期任务 固定速率 是以上一个任务开始的时间计时，period时间过去后，检测上一个任务是否执行完毕，
     * 如果上一个任务执行完毕，则当前任务立即执行，如果上一个任务没有执行完毕，则需要等上一个任务执行完毕后立即执行。
     *
     * @throws Exception
     */
    public static void executeFixedRate() throws Exception {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleAtFixedRate(
                new MyTask(),
                1,
                3000,
                TimeUnit.MILLISECONDS);

        Thread.sleep(20000);
        executor.shutdown();
    }

    /**
     * 周期任务 固定延时 是以上一个任务结束时开始计时，period时间过去后，立即执行。
     *
     * @throws Exception
     */
    public static void executeFixedDelay() throws Exception {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        executor.scheduleWithFixedDelay(
                new MyTask(),
                1,
                3000,
                TimeUnit.MILLISECONDS);

        Thread.sleep(20000);
        executor.shutdown();
    }
}

class MyTask implements Runnable {
    public void run() {
        System.out.println("时间为：" + new Date());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //System.out.println("时间为：" + new Date());
    }
}
