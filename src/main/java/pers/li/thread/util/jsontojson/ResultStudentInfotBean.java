package pers.li.thread.util.jsontojson;

public class ResultStudentInfotBean {

	
	private String userId;
	private String studentName;
	private String sex;
	private String birthday;
	private String kindred;
	private String updateTime;
	private String classId;
	private String thirdUserId;
	private String openId;
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getKindred() {
		return kindred;
	}
	public void setKindred(String kindred) {
		this.kindred = kindred;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getThirdUserId() {
		return thirdUserId;
	}
	public void setThirdUserId(String thirdUserId) {
		this.thirdUserId = thirdUserId;
	}
	@Override
	public String toString() {
		return "ResultStudentInfotBean [userId=" + userId + ", studentName="
				+ studentName + ", sex=" + sex + ", birthday=" + birthday
				+ ", kindred=" + kindred + ", updateTime=" + updateTime
				+ ", classId=" + classId + ", thirdUserId=" + thirdUserId
				+ ", openId=" + openId + "]";
	}


}
