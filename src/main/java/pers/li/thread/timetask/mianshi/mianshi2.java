package pers.li.thread.timetask.mianshi;


/**
 * 
 * @author lishengbo
 * @Time   2017年11月21日下午10:46:35
 * 子线程循环5次，主线程循环10次，如此往复循环50次
 */
public class mianshi2 {
	/**
	 * 错误示例：此种写法只创建了一个子线程，从始至终都只是子线程在执行
	 * @Time 2017年11月21日下午11:00:57
	 *
	 */
	public static void main(String[] args) {
		        final ThreadTest outer=new ThreadTest();
		        new Thread(new Runnable() {
		            @Override
		            public void run() {
		                for (int i = 1; i <=50; i++) {
		                    try {
		                        outer.outpute1(5,i);
		                        outer.outpute2(10,i);
		                    } catch (InterruptedException e) {
		                        // TODO Auto-generated catch block
		                        e.printStackTrace();
		                    }
		                 
		                }
		            }
		        }).start();
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
	            while(!is){//默认为true，若不是则等待,释放锁,让其他线程可进入，当前线程放入对象等待池
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
