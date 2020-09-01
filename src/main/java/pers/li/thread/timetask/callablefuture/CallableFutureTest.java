package pers.li.thread.timetask.callablefuture;
import java.util.concurrent.Callable;  
import java.util.concurrent.ExecutorService;  
import java.util.concurrent.Executors;  
import java.util.concurrent.Future;  
import java.util.concurrent.TimeUnit;  
import java.util.concurrent.TimeoutException;  
 /**
  * Future是否会执行超时问题
  * @author lishengbo
  * @Time 2017年12月2日下午2:44:39
  */
public class CallableFutureTest {  
  
    public static void main(String[] args) {  
  
         // 定义3个Callable类型的任务  
        Callable task0 = new CallableTest(0);  
        Callable task1 = new CallableTest(1);  
        Callable task2 = new CallableTest(2);  
          
        ExecutorService es = Executors.newFixedThreadPool(3);   // 创建一个执行任务的线程池  
        try {  
              
            Future future0 = es.submit(task0);  // 提交并执行任务1，任务启动时返回了一个Future对象  
            System.out.println("task0执行结果 ：" + future0.get());  // 获得第一个任务的结果，如果调用get方法直到当前线程会等待任务执行完毕后才往下执行  
              
            Future future1 = es.submit(task1);  // 提交并执行任务2，任务启动时返回了一个Future对象  
            String result1=null;  

            /*	while(true){
            		if(result1!=null){
            			break;
            		}
            		result1=(String) future1.get();
            	}
            	System.out.println("任务一结果："+result1);
*/
            try{  
            	result1=(String) future1.get(6,TimeUnit.SECONDS);   // 如有必要，最多等待6秒之后，获取其结果（如果结果可用），因为此时任务在无限循环肯定超时  
            }catch(TimeoutException e){  
            	System.out.println("future1用get获取超时了！此时任务1的执行结果："+result1);  
            }  
  
            Future future2 = es.submit(task2);  // 提交并执行任务2，任务启动时返回了一个Future对象  
            System.out.println("任务2开始执行了.....");  
            Thread.sleep(2000);     //主线程即当前线程停止2秒往下执行  
            System.out.println("task1被cancel()取消任务，结果: " + future1.cancel(true));   //中断任务1的循环  
            System.out.println("task2执行结果：" + future2.get());  
        } catch (Exception e){  
            System.out.println(e.toString());  
        }  
  
       // 停止任务执行服务  
        es.shutdownNow();  
        System.out.println("所有任务全部结束了!");  
    }  
}  
class CallableTest implements Callable{  
      
    public int testData = 0;   
  
    public CallableTest(int testData){  
        this.testData = testData;  
    }  
      
    @Override  
    public String call() throws Exception{  
        if (this.testData == 0){    
            return "testData = 0";  
        }   
        if (this.testData == 1){    
            try {  
                while (true) {  
                    System.out.println("任务1执行中.....");  
                    Thread.sleep(1000);  
                }  
            } catch (InterruptedException e) {  
                System.out.println("任务1中断了.....");  
            }  
            return "如果被cancle，则不会接受返回值！";  
       } else {  
            Thread.sleep(10000);  
            return "任务2睡了10秒！";  
       }  
   }  
}  
