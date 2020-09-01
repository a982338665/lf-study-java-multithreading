package pers.li.thread.timetask.threadpool;
/**
 * 模拟webservice：另起一个线程执行单独的任务
 * @author lishengbo
 * @Time 2017年12月2日下午1:34:46
 */
public class A_testRunnable {

	public static void main(String[] args) {
		new Thread(new A_shili2()).start();
	}
}
