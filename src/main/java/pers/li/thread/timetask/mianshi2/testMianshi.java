package pers.li.thread.timetask.mianshi2;
/**
 * 错误示例：------数据未共享）
 * 设计四个线程：
 * 两个线程每次对j加1
 * 两个线程每次对j减1
 * @author lishengbo
 * @Time 2017年12月2日上午10:11:19
 */
public class testMianshi {

	public static void main(String[] args) {
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					new jisuan().increment();
				}
			}).start();
		}
		
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					new jisuan().decrement();
				}
			}).start();
		}
		
		
	}
}

class jisuan{
	private	int j=0;
	
	public synchronized void increment(){
		j++;
		System.out.println(Thread.currentThread().getName()+"、、j++||"+j);
	}
	public synchronized void decrement(){
		j--;
		System.out.println(Thread.currentThread().getName()+"、、j----||"+j);
	}
	
	
}
