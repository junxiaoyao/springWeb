package com.jxy.springMvc.controller;

import com.alibaba.fastjson.JSONObject;
import com.jxy.springMvc.service.IUserService;
import com.jxy.springMvc.entity.UserEntity;
import com.jxy.springMvc.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
	@Autowired
	private IUserService userService;
	public String loginMain(){
		return "index";
	}
	// 系统主界面
	@RequestMapping(value = "/main")
	public String main(HttpServletRequest request, HttpServletResponse response) {
		return "main.jsp";
	}

	// 测试HTML
	@RequestMapping(value = "/getLoginUser")
	public void getLoginUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		HttpSession session = request.getSession();
		UserEntity user = (UserEntity) session.getAttribute("user");
		PrintWriter out = response.getWriter();
		JSONObject jObject = new JSONObject();
		jObject.put("user", user);
		out.print(jObject);
	}

	// 用户信息主界面
	@RequestMapping(value = "/getUserListPage")
	public String userListPage(HttpServletRequest request) {
		return "user/userList.html";
	}

	// 退出系统
	@RequestMapping(value = "/signOut")
	public String signOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "redirect:/index.html";
	}

	// 用户登录
	@RequestMapping(value = "userLogin")
	public void login(HttpServletRequest request, HttpServletResponse response) {
		PrintWriter out = null;
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		UserEntity entity = userService.userLogin(userName, password);
		HttpSession session = request.getSession();
		session.putValue("user", entity);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		if (entity != null) {
			jObject.put("msg", "true");
			out.print(jObject);
		} else {
			jObject.put("msg", "false");
			out.print(jObject);
		}
	}

	// 获取所有用户
	@RequestMapping(value = "/getAllUsers")
	public void getAllUsers(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		List<UserEntity> entities = userService.getAllList();
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		if (entities != null) {
			jObject.put("data", entities);
			out.print(jObject);
		}
	}

	// 查询某页数据
	@RequestMapping(value = "/getUsers")
	public void getUserList(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		String userName = request.getParameter("userName");
		String realName = request.getParameter("realName");
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(userName);
		userEntity.setRealName(realName);
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		List<UserEntity> entitys = userService.getList(userEntity,
				(pageNum - 1) * 10, 10);
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jObject = new JSONObject();
		if (entitys != null) {
			jObject.put("data", entitys);
			out.print(jObject);
		}
	}

	// 获取用户信息条数
	@RequestMapping("/getUsersCount")
	public void getUserListCount(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		String userName = request.getParameter("userName");
		String realName = request.getParameter("realName");
		UserEntity userEntity = new UserEntity();
		userEntity.setUserName(userName);
		userEntity.setRealName(realName);
		int count = userService.count(userEntity);
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

	// 删除某用户
	@RequestMapping(value = "/deleteUser/{id}")
	public void deleteUser(HttpServletRequest request, @PathVariable long id,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		int type = 1;
		try {
			userService.delete(id);
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			type = 2;
		}
		JSONObject jObject = new JSONObject();
		jObject.put("msg", type);
		out.print(jObject);

	}

	// 更新用户
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public void updateUser(HttpServletRequest request,
			HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		UserEntity entity = new UserEntity();
		entity.setUserId(Integer.parseInt(request.getParameter("userId")));
		entity.setUserName(request.getParameter("userName"));
		entity.setSex(request.getParameter("sex"));
		entity.setPassword(request.getParameter("password"));
		entity.setRealName(request.getParameter("realName"));
		entity.setUserPhone(request.getParameter("userPhone"));
		entity.setEmail(request.getParameter("email"));
		entity.setRoleId(Integer.parseInt(request.getParameter("roleId")));
		int countName = userService.getUserByName(entity.getUserName(),
				entity.getUserId());
		int type = 1;

		try {
			if (countName < 1) {
				userService.update(entity);
			} else {
				type = 0;
			}
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			type = 2;
		}
		JSONObject jObject = new JSONObject();
		jObject.put("msg", type);
		out.print(jObject);
	}

	// 添加用户
	@RequestMapping(value = "/addUser")
	public void addUser(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		UserEntity entity = new UserEntity();
		entity.setUserName(request.getParameter("userName"));
		entity.setSex(request.getParameter("sex"));
		entity.setPassword(request.getParameter("password"));
		entity.setRealName(request.getParameter("realName"));
		entity.setUserPhone(request.getParameter("userPhone"));
		entity.setEmail(request.getParameter("email"));
		entity.setCreateTime(DateUtils.formatT(new Date()));
		entity.setRoleId(Integer.parseInt(request.getParameter("roleId")));
		int countName = userService.getUserByName(entity.getUserName(), -1);
		int type = 1;

		try {
			if (countName < 1) {
				userService.add(entity);
			} else {
				type = 0;
			}
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			type = 2;
		}
		JSONObject jObject = new JSONObject();
		jObject.put("msg", type);
		out.print(jObject);
	}
}
