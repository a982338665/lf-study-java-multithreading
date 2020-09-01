package pers.li.thread.timetask.mianshi2;
/**
 * 共享数据+new Runnable的子类分离====
 * 设计四个线程：
 * 两个线程每次对j加1
 * 两个线程每次对j减1
 * @author lishengbo
 * @Time 2017年12月2日上午10:11:19
 */
public class testMianshi_learn2 {

	public static void main(String[] args) {
		
		final sharedata4 ss=new sharedata4();
		for (int i = 0; i < 2; i++) {
			new Thread(new runn1(ss)).start();
		}
		
		for (int i = 0; i < 2; i++) {
			new Thread(new runn2(ss)).start();
		}
		
		/*for (int i = 0; i < 2; i++) {
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
		}*/
		
		
	}
}
class runn1 implements Runnable{
	private sharedata4 share;
	public runn1(sharedata4 share){
		this.share=share;
	}
	@Override
	public void run() {
		share.increment();
	}
	
}
class runn2 implements Runnable{
	private sharedata4 share;
	public runn2(sharedata4 share){
		this.share=share;
	}
	@Override
	public void run() {
		share.decrement();
	}
	
}

class sharedata4{
	private	int j=0;
	
	public synchronized void increment(){
//		System.err.println(Thread.currentThread().getName()+"、此时j的zhi为:"+j);
		j++;
		System.out.println(Thread.currentThread().getName()+"、、j++||"+j);
	}
	public synchronized void decrement(){
//		System.err.println(Thread.currentThread().getName()+"、此时j的zhi为:"+j);
		j--;
		System.out.println(Thread.currentThread().getName()+"、、j----||"+j);
	}
	
	
}
