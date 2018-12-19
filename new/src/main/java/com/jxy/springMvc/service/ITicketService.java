package com.jxy.springMvc.service;

import com.jxy.springMvc.entity.TicketEntity;

import java.util.List;

public interface ITicketService extends IBaseInterface<TicketEntity>{
	void add(TicketEntity entity);
	void updatePic(long id, String pic);
	List<TicketEntity> getAllTicket();
	TicketEntity getOne(long id);
	void updateState(long id, long state);
	void update(TicketEntity entity);
	int countTicket(String customer, String begin, String end);
	double sumTurnover(String customer, String begin, String end);
	int selectTicket(String value, String column, String ticketId);
}
