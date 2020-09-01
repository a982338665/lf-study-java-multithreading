package pers.li.thread.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateDetailUtil {
	
	/**
	 * 时区：东8区（北京时间）
	 */
	public static final String TIMEZONE_GMT8 = "GMT+08:00";

	/**
	 * 日期格式化模板 yyyy-MM-dd HH:mm:ss
	 */
	public static final String DATE_FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 日期格式化模板 yyyy-MM-dd
	 */
	public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";
	
	
	
	
	/**
	 * 日期增加-按年月日增加(当前时间+增加的月份)
	 * 
	 * @param date
	 * @param days
	 * @return java.util.Date
	 */
	public static String getDateIncrease(int year,int month,int day,String format) {//现在时间2017-04-17，mnt若为5，则2017-09-17

		Calendar c = Calendar.getInstance();
		//System.out.println(c.getFirstDayOfWeek());---当前是周几
		c.add(Calendar.YEAR,year);
		c.add(Calendar.MONTH, month);
		c.add(Calendar.DATE, day);
		if(format==null||"".equals(format)){
			format=DATE_FORMAT_LONG;
		}
		Date cal=c.getTime();
		String lastDate = getDateToString(format, cal);
		return lastDate;
	}


	/**
	 * Date类型转String
	 * @param format
	 * @param c
	 * @return
	 */
	public static  String getDateToString(String format, Date c) {
		if(format==null||"".equals(format)){
			format=DATE_FORMAT_LONG;
		}
		SimpleDateFormat sdf;
		String lastDate = null;
		try {
			//第一个参数为时间类型的格式，第二个为所在时区：也可写为Locale.US
		    sdf = new SimpleDateFormat(format,Locale.CHINA);
			lastDate = sdf.format(c);
		} catch (Exception e) {
			return "Date转String异常";
		}
		return lastDate;
	}
	

	/**
	 * String类型转Date
	 * @param str
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	public static Date getStringToDate(String str, String format)
			{
		if(format==null||"".equals(format)){
			if(str.trim().length()>12){
				format=DATE_FORMAT_LONG;
			}else{
				format=DATE_FORMAT_SHORT;
			}
		}
		SimpleDateFormat fmt;
		Date da = null;
		try {
			fmt = new SimpleDateFormat(format);
			da = fmt.parse(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return da;
		}
		return da;
	}
	

	

	/**
	 * 日期比较
	 * DATE1在DATE2之前返回1
	 * DATE1在DATE2之后返回-1
	 * @param DATE1
	 * @param DATE2
	 * @return
	 */
	public static int compare_date(String DATE1, String DATE2) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date dt1 = df.parse(DATE1);
			Date dt2 = df.parse(DATE2);
			if (dt1.getTime() > dt2.getTime()) {
				// System.out.println("dt1 在dt2前");
				return 1;
			} else if (dt1.getTime() < dt2.getTime()) {
				// System.out.println("dt1在dt2后");
				return -1;
			} else {
				return 0;
			}
		} catch (Exception e) {
		}
		return 0;
	}
	
	
	/**
	 * 获取系统当前时间(yyyy-MM-dd HH:mm:ss)--String 
	 * @return
	 */
	public static String getCurrentSimpleDate() {
		return DateDetailUtil.getDateToString(null,new Date() );		
	}
	
	
	
	/**
	 * 根据Date类型获取其年月日相关详情
	 * @param date
	 * @return
	 */
	public static DateBean getDateDetail(Date date){
		  DateBean d=new DateBean();
		  Calendar now2 = new GregorianCalendar(); 
		  now2.setTime(date); 	        
	      d.setDay(now2.get(Calendar.DAY_OF_MONTH));
	      if(now2.get(Calendar.AM_PM)==1){
	       	d.setHour(now2.get(Calendar.HOUR)+12);
	      }else{
	       	d.setHour(now2.get(Calendar.HOUR));
	      }        
	      d.setMinute(now2.get(Calendar.MINUTE));
	      d.setMonth(now2.get(Calendar.MONTH)+1);
	      d.setSecond(now2.get(Calendar.SECOND));
	      d.setWeek(now2.get(Calendar.DAY_OF_WEEK)-1);
	      d.setYear(now2.get(Calendar.YEAR));	
		return d;
	}
	
	
	
	
	/**
	 * 根据Date获取那天是周几
	 *0为周日，其余正常
	 *isChinese表示是否转化为汉语
	 * @return
	 */
	public static String getWeekOfNow(Date date,Boolean isChinese){
		Calendar now2 = new GregorianCalendar(); 
		now2.setTime(date); 
		int weeknum=now2.get(Calendar.DAY_OF_WEEK)-1;
		String cNWeek=String.valueOf(weeknum);
		if(isChinese==true){
			if(weeknum==1){
				cNWeek="星期一";		
			}else if(weeknum==2){
				cNWeek="星期二";	
			}else if(weeknum==3){
				cNWeek="星期三";			
			}else if(weeknum==4){
				cNWeek="星期四";			
			}else if(weeknum==5){
				cNWeek="星期五";			
			}else if(weeknum==6){		
				cNWeek="星期六";
			}else if(weeknum==0){			
				cNWeek="星期日";
			}
		}
		return cNWeek;
	}
	
	

	
	
	public static void main(String[] args) throws Exception {
		
	System.out.println(	DateDetailUtil.getDateIncrease(0, 0, 1, null));
//	System.out.println(	DateDetailUtil.getDateToString(new Date(), null));
//	System.out.println(	DateDetailUtil.getStrToDate("1wdad", null));
//	System.out.println(DateDetailUtil.getDateDetail(DateDetailUtil.getStrToDate("2017-04-17 13:41:14", "")).toString()	);
	System.out.println(	DateDetailUtil.getCurrentSimpleDate());
	Date s=getStringToDate("2017-04-23", null);
	System.out.println(getWeekOfNow(s,true));

//	String s="1994-03-25 12:00:00";
//	System.out.println(s.length());
//	System.out.println(s.substring(0,12));


	}
	
	

