package com.zs.pms.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zs.pms.exception.AppException;
import com.zs.pms.po.TUser;
import com.zs.pms.service.DepService;
import com.zs.pms.service.UserService;
import com.zs.pms.vo.QueryUser;

@Controller
public class UserController {
	@Autowired
	UserService us;
	@Autowired
	DepService ds;

	@RequestMapping("/user/list.do")
	public String list(String page, QueryUser query, ModelMap map) {

		if (page == null) {
			page = "1";
		}
		// 返回分页数据
		map.addAttribute("LIST", us.queryByPage(Integer.parseInt(page), query));
		// 返回总页�?
		map.addAttribute("PAGECONT", us.queryPageCont(query));
		// 当前页数
		map.addAttribute("PAGE", page);
		// 条件
		map.addAttribute("QUERY", query);

		return "user/list";
	}

	@RequestMapping("/user/toadd.do")
	public String toAdd(ModelMap map) {
		// 返回�?级部�?
		map.addAttribute("DLIST", ds.queryByPid(0));
		return "user/add";
	}

	@RequestMapping("/user/add.do")
	public String add(TUser user, HttpSession session, ModelMap map) {
		TUser cu = (TUser) session.getAttribute("CUSER");

		try {
			// 装载数据
			user.setIsenabled(1); // 可用
			user.setCreator(cu.getId()); // 新增�?
			user.setPic("");
			us.insertUser(user);
			// 跳转url
			return "redirect:list.do";
		} catch (AppException e) {
			// TODO Auto-generated catch block
			map.addAttribute("MSG", e.getErrMsg());
			// 调用方法传�?�参�?
			return this.toAdd(map);
		}
	}

	@RequestMapping("/user/delete.do")
	public String delete(int id) {
		us.deleteById(id);
		// 跳转url
		return "redirect:list.do";

	}

	// 批量删除
	@RequestMapping("/user/deletes.do")
	public String delete(int[] ids) {
		us.deleteByIds(ids);
		// 跳转url
		return "redirect:list.do";

	}

	@RequestMapping("/user/get.do")
	public String get(int id, ModelMap map) {
		// 返回用户信息
		map.addAttribute("USER", us.queryById(id));
		// 返回�?级部�?
		map.addAttribute("DLIST", ds.queryByPid(0));
		return "/user/update";
	}
}
