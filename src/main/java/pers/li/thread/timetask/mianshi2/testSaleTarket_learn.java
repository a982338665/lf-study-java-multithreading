package pers.li.thread.timetask.mianshi2;


/**
 * 如果每个线程代码相同，可使用同一个Runnable对象，此Runnable对象中包含那个共享数据
 * 卖票--5个窗口，卖共有的100张票
 * @author lishengbo
 * @Time 2017年12月2日上午10:11:19
 */
public class testSaleTarket_learn {
	public static void main(String[] args) {
		//每个线程都使用同一个对象
		Tarket2 ta=new Tarket2();
		//分配10个线程执行此对象run方法
		for (int i = 0; i < 10; i++) {
			new Thread(ta).start();
		}
	}
}


class Tarket2 implements Runnable{

	private static int s=100;
//	
//	
//	public synchronized static void sale(){
//		if(s==0) return;
//		s--;
//		System.out.println(Thread.currentThread().getName()+"|余票："+s);
//	}


	@Override
	public void run() {
		while(true){
			if(s==0){
				return;
			}
			s--;
		System.out.println(Thread.currentThread().getName()+"|余票："+s);
		}
	}
	
	
}
