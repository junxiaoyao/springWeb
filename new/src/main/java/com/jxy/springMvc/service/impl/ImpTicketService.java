package com.jxy.springMvc.service.impl;

import com.jxy.springMvc.service.ITicketService;
import com.jxy.springMvc.dao.TicketDao;
import com.jxy.springMvc.entity.TicketEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImpTicketService implements ITicketService{
	@Autowired
	private TicketDao dao;
	@Override
	public int count(TicketEntity e) {
		// TODO Auto-generated method stub
		return dao.count(e.getTicketNum(), e.getCustomerName());
	}

	@Override
	public List<TicketEntity> getList(TicketEntity e, int begin, int end) {
		// TODO Auto-generated method stub
		return dao.getList(e.getTicketNum(), e.getCustomerName(), begin, end);
	}

	@Override
	public void add(TicketEntity entity) {
		// TODO Auto-generated method stub
		 dao.add(entity);
	}

	@Override
	public void updatePic(long id, String pic) {
		// TODO Auto-generated method stub
		dao.updatePic(id, pic);
	}

	@Override
	public List<TicketEntity> getAllTicket() {
		// TODO Auto-generated method stub
		return dao.getAllTicket();
	}

	@Override
	public TicketEntity getOne(long id) {
		// TODO Auto-generated method stub
		return dao.getOne(id);
	}

	@Override
	public void updateState(long id, long state) {
		// TODO Auto-generated method stub
		dao.updateState(id, state);
	}

	@Override
	public int countTicket( String customer, String begin,
			String end) {
		// TODO Auto-generated method stub
		return dao.countTicket(customer, begin, end);
	}

	@Override
	public void update(TicketEntity entity) {
		// TODO Auto-generated method stub
		dao.update(entity);
	}

	@Override
	public double sumTurnover(String customer, String begin, String end) {
		// TODO Auto-generated method stub
		double result=(double)Math.round(dao.sumTurnover(customer,begin, end).getMoneyAccount()*100)/100;
		return result;
	}

	@Override
	public int selectTicket(String value, String column,String ticketId) {
		// TODO Auto-generated method stub
		return dao.selectTicket(value, column, ticketId);
	}

}
