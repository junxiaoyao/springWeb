package com.jxy.springMvc.service.impl;


import com.jxy.springMvc.dao.RoleMenuDao;
import com.jxy.springMvc.entity.RoleMenuEntity;
import com.jxy.springMvc.service.IRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpRoleMenuService implements IRoleMenuService {
	@Autowired
	private RoleMenuDao dao;
	@Override
	public void add(List<RoleMenuEntity> list) {
		// TODO Auto-generated method stub
		dao.add(list);
	}
	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}
	@Override
	public List<Long> getRoleMenu(long roleId) {
		// TODO Auto-generated method stub
		return dao.getRoleMenu(roleId);
	}

}