/*	public static void main(String[] args) throws ParseException  {
//		DateBean d=DateDetailUtil.getDateDetail(new Date());
//		System.out.println(d.getYear()+"-"+d.getMonth()+"-"+d.getDay()+"  "+d.getHour()+":"+d.getMinute()+":"+d.getSecond()+"   "+d.getWeek());
//		
		
		  SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟  
		  String dstr="2008-02-05 22:12:12";  
		  java.util.Date date=sdf.parse(dstr); 
		  DateBean dd=DateDetailUtil.getDateDetail(date);
		  System.out.println(dd.getYear()+"-"+dd.getMonth()+"-"+dd.getDay()+"  "+dd.getHour()+":"+dd.getMinute()+":"+dd.getSecond()+"   "+dd.getWeek());
		  if(dd.getMonth()==1&&dd.getDay()==1){		  
			  System.out.println(String.valueOf(dd.getYear()-1)+"-12-01 00:00:00");
			  System.out.println(String.valueOf(dd.getYear()-1)+"-01-01 00:00:00");
		  }
		  if(dd.getDay()==1){
			  if(dd.getMonth()==1){		  
				  System.out.println(String.valueOf(dd.getYear()-1)+"-12-01 00:00:00");
				  System.out.println(String.valueOf(dd.getYear()-1)+"-12-31 23:59:59");
			  }else{
				  System.out.println(String.valueOf(dd.getYear())+"-"+String.valueOf(dd.getMonth()-1)+"-01 00:00:00");
				  System.out.println(String.valueOf(dd.getYear())+"-"+String.valueOf(dd.getMonth()-1)+"-31 23:59:59");
			  }		  
		  }else{
			  System.out.println(String.valueOf(dd.getYear())+"-"+String.valueOf(dd.getMonth())+"-01 00:00:00");
			  System.out.println(String.valueOf(dd.getYear())+"-"+String.valueOf(dd.getMonth())+"-"+String.valueOf(dd.getDay()-1) +" 23:59:59");
		  }


	}*/
}
