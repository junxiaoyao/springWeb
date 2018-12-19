package com.jxy.springMvc.entity;

public class RoleMenuEntity {
	private long roleMenuId;//Id
	private long roleId;//角色ID
	private long menuId;//菜单ID
	public long getRoleMenuId() {
		return roleMenuId;
	}
	public void setRoleMenuId(long roleMenuId) {
		this.roleMenuId = roleMenuId;
	}
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public long getMenuId() {
		return menuId;
	}
	public void setMenuId(long menuId) {
		this.menuId = menuId;
	}
}
