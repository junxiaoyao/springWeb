package com.jxy.springMvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.jxy.springMvc.service.IRoleMenuService;
import com.jxy.springMvc.service.IRoleService;
import com.jxy.springMvc.entity.RoleEntity;
import com.jxy.springMvc.entity.RoleMenuEntity;
import com.jxy.springMvc.entity.UserEntity;
import com.jxy.springMvc.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class RoleController {
	@Autowired
	private IRoleService service;
	@Autowired
	private IRoleMenuService roleMenuService;
	//角色页面
	@RequestMapping(value="/getRolePage")
	public String getRolePage(HttpServletRequest request){
		return "role/roleList.html";
	}
	
	//获取所有角色
	@RequestMapping(value="/getAllRole")
	public void getAllRole(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		List<RoleEntity> entities=service.getAllList();
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		jObject.put("data", entities);
		out.print(jObject);
	}
	//获取某页用户
	@RequestMapping(value="/getRoleList")
	public void getRoleList(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		String roleName = request.getParameter("roleName");
		int pageNum=Integer.parseInt(request.getParameter("pageNum"));
		RoleEntity entity=new RoleEntity();
		entity.setRoleName(roleName);
		List<RoleEntity> entities=service.getList(entity, (pageNum-1)*10, 10);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		jObject.put("data", entities);
		out.print(jObject);
	}
	//获取角色数量
	@RequestMapping(value="/getRoleCount")
	public void getRoleCountt(HttpServletRequest request,HttpServletResponse response){
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		String roleName = request.getParameter("roleName");
		RoleEntity entity=new RoleEntity();
		entity.setRoleName(roleName);
		int count = service.count(entity);
		// map.put("data",entitys);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		jObject.put("count", count);
		out.print(jObject);
	}
	//添加角色
	@RequestMapping(value="/addRole")
	public void addRole(HttpServletRequest request,HttpServletResponse response,@RequestParam("menus[]") long[] menus){
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = null;
		int type=1;
		HttpSession session=request.getSession();
		UserEntity userEntity = (UserEntity) session.getAttribute("user");
		String roleName=request.getParameter("roleName");
		String remark=request.getParameter("remark");
		RoleEntity entity=new RoleEntity();
		entity.setCreateTime(DateUtils.formatT(new Date()));
		entity.setRemark(remark);
		entity.setRoleName(roleName);
		entity.setUserId(userEntity.getUserId());
		entity.setUserName(userEntity.getRealName());
		try {
			service.addRole(entity);
			ArrayList<RoleMenuEntity> list=new ArrayList<>();
			for(int i=0;i<menus.length;i++){
				RoleMenuEntity roleMenuEntity=new RoleMenuEntity();
				roleMenuEntity.setRoleId(entity.getRoleId());
				roleMenuEntity.setMenuId(menus[i]);
				list.add(roleMenuEntity);
			}
			roleMenuService.add(list);
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
	//删除角色
	@RequestMapping(value="/delete/{id}")
	public void delete(HttpServletResponse response,@PathVariable long id){
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = null;
		service.delete(id);
		roleMenuService.delete(id);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		jObject.put("type", "1");
		out.print(jObject);
	}
	//编辑角色
	@RequestMapping(value="/edit/{roleId}")
	public void edit(HttpServletRequest request,HttpServletResponse response,@PathVariable long roleId,@RequestParam("menus[]") long[] menus){
		response.setContentType("text/html; charset=utf-8");	
		PrintWriter out = null;
		int type=1;
		try {
			roleMenuService.delete(roleId);
			String roleName=request.getParameter("roleName");
			String remark=request.getParameter("remark");
			RoleEntity roleEntity=new RoleEntity();
			roleEntity.setRoleId(roleId);
			roleEntity.setRemark(remark);
			roleEntity.setRoleName(roleName);
			ArrayList<RoleMenuEntity> list=new ArrayList<>();
			for(int i=0;i<menus.length;i++){
				RoleMenuEntity roleMenuEntity=new RoleMenuEntity();
				roleMenuEntity.setRoleId(roleEntity.getRoleId());
				roleMenuEntity.setMenuId(menus[i]);
				list.add(roleMenuEntity);
			}
			service.update(roleEntity);
			roleMenuService.add(list);
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
}
