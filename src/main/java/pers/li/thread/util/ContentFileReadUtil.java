package pers.li.thread.util;

import java.util.ResourceBundle;

/**
 * properties文件读取工具类
 * @author cjh
 *
 */
public class ContentFileReadUtil {

	/**
	 * properties文件读取工具类
	 * @param filename=包名(不包括src)+/+文件名称(不包括后缀)
	 * "SQLToUtil/sql"
	 * @return
	 */
	

	public static final String file_name = "./ApplicationResources.properties";
	
	static ResourceBundle resource=null;
	static{
		resource=ReadFile(file_name);
	}
	/**
	 * 读取并加载默认properties文件
	 * @param file_name
	 * @return
	 */
	public static ResourceBundle ReadFile(String file_name){		
		ResourceBundle resource = ResourceBundle.getBundle(file_name);
//		String key = resource.getString("sql"); 
//		System.out.println(key);
		return resource;
	}
	/**
	 * 获取默认properties文件的key值的对应值value
	 * @param param
	 * @return
	 */
	public static String GetFileContent(String param){		
		String key = resource.getString(param); 
//		System.out.println(key);
		return key;
	}
	//=====================================================
	/**
	 * 获取某properties文件的某个key值所对应的value值
	 * @param filename
	 * @param param
	 * @return
	 */
	public static String ReadFileByFileName(String filename,String param){		
		ResourceBundle resource = ResourceBundle.getBundle(filename);
		String key = resource.getString(param); 
//		System.out.println(key);
		return key;
	}
	/****************************************************************************
	 * ResourceBundle.getBundle()来读取properties文件
	 * 	//ResourceBundle resource = ResourceBundle.getBundle("com/mmq/test");
	 *  //test为属性文件名，放在包com.mmq下，如果是放在src下，直接用test即可  
	 ***************************************************************************/
public static void main(String[] args) {
	GetFileContent("getpackagechange");
}
	//=====================================================
	
////	public static void main(String[] args) {
////		FileReadUtil.GetFileContent("sql1");
////		System.err.println(FileReadUtil.ReadFileByFileName("SQLToUtil/sql", "sql2"));
//		
////		ResourceBundle resource = ResourceBundle.getBundle("SQLToUtil/sql");
////		Object ss=(Object)resource ;
////		System.out.println(ss.toString());
//		
////		ResourceBundle resource = ResourceBundle.getBundle("SQLToUtil/sql");
////		Object obj=(Object)resource ;
////		Class c=obj.getClass();
////		Object obj=c.newInstance();
////		Car car = (Car)obj;
////		Field[] fields = c.getDeclaredFields();//拿到数据成员
////		Method[] methods = c.getMethods();//拿到函数成员
////		System.out.println(fields.length);
////		System.out.println(methods.length);
////		for(Field f : fields){
////		    System.out.println("该类的内部变量有:"+f.getName());
////		}
////		for(Method m : methods) {
////		    System.out.println("该类的方法有:"+m.getName());
////		}
//		
//		
////	}
	/**
	 * import java.lang.reflect.Field;
	 * import java.lang.reflect.Method;
	 */
//	public static void reads(){
//		
//		Class c = Class.forName("AbstractClassTest.Car");//要包名+类名
//		Class c = Class.forName("AbstractClassTest.Car");//要包名+类名
//		ResourceBundle resource = ResourceBundle.getBundle("SQLToUtil/sql");
//		Object obj=(Object)resource ;
//		Class c=obj.getClass();
//		Object obj=c.newInstance();
//		Car car = (Car)obj;
//		Field[] fields = c.getDeclaredFields();//拿到数据成员
//		Method[] methods = c.getMethods();//拿到函数成员
//		System.out.println(fields.length);
//		System.out.println(methods.length);
//		for(Field f : fields){
//		    System.out.println("该类的内部变量有:"+f.getName());
//		}
//		for(Method m : methods) {
//		    System.out.println("该类的方法有:"+m.getName());
//		}
//}
	
	
	
//	//方法二：用来遍历对象属性和属性值
//	public static Map<String,String> readClassAttr(TestBean tb) throws Exception{
//	    Field[] fields=tb.getClass().getDeclaredFields();
//	    String keyList="";
//	    String valueList="";
//	    for(Field field:fields){
//	        field.setAccessible(true);  
//	        if(field.get(tb)!=null&&!"".equals(field.get(tb).toString())){
//	            keyList+=","+field.getName();
//	            if("a".equals(field.getName())){
//	                valueList+=","+"特殊格式哦";
//            }else{
//	                valueList+=","+field.get(tb);
//	            }
//	            System.out.println(field.getName()+"   "+field.get(tb).toString());
//	        }
//	    }
//	    Map<String,String> maps=new HashMap<String, String>();
//	    maps.put("keys", keyList);
//	    maps.put("values",valueList);
//	    return maps;
//	}
	
	
	
	
	
	
	
	
	

}
