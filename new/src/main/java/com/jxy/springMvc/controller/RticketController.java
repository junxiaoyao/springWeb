package com.jxy.springMvc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jxy.springMvc.service.IRticketService;
import com.jxy.springMvc.service.ITicketService;
import com.jxy.springMvc.entity.RticketEntity;
import com.jxy.springMvc.entity.TicketEntity;
import com.jxy.springMvc.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class RticketController {
	@Autowired
	private IRticketService service;
	@Autowired
	private ITicketService ticketService;
	//退废票页面
	@RequestMapping(value="/getRticketPage")
	public String getRticketPage(HttpServletRequest request){
		return "rTicket/rTicketList.html";
	}
	//获取退废票数据
	@RequestMapping(value="/getRticketList")
	public void getList(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		String customerName = request.getParameter("customerName");
		String ticketNum = request.getParameter("ticketNum");
		RticketEntity e=new RticketEntity();
		e.setCustomerName(customerName);
		e.setTicketNum(ticketNum);
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		List<RticketEntity> list=service.getList(e, (pageNum-1)*10, 10);
		try {
			out = response.getWriter();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		if (list != null) {
			jObject.put("data", list);
			jArray.add(jObject);
			out.print(jObject);
		}
	}
	//获取退废票数量
	@RequestMapping(value="/getRticketCount")
	public void getCount(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		String customerName = request.getParameter("customerName");
		String ticketNum = request.getParameter("ticketNum");
		RticketEntity e=new RticketEntity();
		e.setCustomerName(customerName);
		e.setTicketNum(ticketNum);
		int count=service.count(e);
		try {
			out = response.getWriter();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("count", count);
		jArray.add(jObject);
		out.print(jObject);
	}
	//添加退废票
	@RequestMapping(value="/addRticket")
	public void addRticket(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		HttpSession session=request.getSession();
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		PrintWriter out = null;
		long ticketId =Long.parseLong(request.getParameter("ticketId"));
		String type =request.getParameter("type");
		String remark = request.getParameter("remark");
		RticketEntity entity=new RticketEntity();
		TicketEntity ticketEntity=ticketService.getOne(ticketId);
		entity.setTicketId(ticketId);
		entity.setRemark(remark);
		entity.setCustomerId(ticketEntity.getCustomerId());
		entity.setType(type);
		entity.setUserId(userEntity.getUserId());
		
		try {
			service.add(entity);
			ticketService.updateState(ticketId, Long.parseLong(type));
			out = response.getWriter();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		JSONArray jArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		jObject.put("msg", 1);
		jArray.add(jObject);
		out.print(jObject);
	}
}
