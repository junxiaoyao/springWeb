package com.jxy.springMvc.dao;

import com.jxy.springMvc.entity.RoleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleDao extends BaseDao<RoleEntity> {
	//List<RoleEntity> getListByRoleId(long id);
	List<RoleEntity> getList(@Param("roleName") String roleName, int pageNum, int size);
	int count(@Param("roleName") String roleName);
	List<RoleEntity> getAllList();
	void delete(long id);
	void update(RoleEntity entity);
	void addRole(RoleEntity entity);
}
