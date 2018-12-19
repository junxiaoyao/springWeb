package com.jxy.springMvc.service;

import com.jxy.springMvc.entity.RticketEntity;
import org.apache.ibatis.annotations.Param;

public interface IRticketService extends IBaseInterface<RticketEntity>{
	//新增代开票信息
	void add(RticketEntity entity);
	//查询数量
	int countTicket(@Param("customerName") String customerName, int state, String begin, String end);
}
