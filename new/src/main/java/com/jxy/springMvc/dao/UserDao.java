package com.jxy.springMvc.dao;

import com.jxy.springMvc.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseDao<UserEntity>{
	UserEntity userLogin(String userName, String userPassword);
	int count(@Param("userName") String userName, @Param("realName") String realName);
	void update(UserEntity entity);
	List<UserEntity> getList(@Param("userName") String userName, @Param("realName") String realName, int begin, int end);
	int getUserByName(String userName, long id);
	void add(UserEntity entity);
	List<UserEntity> getAllList();
}
