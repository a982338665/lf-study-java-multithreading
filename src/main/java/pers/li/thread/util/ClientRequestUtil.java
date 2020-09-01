package pers.li.thread.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 
 * @author zkl
 * @date 2014-8-4 下午3:56:47
 * @version 1.0
 */
public class ClientRequestUtil {
	private static Logger logger = Logger.getLogger(ClientRequestUtil.class);
	private String outStr;
	
	public String getOutStr() {
		return outStr;
	}

	

	
	public void sendRequest(String url1,String requestMethod){
		sendRequest(url1, requestMethod, null, null);
	}
	
	public void sendRequest(String url1,String requestMethod,String data){
		sendRequest(url1, requestMethod, data, null);
	}
	
	public void sendRequest(String url1,String requestMethod,Map<String,String> headParamMap){
		sendRequest(url1, requestMethod, null, headParamMap);
	}
	
	private void sendRequest(String url1,String requestMethod,String data,Map<String,String> headParamMap){
		
		try {
			
			URL url = new URL(url1);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("Accept", "application/json");
			if(headParamMap!=null && !headParamMap.isEmpty()){
				for(String key:headParamMap.keySet()){
					String value = headParamMap.get(key);
					conn.addRequestProperty(key, value);
				}
			}
			
			if(data!=null && data.trim().length()>0){
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type", "application/json");
				String input = data;
				
				System.out.println("outputString:"+input+"\n");

				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes("UTF-8"));
				os.flush();
			}
			
			if (conn.getResponseCode() != 200) {
				System.out.println("-----httpcode-------"+conn.getResponseCode());
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream()),"UTF-8"));
			
			String output;
			System.out.println("Output from Server .... \n");
			while ((output = br.readLine()) != null) {
				this.outStr=output;
				System.out.println(output);
			}
			
			conn.disconnect();
			
		} catch (MalformedURLException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			
			e.printStackTrace();
			
		}
	}
	
	public String sendUrl(){
		
		return null;
	}
	
	
	public static String post(String url,String data){
		return request(url, "POST", data, null);
	}
	
	public static String request(String url1, String requestMethod,String data,Map<String,String> headParamMap){
		String operation = "url=>"+url1+"==requestmethod=>"+requestMethod+"==senddata=>"+data;
		StringBuilder sb = new StringBuilder();
		try {
			
			URL url = new URL(url1);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("Accept", "application/json");
			if(headParamMap!=null && !headParamMap.isEmpty()){
				for(String key:headParamMap.keySet()){
					String value = headParamMap.get(key);
					conn.addRequestProperty(key, value);
				}
			}
			
			if(data!=null && data.trim().length()>0){
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type", "application/json");
				String input = data;
				logger.debug("发送数据:"+operation);
				OutputStream os = conn.getOutputStream();
				os.write(input.getBytes("UTF-8"));
				os.flush();
			}
			
			if (conn.getResponseCode() != 200) {
				logger.error("请求失败:响应码为"+conn.getResponseCode()+" |operation==>"+operation);
				throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
			}
			
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream()),"UTF-8"));
			String output;
			while ((output = br.readLine()) != null) {
				sb.append(output);
			}
			
			conn.disconnect();
			
		} catch (Exception e) {
			logger.error("请求失败:"+operation, e);
		} 
		String result = sb.toString();
		logger.debug("请求结果:"+result+"   | 请求信息"+operation);
		return result;
	}
	
	
	
}
