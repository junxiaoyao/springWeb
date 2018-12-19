package com.jxy.springMvc.entity;



//
public class UserEntity {
	private long userId;//ID
	private String userName;//用户名
	private String realName;//真实姓名
	private String password;//密码
	private String sex;//性别
	private String userPhone;//联系方式
	private String email;//email
	private long roleId;//角色ID
	private String createTime;//创建时间
	private String roleName;//角色名
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "UserEntity [userId=" + userId + ", userName=" + userName
				+ ", realName=" + realName + ", password=" + password
				+ ", userPhone=" + userPhone + ", email=" + email + ", roleId="
				+ roleId + ", createTime=" + createTime + "]";
	}
	
}
