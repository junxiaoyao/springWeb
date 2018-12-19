package com.jxy.springMvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.jxy.springMvc.service.ICustomerService;
import com.jxy.springMvc.entity.CustomerEntity;
import com.jxy.springMvc.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;


@Controller
public class CustomerController {
	@Autowired
	private ICustomerService service;
	//获取某页客户信息
	@RequestMapping(value="/getCustomerList")
	public void getList(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		String customerName = request.getParameter("customerName");
		CustomerEntity entity = new CustomerEntity();
		entity.setCustomerName(customerName);;
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		List<CustomerEntity> entitys = service.getList(entity, (pageNum-1)*10, 10);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		if (entitys != null) {
			jObject.put("data", entitys);
			out.print(jObject);
		}
	}
	//获取客户条数
	@RequestMapping(value="/getCustomerCount")
	public void getCount(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		String customerName = request.getParameter("customerName");
		CustomerEntity entity = new CustomerEntity();
		entity.setCustomerName(customerName);;
		int count=service.count(entity);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		jObject.put("count", count);
		out.print(jObject);
	}
	//跳转到客户列表
	@RequestMapping(value = "/getCustomerListPage")
	public String customerListPage(HttpServletRequest request) {
		return "customer/customerList.html";
	}
	//删除某客户
	@RequestMapping(value="/deleteCustomer/{id}")
	public void deleteCustomer(HttpServletRequest request,@PathVariable long id,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = null;
		service.delete(id);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		jObject.put("msg", "1");
		out.print(jObject);
		//long id=Integer.parseInt(request.getParameter("id"));
	
	}
	//更新客户
	@RequestMapping(value="/updateCustomer")
	public void updateCustomer(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = null;
		int type=1;
		CustomerEntity entity=new CustomerEntity();
		entity.setCustomerId(Integer.parseInt(request.getParameter("customerId")));
		entity.setCustomerAddress(request.getParameter("customerAddress"));
		entity.setCustomerName(request.getParameter("customerName"));
		entity.setCustomerPhone(request.getParameter("customerPhone"));
		entity.setEmail(request.getParameter("email"));
		entity.setCompany(request.getParameter("company"));
		service.update(entity);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			type=2;
		}
		JSONObject jObject = new JSONObject();
		jObject.put("msg", type);
		out.print(jObject);
	}
	//添加客户
	@RequestMapping(value="/addCustomer")
	public void addCustomer(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = null;
		int type=1;
		CustomerEntity entity=new CustomerEntity();
		entity.setCreateTime(DateUtils.formatT(new Date()));
		entity.setCustomerAddress(request.getParameter("customerAddress"));
		entity.setCustomerName(request.getParameter("customerName"));
		entity.setCustomerPhone(request.getParameter("customerPhone"));
		entity.setEmail(request.getParameter("email"));
		entity.setCompany(request.getParameter("company"));
		service.add(entity);
		try {
			out = response.getWriter();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			type=2;
		}
		JSONObject jObject = new JSONObject();
		jObject.put("msg", type);
		out.print(jObject);
	}
	//根据客户名查询客户
	@RequestMapping(value="/getCustomerByName")
	public void getCustomerByName(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = null;
		
		String name=request.getParameter("customerName");
		List<CustomerEntity> entitys =service.getCustomerEntitiesByName(name);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		if (entitys != null) {
			jObject.put("data", entitys);
			out.print(jObject);
		}
	}
}
