package pers.li.thread.lock.exchanger;

import java.util.Scanner;
import java.util.concurrent.Exchanger;

public class ExchangerExample {
	
	/**
	 * 本例通过Exchanger实现学生成绩查询，简单线程间数据的交换
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Exchanger<String> exchanger = new Exchanger<String>();
		BackgroundWorker worker = new BackgroundWorker(exchanger);
		new Thread(worker).start();
		
		Scanner scanner = new Scanner(System.in);
		while(true) {
			System.out.println("输入要查询的属性学生姓名：");
			String input = scanner.nextLine().trim();
			exchanger.exchange(input); //把用户输入传递给线程
			String value = exchanger.exchange(null); //拿到线程反馈结果
			if ("exit".equals(value)) {
				break;
			}
			System.out.println("查询结果：" + value);
		}
		scanner.close();
	} 
}

class BackgroundWorker implements Runnable {

	final Exchanger<String> exchanger;
	BackgroundWorker(Exchanger<String> exchanger) {
		this.exchanger = exchanger;
	}
	@Override
	public void run() {
		while (true) {
			try {
				String item = exchanger.exchange(null);
				switch (item) {
				case "zhangsan": 
					exchanger.exchange("90");
					break;
				case "lisi":
					exchanger.exchange("80");
					break;
				case "wangwu":
					exchanger.exchange("70");
					break;
				case "exit":
					exchanger.exchange("exit");
					return;
				default:
					exchanger.exchange("查无此人");
				}					
			} catch (InterruptedException e) {
				e.printStackTrace();
			}				
		}
	}		
}
