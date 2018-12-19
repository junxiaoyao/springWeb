package com.jxy.springMvc.dao;

import com.jxy.springMvc.entity.RoleMenuEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleMenuDao {
	List<Long> getRoleMenu(long roleId);
	void add(List<RoleMenuEntity> list);
	void delete(long id);
}
