package pers.li.thread.timetask;

import org.junit.Test;
import pers.li.thread.util.DateDetailUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
 
/**
 * 传统定时任务及线程
 *
 */
public class TraditionalTimerTask {
 
    /**
     * 定时任务：1秒后执行一次!
     * ***************************************************
     * @throws InterruptedException
     */
    @Test
    public void sdfsf() throws InterruptedException {
        new Timer().schedule(new TimerTask() {
 
            @Override
            public void run() {
                System.out.println("1秒后执行一次!");
            }
        }, 1000);
        Timer();
    };
    /**
     * 定时任务：3秒后执行第一次，之后每隔一秒执行一次!
     * ***************************************************
     * @throws InterruptedException
     */
    @Test
    public void sdfsfSAS() throws InterruptedException {
        new Timer().schedule(new TimerTask() {
 
            @Override
            public void run() {
                System.out.println("3秒后执行第一次，之后每隔一秒执行一次!");
            }
        }, 3000, 1000);
        Timer();
    };
    /**
     * 定时任务：2017-11-14 16:52:00 执行一次
     * ***************************************************
     * @throws InterruptedException
     */
    @Test
    public void sdfsfSAssdS() throws InterruptedException {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("2017-11-14 16:52:00 执行一次");
            }
        }, DateDetailUtil.getStringToDate("2017-11-14 16:52:50", null));
        Timer();
    };
    /**
     * 定时任务：2017-11-14 16:52:00 执行第一次，之后每隔24小时执行一次
     * ***************************************************
     * @throws InterruptedException
     */
    @Test
    public void sdfsfSAssdssdS() throws InterruptedException {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("2017-11-14 16:52:00 执行第一次，之后每隔24小时执行一次");
            }
        }, DateDetailUtil.getStringToDate("2017-11-14 16:52:50", null), 1000
                * 60 * 60 * 60 * 24);
        Timer();
    }
    /**
     * 间隔两秒-间隔四秒--循环执行************************************************
     */
    private static int count=0;//静态变量用来记录该类被实例化了多少次
    class MyTimerTask extends TimerTask{
        @Override
        public void run() {
            count=(count+1)%2;
            System.out.println("oommmmm-----");
            new Timer().schedule(new MyTimerTask(), 2000*(count+1));;
        }
         
    }
    @Test
    public void sdfsfSAsssaldS() throws InterruptedException {
        new MyTimerTask().run();
    };
    /**
     * 辅助计时方法
     * ***************************************************
     * @throws InterruptedException
     */
    private void Timer() throws InterruptedException {
        while (true) {
            for (int i = 1; i < 60; i++) {
                Thread.sleep(1000);
                ;
                System.out.println(i);
            }
        }
    };
    /**
     * new Thread子类创建一个线程
     * ***************************************************
     * @throws InterruptedException
     */
    @Test
    public void sdfsfSAssdsssadS() throws InterruptedException {
        // Thread thread=new Thread(){}
        // new 子类对象实现run方法，开启线程
        new Thread() {
            @Override
            public void run() {
                for (int i = 1; i < 60; i++) {
                    try {
                        this.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("new子类对象覆盖父类run方法："+i+"  |"
                            + Thread.currentThread().getName());
/*                  System.out.println("new子类对象覆盖父类run方法："+i+"  |"
                            + this.getName());
                    //可使用this代替指当前线程
*/              }
            };
        }.start();
        Timer();
    };
    /**
     * new Runnable为参数创建一个线程
     * ***************************************************
     * @throws InterruptedException
     */
    @Test
    public void sdfsfSAsssadS() throws InterruptedException {
        //不能使用this，因为此处的this指的是Runnable对象，而非Thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 60; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("Runnable参数传递run方法："+i+"  |"
                            + Thread.currentThread().getName());
                    //实际上Thread中的run方法调用的是Runnable的run方法，
                    //故直接重写Runnable的run方法也可以开启线程
                }
            }
        }).start();
        Timer();
    };
    /**
     * 先找Thread子类run方法，后找Runnable重写run方法
     * new Runnable为参数创建一个线程
     * ***************************************************
     * @throws InterruptedException
     */
    @Test
    public void sdfsfSAssdsassadS() throws InterruptedException {
        //new Thread(new Runnable(){//runnable重写run}){Thread子类重写run}.start();
        //以上方法会执行子类重写的run方法，因为他把runnable的run方法给覆盖了
        //如果Thread的子类未重写run，则执行Runnable重写的run方法
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i < 60; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("Runnable参数传递run方法："+i+"  |"
                            + Thread.currentThread().getName());
                    //实际上Thread中的run方法调用的是Runnable的run方法，
                    //故直接重写Runnable的run方法也可以开启线程
                }
            }
        }){
            @Override
            public void run() {
                for (int i = 1; i < 60; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    System.out.println("new子类对象覆盖父类run方法："+i+"  |"
                            + Thread.currentThread().getName());
                    //实际上Thread中的run方法调用的是Runnable的run方法，
                    //故直接重写Runnable的run方法也可以开启线程
                }
            }
         
        }.start();
        Timer();
    };
     
    /**
     * 静态方法中（包含main方法），不能实例化内部类：
     * 因为内部类可以访问该类成员变量，而非静态的成员变量是在类实例化的的时候才能被访问，而静态方法是在类加载时就可被调用
     * ***************************************************************
     *线程互斥开始
     */
    static class outputer{
        public void outpute22(String name){
            int len=name.length();
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
        }
        public void outpute(String name){
            int len=name.length();
            synchronized (this) {//this是指此类实例化后的对象,针对局部代码互斥
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }
        //方法上的synchronized也是针对于同一个该类实例化的对象，故与上一个方法互斥，同一把锁
        public synchronized void outpute1(String name){
            int len=name.length();
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
        }
        //静态方法在类加载时候加载，故该同步针对于此类，即.class文件
        public static synchronized void outpute2(String name){
            int len=name.length();
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
        }
        //与上一个方法，针对同一个对象，同一把锁，互斥
        public  void outpute3(String name){
            int len=name.length();
            synchronized (outputer.class) {//this是指此类实例化后的对象
                for (int i = 0; i < len; i++) {
                    System.out.print(name.charAt(i));
                }
                System.out.println();
            }
        }
    }
    /**
     * *****************************************************
     * 创建两个线程--公用一个对象，产生统一种锁来达到互斥效果
     */
    private void initi(){
        //同一個對象，不同的參數，為防止數據問題，需要加同步代碼款來互斥，防止數據錯亂
        final outputer outer=new outputer();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    outer.outpute("qwekjhkhkrty");
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    outer.outpute1("123999999945");
                }
            }
        }).start();
     
    }
    /**
     * 面试题：
     * 子线程循环10次，主线程循环20次，然后继续子10次，主20次，如此循环50次
     * ***********************************************************
     * 以下示例仅创建了一个线程Thread-0，故此种方式等同于单线程顺序执行
     */
    private void test() {
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
    private void test2() {
        final ThreadTest outer=new ThreadTest();
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
                System.out.println("子线程："+(i+1)+"||-"+count1);
            }
            is=false;
            this.notify();//通知其他线程执行，从对象等待池中移走任意一个线程
//          this.notifyAll();//移走所有线程
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
                System.out.println("主线程："+i+"_---------"+"||-"+count1);
            }
            is=true;
            this.notify();
        }
    }
    /**
     * 创建三个线程，则需要写三次******************************************
     */
    @Test
    public void sdfsfs(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
    /**
     * 线程范围内的共享变量：****************************************
     * 共享同一个对象，不同的数据，
     * 每一条线程所执行的事物均要独立（包括提交或是回滚，都不能影响其他线程）
     */
    @Test
    public void sdfss(){
//      for (int i = 0; i < 2; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                     
                    data= 121412414;
                    map.put(Thread.currentThread(), data);//保证存什么数据，就拿到什么数据
            System.out.println("================================");
                    System.out.println(Thread.currentThread().getName()+"||"+data);
                    new ttestShareA().A();
                    new ttestShareB().B();
                }
            }).start();
            new Thread(new Runnable() {
                @Override
                public void run() {
                     
                    data= 12313123;
                    map.put(Thread.currentThread(), data);
                    System.out.println("================================");
                    System.out.println(Thread.currentThread().getName()+"||"+data);
                    new ttestShareA().A();
                    new ttestShareB().B();
                }
            }).start();
        }
//  }
    private static int data=0;
    private static Map<Thread,Integer> map=new HashMap<Thread,Integer>();
    class ttestShareA{
        public void A(){
            int data=map.get(Thread.currentThread());//转换为局部变量取值
            System.out.println("A__"+Thread.currentThread().getName()+"-||-"+data);
        }
    }
    class ttestShareB{
        public void B(){
            int data=map.get(Thread.currentThread());
            System.out.println("B__"+Thread.currentThread().getName()+"-||-"+data);
        }
    }
    /**
     * main方法测试*********************************************
     * TODO
     * @param args
     */
    public static void main(String[] args) {
        //測試數據互斥
//      new TraditionalTimerTask().initi();
        //面试题-单线程顺序执行
//      new TraditionalTimerTask().test();
        //创建两个线程进行互斥和通讯
        new TraditionalTimerTask().test2();
         
    }
}
