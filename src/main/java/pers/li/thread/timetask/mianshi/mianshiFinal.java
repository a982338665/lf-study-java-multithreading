package pers.li.thread.timetask.mianshi;


/**
 * 正确写法-----内部类方式实现
 * @author lishengbo
 * @Time   2017年11月21日下午11:03:50
 * 子线程循环5次，主线程循环10次，如此往复循环50次
 * 由题意说明，子线程，主线程各需要执行50次：
 * 子线程每次循环5次执行完毕
 * 主线程每次循环10次执行完毕
 */
public class mianshiFinal {

	public static void main(String[] args) {
		/**创建一个用来处理逻辑的类**/
        final ThreadTest outer=new ThreadTest();
        /**创建一个子线程**/
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i <=50; i++) {
                    try {
                        outer.outpute1(5,i);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                     
                }
            }
        }).start();
//      new Thread(new Runnable() {
//          @Override
//          public void run() {
        //此为主线程
                for (int i = 1; i <=50; i++) {
                    try {
                        outer.outpute2(10,i);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                     
                }
//          }
//      }).start();
    
	}
	  static class ThreadTest{
	        /**
	         * 通信协议：确定先后顺序
	         */
	        private boolean is=true;
	        /**
	         *子线程循环次数
	         * @throws InterruptedException 
	         */
	        public synchronized void outpute1(Integer count,Integer count1) throws InterruptedException{
	            while(!is){
	            //如果非true,则等待，否则向下执行，重置is=false，并通知其他线程
	            //默认为true，若不是则等待,释放锁,让其他线程可进入，当前线程放入对象等待池
	            //使用while而不用if的原因，防止伪唤醒，增加程序健壮性
	            //在未notify()之前伪唤醒，若方法未同步，就用while判断，避免进程不同步问题
	            //--自己理解：
	            //如果用if，在wait时，被通知notify，则不会再检查，直接向下执行
	            //如果用while，wait之后被唤醒，还要去重新验证条件是否成立，如不成立则继续等待，这样就
	            //防止了伪唤醒造成的数据不同步问题，通信条件均使用while更严格
	                this.wait(); 
	            }
	            for (int i = 0; i < count; i++) {
	                Thread.sleep(10);
	                System.out.println("子线程："+(i+1)+"||-"+count1+"---"+Thread.currentThread().getName());
	            }
	            is=false;
	            this.notify();//通知其他线程执行，从对象等待池中移走任意一个线程
//	          this.notifyAll();//移走所有线程
	        }
	        /**
	         *主线程循环次数
	         * @throws InterruptedException 
	         */
	        public synchronized void outpute2(Integer count,Integer count1) throws InterruptedException{
	            while(is){//默认为true，若不是则等待
	                this.wait(); 
	            }
	            for (int i = 0; i < count; i++) {
	                Thread.sleep(10); 
	                System.out.println("主线程："+i+"_---------"+"||-"+count1+"---"+Thread.currentThread().getName());
	            }
	            is=true;
	            this.notify();
	        }
	    }
}
