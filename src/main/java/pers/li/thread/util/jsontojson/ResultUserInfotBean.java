package pers.li.thread.util.jsontojson;

import java.util.List;

public class ResultUserInfotBean {
	
	private String userId;
	private String openId;
	
	private String nickName;
	private String birthday;
	private String picture;
	private String sex;
	private String updateTime;
	
	private String scope;
	private String accessToken;
	private String email;
	private String expiresIn;
	private String userType;
	

	private List<ResultStudentInfotBean> studentData ;
	private ResultUserExtDatatBean userExtData  ;
	
	
	@Override
	public String toString() {
		return "ResultUserInfotBean [userId=" + userId + ", openId=" + openId
				+ ", nickName=" + nickName + ", birthday=" + birthday
				+ ", picture=" + picture + ", sex=" + sex + ", updateTime="
				+ updateTime + ", scope=" + scope + ", accessToken="
				+ accessToken + ", email=" + email + ", expiresIn=" + expiresIn
				+ ", userType=" + userType + ", studentData=" + studentData.toString()
				+ ", userExtData=" + userExtData + "]";
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(String expiresIn) {
		this.expiresIn = expiresIn;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public List<ResultStudentInfotBean> getStudentData() {
		return studentData;
	}
	public void setStudentData(List<ResultStudentInfotBean> studentData) {
		this.studentData = studentData;
	}
	public ResultUserExtDatatBean getUserExtData() {
		return userExtData;
	}
	public void setUserExtData(ResultUserExtDatatBean userExtData) {
		this.userExtData = userExtData;
	}
	

}
