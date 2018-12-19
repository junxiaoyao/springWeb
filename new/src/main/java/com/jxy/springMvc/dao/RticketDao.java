package com.jxy.springMvc.dao;

import com.jxy.springMvc.entity.RticketEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RticketDao extends BaseDao<RticketEntity>{
	List<RticketEntity> getList(@Param("ticketNum") String ticketNum, @Param("customerName") String customerName, int pageNum, int size);
	int count(@Param("ticketNum") String ticketNum, @Param("customerName") String customerName);
	void add(RticketEntity entity);
	int countTicket(@Param("customerName") String customerName, int state, String begin, String end);
}
