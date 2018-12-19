package com.jxy.springMvc.dao;
import com.jxy.springMvc.entity.CustomerEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Mapper
@Repository
public interface CustomerDao extends BaseDao<CustomerEntity>{
	List<CustomerEntity> getList(@Param("customerName") String customerName, int begin, int end);
	int count(@Param("customerName") String customerName);
	void delete(long id);
	void update(CustomerEntity entity);
	void add(CustomerEntity entity);
	List<CustomerEntity> getCustomerEntitiesByName(@Param("customerName") String customerName);
}
