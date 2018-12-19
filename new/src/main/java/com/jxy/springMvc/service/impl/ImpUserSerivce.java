package com.jxy.springMvc.service.impl;

import com.jxy.springMvc.service.IUserService;
import com.jxy.springMvc.dao.UserDao;
import com.jxy.springMvc.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpUserSerivce implements IUserService{
	@Autowired
	private UserDao dao;
	@Override
	public UserEntity userLogin(String userName, String userPassword) {
		// TODO Auto-generated method stub
		return dao.userLogin(userName, userPassword);
	}
	@Override
	public int count(UserEntity e) {
		// TODO Auto-generated method stub
		return dao.count(e.getUserName(),e.getRealName());
	}
	@Override
	public List<UserEntity> getList(UserEntity e, int begin, int end) {
		// TODO Auto-generated method stub
		return dao.getList(e.getUserName(),e.getRealName(), begin, end);
	}
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}
	@Override
	public void update(UserEntity entity) {
		// TODO Auto-generated method stub
		dao.update(entity);
	}
	@Override
	public int getUserByName(String userName,long id) {
		// TODO Auto-generated method stub
		return dao.getUserByName(userName,id);
	}
	@Override
	public void add(UserEntity entity) {
		// TODO Auto-generated method stub
		dao.add(entity);
	}
	@Override
	public List<UserEntity> getAllList() {
		// TODO Auto-generated method stub
		return dao.getAllList();
	}

}
