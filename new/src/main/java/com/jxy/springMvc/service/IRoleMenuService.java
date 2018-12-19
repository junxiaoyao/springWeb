package com.jxy.springMvc.service;

import com.jxy.springMvc.entity.RoleMenuEntity;

import java.util.List;

public interface IRoleMenuService {
	//新增角色菜单数据
	void add(List<RoleMenuEntity> list);
	//删除
	void delete(long id);
	//获取角色菜单
	List<Long> getRoleMenu(long roleId);
}
