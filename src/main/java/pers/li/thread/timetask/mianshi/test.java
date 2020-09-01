package pers.li.thread.timetask.mianshi;

public class test {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					try {
						huchitongxin.zi();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

		for (int i = 0; i < 50; i++) {
			try {
				huchitongxin.main();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class huchitongxin {
	private static boolean is=true;
	public static synchronized void zi() throws InterruptedException {
		System.err.println("zi线程开始等待："+is);
		while(!is){
			huchitongxin.class.wait();
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName()+"|---"+(i+1));
		}
		is=false;
		huchitongxin.class.notify();
		
	}

	public static synchronized void main() throws InterruptedException {
		System.err.println("主线程开始等待："+is);
		while(is){
			huchitongxin.class.wait();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+"|"+(i+1));
		}
		is=true;
		huchitongxin.class.notify();
	}
}
