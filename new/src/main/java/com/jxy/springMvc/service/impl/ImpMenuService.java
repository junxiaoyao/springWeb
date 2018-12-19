package com.jxy.springMvc.service.impl;

import com.jxy.springMvc.dao.MenuDao;
import com.jxy.springMvc.entity.MenuEntity;
import com.jxy.springMvc.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpMenuService implements IMenuService {
	@Autowired
	private MenuDao dao;
	@Override
	public List<MenuEntity> getList() {
		// TODO Auto-generated method stub
		return dao.getList( );
	}
	@Override
	public List<MenuEntity> getRoleMenu(long roleId) {
		// TODO Auto-generated method stub
		return dao.getRoleMenu(roleId);
	}

}
