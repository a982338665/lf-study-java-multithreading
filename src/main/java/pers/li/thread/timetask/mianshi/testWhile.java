package pers.li.thread.timetask.mianshi;
/**
 * 主线程处理完之后，定时处理隔一秒执行一次
 * @author lishengbo
 * @Time 2017年12月2日上午9:57:56
 */
public class testWhile {

	static boolean is=true;
	public static void main(String[] args) throws InterruptedException {
		System.out.println(Thread.currentThread().getName()+":主线程执行-------之后，创建一个子线程处理问题：");
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					while(is){
						System.out.println(Thread.currentThread().getName()+"sdfsfsdf");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						is=false;
					}
					
					while(!is){
						System.out.println(Thread.currentThread().getName()+"----------");
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						is=true;
					}
				}
			}
		}).start();
	}
}
