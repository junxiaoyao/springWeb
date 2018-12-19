package com.jxy.springMvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.jxy.springMvc.service.ITicketService;
import com.jxy.springMvc.entity.TicketEntity;
import com.jxy.springMvc.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
public class TicketController {
	@Autowired
	private ITicketService service;

	// 代开票数据
	@RequestMapping(value = "/getTicketList")
	public void getList(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		String customerName = request.getParameter("customerName");
		String ticketNum = request.getParameter("ticketNum");
		TicketEntity entity = new TicketEntity();
		entity.setCustomerName(customerName);
		entity.setTicketNum(ticketNum);
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		List<TicketEntity> entitys = service.getList(entity,
				(pageNum - 1) * 10, 10);
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
		// return map;

	}

	// 获取代开票数量
	@RequestMapping(value = "/getTicketCount")
	public void getCount(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		String customerName = request.getParameter("customerName");
		String ticketNum = request.getParameter("ticketNum");
		TicketEntity entity = new TicketEntity();
		entity.setCustomerName(customerName);
		entity.setTicketNum(ticketNum);
		int count = service.count(entity);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		jObject.put("count", count);
		out.print(jObject);
	}

	// 代开票页面
	@RequestMapping(value = "/getTicketListPage")
	public String getTicketListPage(HttpServletRequest request) {
		return "ticket/ticketList.html";
	}

	// 新增代开票信息
	@RequestMapping(value = "/addTicket")
	public void addTicket(HttpServletRequest request,
			@RequestParam MultipartFile file, HttpServletResponse response) {
		PrintWriter out = null;
		TicketEntity entity = new TicketEntity();
		String path = "C:\\workspace\\coursePic\\";
		String fileName = file.getOriginalFilename();
		String ticketNum = request.getParameter("ticketNum");
		double moneyAccount = Double.parseDouble(request
				.getParameter("moneyAccount"));
		String reasonPayment = request.getParameter("reasonPayment");
		long customerId = Long.parseLong(request.getParameter("customerId"));
		String ticketCode = request.getParameter("ticketCode");
		String ticketTime = request.getParameter("ticketTime");
		// entity.setTicketId(id);
		entity.setCreateTime(DateUtils.formatT(new Date()));
		entity.setCustomerId(customerId);
		entity.setMoneyAccount(moneyAccount);
		entity.setTicketCode(ticketCode);
		entity.setReasonPayment(reasonPayment);
		entity.setTicketNum(ticketNum);
		entity.setTicketTime(ticketTime);
		entity.setPic(path + "\\" + entity.getTicketId() + fileName);
		service.add(entity);
		File fileDir = new File(path + entity.getTicketId() + fileName);
		service.updatePic(entity.getTicketId(), entity.getTicketId() + fileName);
		if (!fileDir.exists()) { // 如果不存在 则创建
			fileDir.mkdirs();
		}
		try {
			file.transferTo(fileDir);
			// return sqlPath+fileName;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 更新代开票
	@RequestMapping(value = "/updateTicket")
	public void updateTicket(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
			TicketEntity entity = new TicketEntity();
			PrintWriter out = null;
			int type =1;
			long ticketId = Long.parseLong(request.getParameter("ticketId"));
			String ticketNum = request.getParameter("ticketNum");
			double moneyAccount = Double.parseDouble(request
					.getParameter("moneyAccount"));
			String reasonPayment = request.getParameter("reasonPayment");
			long customerId = Long.parseLong(request.getParameter("customerId"));
			String ticketCode = request.getParameter("ticketCode");
			// entity.setTicketId(id);
			entity.setTicketId(ticketId);
			entity.setCustomerId(customerId);
			entity.setMoneyAccount(moneyAccount);
			entity.setTicketCode(ticketCode);
			entity.setReasonPayment(reasonPayment);
			entity.setTicketNum(ticketNum);
			try {
				out = response.getWriter();
				service.update(entity);
			} catch (IOException e) {
				e.printStackTrace();
				type = 2;
			}
			JSONObject jObject = new JSONObject();
			jObject.put("msg", type);
			out.print(jObject);
	}

	// 获取代开票发票代码
	@RequestMapping(value = "/getTicketNum")
	public void getTicketNum(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		List<TicketEntity> list = service.getAllTicket();
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		jObject.put("tickets", list);
		out.print(jObject);
	}

	// 验证代开票
	@RequestMapping(value = "/selectTicket")
	public void selectTicket(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		String value = request.getParameter("value");
		String column = request.getParameter("column"); 
		String ticketId = request.getParameter("ticketId");
		PrintWriter out = null;
		try {
			int count = service.selectTicket(value, column,ticketId);
			out = response.getWriter();
			JSONObject jObject = new JSONObject();
			jObject.put("type", count);
			out.print(jObject);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
