package pers.li.thread.timetask.mianshi2;


/**
 * * 如果每个线程代码相同，可使用同一个Runnable对象，此Runnable对象中包含那个共享数据
 * 卖票--5个窗口，卖共有的100张票
 * @author lishengbo
 * @Time 2017年12月2日上午10:11:19
 */
public class testSaleTarket {
	public static void main(String[] args) {
//		final Integer num=1000;
		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						Tarket.sale();
					}
				}
			}).start();
		}
	}
}


class Tarket{
//	private Tarket() {
		// TODO Auto-generated constructor stub
//	}
//	private static Tarket t=null;
	private static int s=100;
	
//	public static Tarket getinstance(){
//		if(t==null){
//			t=new Tarket();
//		}
//		return t;
//	}
//	
	public synchronized static void sale(int s){
		if(s==0) return;
		s--;
		System.out.println(Thread.currentThread().getName()+"|余票："+s);
	}
	
	public synchronized static void sale(){
		if(s==0) return;
		s--;
		System.out.println(Thread.currentThread().getName()+"|余票："+s);
	}
	
	
}
