package pers.li.thread.timetask.mianshi;
/**
 * 
 * @author lishengbo
 * @Time   2017年11月21日下午10:13:18
 * 子线程循环5次，主线程循环10次，如此往复循环50次
 * 
 */
public class mianshi {
	/**
     * 通信协议：确定先后顺序
     */
    private static boolean is=true;
	private static ThreadLocal<Integer> map=new ThreadLocal<Integer>();
	/**
	 * 错误示例：此种写法创建了50个子线程，一个主线程，50个子线程依次执行，不满足条件
	 * @Time 2017年11月21日下午11:02:20
	 *
	 */
	public static void main(String[] args) throws InterruptedException {
		/**开了50个子线程===**/
		for (int i = 0; i < 50; i++) {
//			map.set(i);
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						zi();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
			
			synchronized (mianshi.class) {
				while(is){
					mianshi.class.wait();
				}
				for (int j = 0; j < 10; j++) {
					System.out.println("main:"+(j+1)+"---|"+i+"---|"+Thread.currentThread().getName());
					
			}
				is=true;
				mianshi.class.notify();
			}
			
			
			
		}
	}
	public synchronized static void zi() throws InterruptedException{
		while(!is){
			mianshi.class.wait();
		}
		for (int i = 0; i < 5; i++) {
			System.out.println("ZI____"+(i+1)+"-|--"+Thread.currentThread().getName());
		}
		is=false;
		mianshi.class.notify();
	}
}
