package com.jxy.springMvc.dao;

import com.jxy.springMvc.entity.TicketEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketDao extends BaseDao<TicketEntity>{
	List<TicketEntity> getList(@Param("ticketNum") String ticketNum, @Param("customerName") String customerName, int begin, int end);
	int count(@Param("ticketNum") String ticketNum, @Param("customerName") String customerName);
	void add(TicketEntity entity);
	void updatePic(long id, String pic);
	void update(TicketEntity entity);
	List<TicketEntity> getAllTicket();
	TicketEntity getOne(long id);
	void updateState(long id, long state);
	int countTicket(@Param("customerName") String customer, String begin, String end);
	TicketEntity sumTurnover(@Param("customerName") String customer, String begin, String end);
	int selectTicket(@Param("value") String value, @Param("column") String column, @Param("ticketId") String ticketId);
}
