package com.jxy.springMvc.service;

import com.jxy.springMvc.entity.UserEntity;

import java.util.List;

public interface IUserService extends IBaseInterface<UserEntity>{
	//用户登录
	UserEntity userLogin(String userName, String userPassword);
	//删除用户
	void delete(long id);
	//通过用户名查找用户
	int getUserByName(String userName, long id);
	//更新 用户
	void update(UserEntity entity);
	//添加用户
	void add(UserEntity entity);
	//查询所以用户
	List<UserEntity> getAllList();
}
