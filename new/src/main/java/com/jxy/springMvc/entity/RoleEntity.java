package com.jxy.springMvc.entity;

import java.util.List;

public class RoleEntity {
	private long roleId;//ID
	private String roleName;//角色名字
    private List<Long> menuIdList;//权限列表
    private String createTime;//创建时间
    private String remark;//备注
    private long userId;//创建人ID
    private String userName;//创建人
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public List<Long> getMenuIdList() {
		return menuIdList;
	}
	public void setMenuIdList(List<Long> menuIdList) {
		this.menuIdList = menuIdList;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
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
    
}
