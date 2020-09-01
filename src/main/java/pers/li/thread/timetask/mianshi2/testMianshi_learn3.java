package pers.li.thread.timetask.mianshi2;
/**
 * 写在同一个类中===========内部类共享外部类数据
 * 设计四个线程：
 * 两个线程每次对j加1
 * 两个线程每次对j减1
 * @author lishengbo
 * @Time 2017年12月2日上午10:11:19
 */
public class testMianshi_learn3 {

	private	int j=0;
	
	public synchronized  void increment(){
		j++;
		System.out.println(Thread.currentThread().getName()+"、、j++||"+j);
	}
	public synchronized  void decrement(){
		j--;
		System.out.println(Thread.currentThread().getName()+"、、j----||"+j);
	}
	public static void main(String[] args) {
		
		final testMianshi_learn3 ss=new testMianshi_learn3();
		
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

