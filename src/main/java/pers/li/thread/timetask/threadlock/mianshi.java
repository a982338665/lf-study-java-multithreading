package pers.li.thread.timetask.threadlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 伪代码写缓存系统==========Cach
 * 		相比之下：性能getData120>getData100
 * @author lishengbo
 * @Time 2017年12月2日下午4:26:20
 */
public class mianshi {

	private Map<String,Object>  cache=new HashMap<String, Object>();
	
	/**synchronized:假如有多个线程查询一个key值，同时到达if条件中，就会查询数据库多次，故加关键字仅查询一次**/
	public synchronized Object getData100(String key){
		Object value=cache.get(key);
		if(value==null){
			/****若在缓存中不存在则查询数据库*********************/
			value="---";
			/****若存在则将结果放入缓存***************************/
			cache.put(key, value);
		}
		return key;
		
	}
	/*****************************************************************************************/
	private ReadWriteLock cwl=new ReentrantReadWriteLock();
	public  Object getData120(String key){
		cwl.readLock().lock();//添加读锁
		Object value=null;
		try {
			value=cache.get(key);
			if(value==null){
				/****若在缓存中不存在则查询数据库*********************/
				cwl.readLock().unlock();//释放读锁
				cwl.writeLock().lock();//添加写锁，读取数据库，进行数据写入
				try {
					/***
					 * 此处加判断的原因是：
					 *   当有三个线程同时走到了添加写锁的时候，其中假设第一个写锁添加成功，剩下两个等待
					 *   那么，当第一个继续向下执行到释放写锁重新添加读锁时，此时，第二个线程开始进入try
					 *   那么如果没有以下判断，将会再次去查询数据库，重新写入数据。
					 *   而此时第一个线程在读锁，第二个线程在写锁，就会导致读写不互斥
					 */
					if(value==null){
						value="---";
					}
				}finally{
					cwl.writeLock().unlock();//释放写锁
				}
				cwl.readLock().lock();//添加读锁
				/****若存在则将结果放入缓存***************************/
				//				cache.put(key, value);
			}
		}finally{
			cwl.readLock().unlock();//释放读锁
		}
		return key;
		
	}
}
