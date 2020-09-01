package pers.li.thread.timetask.mianshi;

/**
 * 正确写法-----外部类方式实现
 * （此类中融合两个无关的类--仅用于示例）
 * @author lishengbo
 * @Time   2017年11月21日下午11:03:50
 * 子线程循环5次，主线程循环10次，如此往复循环50次
 * 由题意说明，子线程，主线程各需要执行50次：
 * 子线程每次循环5次执行完毕
 * 主线程每次循环10次执行完毕
 */
public class mianshiEx {

	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 50; i++) {
					try {
						huchitongxin3.zi();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

		for (int i = 0; i < 50; i++) {
			try {
				huchitongxin3.main();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}

class huchitongxin3{
	private static boolean is=true;
	public static synchronized void zi() throws InterruptedException {
		System.err.println("zi线程开始等待："+is);
		while(!is){
			huchitongxin3.class.wait();
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(Thread.currentThread().getName()+"|---"+(i+1));
		}
		is=false;
		huchitongxin3.class.notify();
		
	}

	public static synchronized void main() throws InterruptedException {
		System.err.println("主线程开始等待："+is);
		while(is){
			huchitongxin3.class.wait();
		}
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName()+"|"+(i+1));
		}
		is=true;
		huchitongxin3.class.notify();
	}
}
