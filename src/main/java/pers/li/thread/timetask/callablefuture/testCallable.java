package pers.li.thread.timetask.callablefuture;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 异步收集线程执行结果：
 *    使用completionService收集callable结果
 * @author lishengbo
 * @Time 2017年12月2日下午2:34:54
 */
public class testCallable {
    public static void main(String[] args) {
        try {
            completionServiceCount();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

  
    /**
     * 使用completionService收集callable结果
     * @throws ExecutionException 
     * @throws InterruptedException 
     */
    public static void completionServiceCount() throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executorService);
        int threadNum = 20;
        /***开始20次任务提交******************/
        for (int i = 0; i < threadNum; i++) {
            completionService.submit(getTask(i));
        }
        int sum = 0;
        int temp = 0;
        /***进行20次任务提交后的结果收集********/
        for(int i=0;i<threadNum;i++){
            temp = completionService.take().get();
            sum += temp;
            System.err.print(temp + "\t");
        }
        System.out.println("CompletionService all is : " + sum);
        executorService.shutdown();
    }

    public static Callable<Integer> getTask(final int no) {
        final Random rand = new Random();
        Callable<Integer> task = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int time = rand.nextInt(100)*100;
                System.out.println(Thread.currentThread().getName()+"||第-"+no+"-次任务	生成的随机数为:"+time);
                Thread.sleep(time);
                return no;
            }
        };
        return task;
    }
}
