package pers.li.thread.timetask.callablefuture;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


/**
 * 简易理解
 * @author lishengbo
 * @Time 2017年12月2日下午2:53:08
 */
public class simpleCallable {

	public static void main(String[] args)  {
		ExecutorService threadPool = Executors.newFixedThreadPool(10);
		Future<String> future=threadPool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return "hello";
			}
		});
		System.out.println("wait.........");
		try {
			System.out.println("result:"+future.get());//有可能结果未返回，拿不到结果
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
//		System.out.println("result2:"+future.get(2,TimeUnit.SECONDS));//最多等两秒就开始取结果
	}
}
