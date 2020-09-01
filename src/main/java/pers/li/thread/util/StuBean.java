package pers.li.thread.util;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;



/**
 * 测试bean
 * @author cjh
 *
 */
public class StuBean {
	/**
	 * 注释id
	 */
	@Resource
	private String id;
	/**
	 * 注释名称
	 */
	private String name;
	private List<String> namee;
	private String[] strings; 
	private Map<String,String> map;
	private UserInfo userif;
	public UserInfo getUserif() {
		return userif;
	}
	public void setUserif(UserInfo userif) {
		this.userif = userif;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	public String[] getStrings() {
		return strings;
	}
	public void setStrings(String[] strings) {
		this.strings = strings;
	}
	public List<String> getNamee() {
		return namee;
	}
	public void setNamee(List<String> namee) {
		this.namee = namee;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "StuBean [id=" + id + ", name=" + name + "]";
	}
	
	
}
