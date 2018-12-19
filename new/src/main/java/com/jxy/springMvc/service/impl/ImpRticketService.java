package com.jxy.springMvc.service.impl;

import com.jxy.springMvc.service.IRticketService;
import com.jxy.springMvc.dao.RticketDao;
import com.jxy.springMvc.entity.RticketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpRticketService implements IRticketService{
	@Autowired
	private RticketDao dao;
	@Override
	public int count(RticketEntity e) {
		// TODO Auto-generated method stub
		return dao.count(e.getTicketNum(),e.getCustomerName());
	}

	@Override
	public List<RticketEntity> getList(RticketEntity e, int pageNum, int size) {
		// TODO Auto-generated method stub
		return dao.getList(e.getTicketNum(),e.getCustomerName(),pageNum, size);
	}

	@Override
	public void add(RticketEntity entity) {
		// TODO Auto-generated method stub
		dao.add(entity);
	}

	@Override
	public int countTicket( String customerName, int state,
			String begin, String end) {
		// TODO Auto-generated method stub
		return dao.countTicket(customerName, state, begin, end);
	}

	

}
