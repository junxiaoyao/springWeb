package com.jxy.springMvc.service.impl;

import com.jxy.springMvc.service.IRoleService;
import com.jxy.springMvc.dao.RoleDao;
import com.jxy.springMvc.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpRoleService implements IRoleService{
	@Autowired
	private RoleDao dao;
	@Override
	public int count(RoleEntity e) {
		// TODO Auto-generated method stub
		return dao.count(e.getRoleName());
	}

	@Override
	public List<RoleEntity> getList(RoleEntity e, int pageNum,int size) {
		// TODO Auto-generated method stub
		return dao.getList(e.getRoleName(), pageNum, size);
	}

	@Override
	public List<RoleEntity> getAllList() {
		// TODO Auto-generated method stub
		return dao.getAllList();
	}

	@Override
	public void addRole(RoleEntity entity) {
		// TODO Auto-generated method stub
		dao.addRole(entity);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public void update(RoleEntity entity) {
		// TODO Auto-generated method stub
		dao.update(entity);
	}

}
