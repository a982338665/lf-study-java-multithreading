package pers.li.thread.util;

/**
 * 时间解析参数类
 * @author lsb
 *
 */
public class DateBean {

	private Integer year;
	private Integer month;
	private Integer day;
	private Integer hour;
	private Integer minute;
	private Integer second;
	
	private Integer week;

	public DateBean() {
		// TODO Auto-generated constructor stub
	}
	public DateBean(Integer year, Integer month, Integer day, Integer hour,
			Integer minute, Integer second, Integer week) {
		super();
		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
		this.second = second;
		this.week = week;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getDay() {
		return day;
	}

	public void setDay(Integer day) {
		this.day = day;
	}

	public Integer getHour() {
		return hour;
	}

	public void setHour(Integer hour) {
		this.hour = hour;
	}

	public Integer getMinute() {
		return minute;
	}

	public void setMinute(Integer minute) {
		this.minute = minute;
	}

	public Integer getSecond() {
		return second;
	}

	public void setSecond(Integer second) {
		this.second = second;
	}

	public Integer getWeek() {
		return week;
	}

	public void setWeek(Integer week) {
		this.week = week;
	}
	@Override
	public String toString() {
		return "DateBean [year=" + year + ", month=" + month + ", day=" + day
				+ ", hour=" + hour + ", minute=" + minute + ", second="
				+ second + ", week=" + week + "]";
	}


	
	
	
	
}
