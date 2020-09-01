package pers.li.thread.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/***
 * 判断文件所属 的MIME类型
 * @author lishengbo
 * @Time 2017年12月7日下午5:24:44
 */
public class GetMiMETypeUtil {
	
	/**
	 * 带拓展名的文件名称JDK1.7及以上支持
	 * @param filename
	 * @return
	 * @throws IOException 
	 */
	public static String getMiMeType(String filename) throws IOException{
		
		  String type = null;  
		  Path path = Paths.get(filename);  
		  System.out.println(Files.probeContentType(path)); ;
		
		return type;
		
	}
}
