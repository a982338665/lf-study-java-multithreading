package pers.li.thread.lock.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
	
	/**
	 * 假定有三行数，用三个线程分别计算每一行的和，最终计算总和
	 * @param args
	 */
	public static void main(String[] args) {
		final int[][] numbers = new int[3][5];
		final int[] results = new int[3];
		int[] row1 = new int[]{1, 2, 3, 4, 5};
		int[] row2 = new int[]{6, 7, 8, 9, 10};
		int[] row3 = new int[]{11, 12, 13, 14, 15};
		numbers[0] = row1;
		numbers[1] = row2;
		numbers[2] = row3;
		
		CalculateFinalResult finalResultCalculator = new CalculateFinalResult(results);
		CyclicBarrier barrier = new CyclicBarrier(3, finalResultCalculator);
		//当有3个线程在barrier上await，就执行finalResultCalculator
		
		for(int i = 0; i < 3; i++) {
			CalculateEachRow rowCalculator = new CalculateEachRow(barrier, numbers, i, results);
			new Thread(rowCalculator).start();
		}		
	}
}

class CalculateEachRow implements Runnable {

	final int[][] numbers;
	final int rowNumber;
	final int[] res;
	final CyclicBarrier barrier;
	
	CalculateEachRow(CyclicBarrier barrier, int[][] numbers, int rowNumber, int[] res) {
		this.barrier = barrier;
		this.numbers = numbers;
		this.rowNumber = rowNumber;
		this.res = res;
	}
	
	@Override
	public void run() {
		int[] row = numbers[rowNumber];
		int sum = 0;
		for (int data : row) {
			sum += data;
			res[rowNumber] = sum;
		}
		try {
			System.out.println(Thread.currentThread().getName() + ": 计算第" + (rowNumber + 1) + "行结束，结果为: " + sum);
			barrier.await(); //等待！只要超过3个(Barrier的构造参数)，就放行。
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}
	
}


class CalculateFinalResult implements Runnable {
	final int[] eachRowRes;
	int finalRes;
	public int getFinalResult() {
		return finalRes;
	}
	
	CalculateFinalResult(int[] eachRowRes) {
		this.eachRowRes = eachRowRes;
	}
	
	@Override
	public void run() {
		int sum = 0;
		for(int data : eachRowRes) {
			sum += data;
		}
		finalRes = sum;
		System.out.println("最终结果为: " + finalRes);
	}
	
}
