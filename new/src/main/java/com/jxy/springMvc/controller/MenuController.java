package com.jxy.springMvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.jxy.springMvc.service.IMenuService;
import com.jxy.springMvc.service.IRoleMenuService;
import com.jxy.springMvc.entity.MenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class MenuController {
	@Autowired
	private IMenuService service;
	@Autowired
	private IRoleMenuService roleMenuService;
	//获取某角色菜单（编辑）
	@RequestMapping(value="/getMenu/{roleId}")
	public void getMenu(HttpServletRequest request,HttpServletResponse response,@PathVariable long roleId){
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		List<MenuEntity> list=service.getList();
		List<Long> menus=roleMenuService.getRoleMenu(roleId);
		for(MenuEntity entity:list){
			if(menus.contains(entity.getMenuId())){
				entity.setChecked(true);
			}
		}
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		if (list != null) {
			jObject.put("data", list);
			out.print(jObject);
		}
	}
	//获取某角色菜单
	@RequestMapping(value="/getRoleMenu/{roleId}")
	public void getRoleMenu(HttpServletRequest request,HttpServletResponse response,@PathVariable long roleId){
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		List<MenuEntity> list=service.getRoleMenu(roleId);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		if (list != null) {
			jObject.put("data", list);
			out.print(jObject);
		}
	}
}
