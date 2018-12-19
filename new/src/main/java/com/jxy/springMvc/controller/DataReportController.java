package com.jxy.springMvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.jxy.springMvc.service.IRticketService;
import com.jxy.springMvc.service.ITicketService;
import com.jxy.springMvc.util.TimesUtils;
import com.jxy.springMvc.util.ToJsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@Controller
public class DataReportController {
	@Autowired
	private ITicketService ticketService;
	@Autowired
	private IRticketService iRticketService;
	//开票报表页面
	@RequestMapping(value = "/ticketReport")
	public String ticketReport(HttpServletRequest request) {
		return "report/reportTicket.html";
	}
	//退废票报表
	@RequestMapping(value = "/rTicketReport")
	public String rTicketReport(HttpServletRequest request) {
		return "report/reportRticket.html";
	}
	//营业额报表
	@RequestMapping(value = "/reportTurnover")
	public String reportTurnover(HttpServletRequest request) {
		return "report/reportTurnover.html";
	}
	//开票数据
	@RequestMapping(value = "/getTicketReport")
	public void getTicketReport(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		Date date = new Date();
		String time = request.getParameter("time");
		String customer = request.getParameter("customer");
		if (time.length() > 0) {
			String[] times = TimesUtils.getTimes(time);
			int[] ticketNum = new int[times.length - 1];
			String[] dates = TimesUtils.dates(times);
			for (int i = 0; i < times.length - 1; i++) {
				ticketNum[i] = ticketService.countTicket(customer, times[i],
						times[i + 1]);
			}
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONObject jObject = new JSONObject();
			jObject.put("data", ToJsonObject.ticketData(ticketNum, "代开票"));
			jObject.put("type", 1);
			jObject.put("time", dates);
			out.print(jObject);
		} else {
			String[] times = TimesUtils.times();
			int[] ticketNum = new int[times.length - 1];
			for (int i = 0; i < times.length - 1; i++) {
				ticketNum[i] = ticketService.countTicket(customer, times[i],
						times[i + 1]);
			}
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONObject jObject = new JSONObject();
			jObject.put("data", ToJsonObject.ticketData(ticketNum, "代开票"));
			jObject.put("type", 2);
			jObject.put("year", date.getYear() + 1900);
			out.print(jObject);
		}
	}
//退废票数据
	@RequestMapping(value = "/getRticketReport")
	public void getRticketReport(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		Date date = new Date();
		String time = request.getParameter("time");
		String customerName = request.getParameter("customer");
		if (time.length() > 0) {
			String[] times = TimesUtils.getTimes(time);
			String[] dates = TimesUtils.dates(times);
			int[] ticketNum1 = new int[times.length - 1];
			int[] ticketNum2 = new int[times.length - 1];
			for (int i = 0; i < times.length - 1; i++) {
				ticketNum1[i] = iRticketService.countTicket(customerName, 2,
						times[i], times[i + 1]);
				ticketNum2[i] = iRticketService.countTicket(customerName, 3,
						times[i], times[i + 1]);
			}
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONObject jObject = new JSONObject();
			jObject.put("data1", ToJsonObject.ticketData(ticketNum1, "退票"));
			jObject.put("data2", ToJsonObject.ticketData(ticketNum2, "废票"));
			jObject.put("type", 1);
			jObject.put("time", dates);
			out.print(jObject);

		} else {
			String[] times = TimesUtils.times();
			int[] ticketNum1 = new int[times.length - 1];
			int[] ticketNum2 = new int[times.length - 1];
			for (int i = 0; i < times.length - 1; i++) {
				ticketNum1[i] = iRticketService.countTicket(customerName, 2,
						times[i], times[i + 1]);
				ticketNum2[i] = iRticketService.countTicket(customerName, 3,
						times[i], times[i + 1]);
			}
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONObject jObject = new JSONObject();
			jObject.put("data1", ToJsonObject.ticketData(ticketNum1, "退票"));
			jObject.put("data2", ToJsonObject.ticketData(ticketNum2, "废票"));
			jObject.put("year", date.getYear() + 1900);
			jObject.put("type", 2);
			out.print(jObject);
		}
	}
	//营业额数据
	@RequestMapping(value = "/getTurnoverReport")
	public void getTurnoverReport(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		Date date = new Date();
		String time = request.getParameter("time");
		String customer = request.getParameter("customer");
		if (time.length() > 0) {
			String[] times = TimesUtils.getTimes(time);
			double[] turnovers = new double[times.length - 1];
			String[] dates = TimesUtils.dates(times);
			for (int i = 0; i < times.length - 1; i++) {
				turnovers[i] = ticketService.sumTurnover(customer, times[i],
						times[i + 1]);
			}
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONObject jObject = new JSONObject();
			jObject.put("data", ToJsonObject.turnoversData(turnovers, "代开票"));
			jObject.put("type", 1);
			jObject.put("time", dates);
			out.print(jObject);
		} else {
			String[] times = TimesUtils.times();
			double[] turnovers = new double[times.length - 1];
			for (int i = 0; i < times.length - 1; i++) {
				turnovers[i] = ticketService.sumTurnover(customer, times[i],times[i + 1]);
			}
			try {
				out = response.getWriter();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			JSONObject jObject = new JSONObject();
			jObject.put("data", ToJsonObject.turnoversData(turnovers, "营业额"));
			jObject.put("type", 2);
			jObject.put("year", date.getYear() + 1900);
			out.print(jObject);
		}
	}
}
