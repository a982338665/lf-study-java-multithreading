package pers.li.thread.executor.exam1;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 执行服务器
 *
 */
public class Server {
	
	//线程池
	private ThreadPoolExecutor executor;
	
	public Server(){
		executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		//executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(5);
	}
	
	//向线程池提交任务
	public void submitTask(Task task){
		System.out.printf("Server: A new task has arrived\n");
		executor.execute(task); //执行  无返回值
		
		System.out.printf("Server: Pool Size: %d\n",executor.getPoolSize());
		System.out.printf("Server: Active Count: %d\n",executor.getActiveCount());
		System.out.printf("Server: Completed Tasks: %d\n",executor.getCompletedTaskCount());
	}

	public void endServer() {
		executor.shutdown();
	}
}
