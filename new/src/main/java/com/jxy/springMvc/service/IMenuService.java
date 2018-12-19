package com.jxy.springMvc.service;

import com.jxy.springMvc.entity.MenuEntity;

import java.util.List;

public interface IMenuService {
	//获取菜单列表
	List<MenuEntity> getList();
	//获取某角色菜单
	List<MenuEntity> getRoleMenu(long roleId);
}
