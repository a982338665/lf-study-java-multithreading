package pers.li.thread.timetask.threadlock;
/**
 * Hibernate中：
 * User user=session.load(id,User.calss);
 * 和
 * User user=session.get(id,User.calss);的区别
 * 
 * load返回的是一个代理类：
 * User$Proxy extends User{
 * 		private Integer id =id;
 * 		User readUser=null;
 * 		getName(){
 *			if(readUser==null){
 *				readUser=session.get(id);
 *				if(readUser==null){ 
 *					throw Exception;
 *				}
 *			} 
 *			return readUser.getName();
 * 		}
 * }
 * 
 * 所以，如果没有查出结果，
 * load会抛异常，get会返回null
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * @author lishengbo
 * @Time 2017年12月2日下午4:09:42
 */
public class introduce {

}
