package com.jxy.springMvc.service;

import java.util.List;

public interface IBaseInterface <E>{
	//查询数据数量
	int count(E e);
	//获取某页数据
	List<E> getList(E e, int pageNum, int size);
}
