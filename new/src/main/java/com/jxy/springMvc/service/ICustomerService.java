package com.jxy.springMvc.service;

import com.jxy.springMvc.entity.CustomerEntity;

import java.util.List;

public interface ICustomerService extends IBaseInterface<CustomerEntity>{
	//删除客户
	void delete(long id);
	//更新客户信息
	void update(CustomerEntity entity);
	//添加客户信息
	void add(CustomerEntity entity);
	//根据客户名获取客户
	List<CustomerEntity> getCustomerEntitiesByName(String customerName);
}
