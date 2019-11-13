package pers.li.thread.executor.exam1;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// 创建一个执行服务器
		Server server=new Server();
		
		// 创建100个任务，并发给执行器，等待完成
		for (int i=0; i<100; i++){
			Task task=new Task("Task "+i);
			Thread.sleep(10);
			server.submitTask(task);
		}		
		server.endServer();
	}
}
