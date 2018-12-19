package com.jxy.springMvc.dao;

import com.jxy.springMvc.entity.MenuEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao extends BaseDao<MenuEntity>{
	List<MenuEntity> getList();
	List<MenuEntity> getRoleMenu(long roleId);
}
