package pers.li.thread.timetask.mianshi2;
/**
 * 共享数据分离分离出来=========
 * 设计四个线程：
 * 两个线程每次对j加1
 * 两个线程每次对j减1
 * @author lishengbo
 * @Time 2017年12月2日上午10:11:19
 */
public class testMianshi_learn {

	public static void main(String[] args) {
		
		final sharedata ss=new sharedata();
		
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					ss.increment();
				}
			}).start();
		}
		
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					ss.decrement();
				}
			}).start();
		}
		
		
	}
}

class sharedata{
	private	int j=0;
	
	public synchronized void increment(){
		j++;
		System.out.println(Thread.currentThread().getName()+"、、j++||"+j);
	}
	public synchronized  void decrement(){
		j--;
		System.out.println(Thread.currentThread().getName()+"、、j----||"+j);
	}
	
	
}
