package pers.li.thread.executor.exam2;

import java.util.Random;
import java.util.concurrent.Callable;

public class SumTask implements Callable<Integer> {
	//定义每个线程计算的区间
	private int startNumber;
	private int endNumber;
	
	public SumTask(int startNumber, int endNumber){
		this.startNumber=startNumber;
		this.endNumber=endNumber;
	}
	
	@Override
	public Integer call() throws Exception {
		int sum = 0;
		for(int i=startNumber; i<=endNumber; i++)
		{
			sum = sum + i;
		}
		
		Thread.sleep(new Random().nextInt(1000));
		
		System.out.printf("%s: %d\n",Thread.currentThread().getName(),sum);
		return sum;
	}
}
