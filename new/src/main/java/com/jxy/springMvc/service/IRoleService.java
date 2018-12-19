package com.jxy.springMvc.service;

import com.jxy.springMvc.entity.RoleEntity;

import java.util.List;

public interface IRoleService extends IBaseInterface<RoleEntity>{
	//获取所有角色
	List<RoleEntity> getAllList();
	//添加角色
	void addRole(RoleEntity entity);
	//删除角色
	void delete(long id);
	//更新角色
	void update(RoleEntity entity);
}
