package pers.li.thread.timetask.endmianshi;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class mianshiTest3 {

	
	public static void main(String[] args) {
		final BlockingQueue<String> que=new ArrayBlockingQueue<String>(4);
		for (int i = 0; i < 10; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						String take = que.take();
						doex2.dosome(take);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
		for (int i = 0; i <10; i++) {
//			new Thread(new Runnable() {
				
//				@Override
//				public void run() {
					// TODO Auto-generated method stub
//					doex2.dosome(new Random().nextInt(3)+"");
					String s="队列加入数据"+new Random().nextInt(3)+"";
					System.out.println(s);
					try {
						que.put(s);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
//				}
//			}).start();
		}
	}	 
	public static void main1(String[] args) {
		
		for (int i = 0; i <10; i++) {
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					doex2.dosome(new Random().nextInt(3)+"");
					
				}
			}).start();
		}
	}	 
}
class doex2{//
//	private static List<String> list=new ArrayList<String>();
	private static CopyOnWriteArrayList<String> list=new CopyOnWriteArrayList();
	public static String dosome(String in){
		Object huchi=in;
		//若集合中已经存在该值，则互斥对象从集合中提取
		//因为是多线程，所以在迭代时，不能在里面加数据，否则有时候会报错,故使用list要用同步工具
		if(list.contains(in)){
			for (String string : list) {
				try {
					Thread.sleep(20);//此行代码可以使错误显示出来
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(string.equals(in)){
					huchi=string;
				}
			}
		}else{
			//因为是多线程，所以在迭代时，不能在里面加数据，否则有时候会报错
			list.add(in);
		}
		synchronized (huchi) {//非同一个对象不互斥--传进来时候由于参数不确定，不能互斥要做处理
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String out=Thread.currentThread().getName()+"|"+in+":"+(System.currentTimeMillis()/1000);
			System.out.println(out);
			return out;

		}
	}
}
