package pers.li.thread.timetask.synccollection;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 同步工具集合--防止并发问题:对于线程不安全的集合一定要注意，读写时保障线程安全
 * @author lishengbo
 * @Time   2017年12月9日下午8:39:54
 *
 */
public class synchCollectionTest {

	/**
	 * 在进行集合迭代的时候，不能够进行对象移出操作：--以下为迭代器示例
	 * java.util.ConcurrentModificationException
	 * @Time 2017年12月9日下午8:52:06
	 *
	 */
	@Test
	public  void arrayListTest() {
		Collection s=new ArrayList();
		s.add("1");
		s.add("2");
		s.add("3");
		Iterator dd=s.iterator();
		while (dd.hasNext()) {
			Object object = (Object) dd.next();
			if(object.toString().equals("2")){
				s.remove(object);
			}else{
				System.out.println(object.toString());

			}

		}
	}

	/**
	 * 
	 * 在进行集合迭代的时候，不能够进行对象移出操作：--以下为增强for示例
	 * java.util.ConcurrentModificationException
	 * @Time 2017年12月9日下午8:52:06
	 *
	 */
	@Test
	public  void arrayListTest1() {
		Collection s=new ArrayList();
		s.add("1");
		s.add("2");
		s.add("3");
		for (Object object : s) {
			if(object.toString().equals("1")){
				s.remove(object);
			}else{
				System.out.println(object.toString());
				
			}
		}
//		Iterator dd=s.iterator();
//		while (dd.hasNext()) {
//			Object object = (Object) dd.next();
//			if(object.toString().equals("2")){
//				s.remove(object);
//			}else{
//				System.out.println(object.toString());
//				
//			}
			
//		}
	}
	/**
	 * 
	 * 在进行集合迭代的时候，不能够进行对象移出操作：--以下为增强for示例
	 * java.util.ConcurrentModificationException
	 * @Time 2017年12月9日下午8:52:06
	 *
	 */
	@Test
	public  void arrayListTest2() {
		List s=new ArrayList();
		s.add("1");
		s.add("2");
		s.add("3");
		for (Object object : s) {
			if(object.toString().equals("2")){
				s.remove(object);
			}else{
				System.out.println(object.toString());
				
			}
		}
	}
	@Test
	public  void arrayListTest3() {
		Map s=new HashMap();
		s.put("1","1");
		s.put("2","2");
		s.put("3","3");
		Set keySet = s.keySet();
		for (Object object : keySet) {
			if(object.toString().equals("2")){
				s.remove(object);
			}else{
				System.out.println(object.toString());
			}
		}
	}
	@Test
	public  void arrayListTest4() {
		Hashtable s=new Hashtable();
		s.put("1","1");
		s.put("2","2");
		s.put("3","3");
		Enumeration keySet = s.keys();
	}
	@Test
	public  void arrayListTest5() {
		List s=new CopyOnWriteArrayList();
		s.add("1");
		s.add("2");
		s.add("3");
		for (Object object : s) {
			if(object.toString().equals("2")){
				s.remove(object);
			}else{
				System.out.println(object.toString());
				
			}
		}
	}
}
