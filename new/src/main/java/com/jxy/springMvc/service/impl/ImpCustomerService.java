package com.jxy.springMvc.service.impl;
import com.jxy.springMvc.dao.CustomerDao;
import com.jxy.springMvc.entity.CustomerEntity;
import com.jxy.springMvc.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpCustomerService implements ICustomerService {
	@Autowired
	private CustomerDao dao;
	@Override
	public int count(CustomerEntity e) {
		// TODO Auto-generated method stub
		return dao.count(e.getCustomerName());
	}

	@Override
	public List<CustomerEntity> getList(CustomerEntity e, int begin, int end) {
		// TODO Auto-generated method stub
		return dao.getList(e.getCustomerName(),begin,end);
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		dao.delete(id);
	}

	@Override
	public void update(CustomerEntity entity) {
		// TODO Auto-generated method stub
		dao.update(entity);
	}

	@Override
	public void add(CustomerEntity entity) {
		// TODO Auto-generated method stub
		dao.add(entity);
	}

	@Override
	public List<CustomerEntity> getCustomerEntitiesByName(String customerName) {
		// TODO Auto-generated method stub
		return dao.getCustomerEntitiesByName(customerName);
	}

}
