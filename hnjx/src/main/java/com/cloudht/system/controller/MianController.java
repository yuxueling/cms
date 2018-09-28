package com.cloudht.system.controller;

import com.cloudht.common.annotation.Log;

import com.cloudht.common.controller.BaseController;
import com.cloudht.common.domain.Tree;

import com.cloudht.system.domain.MenuDO;
import com.cloudht.system.service.MenuService;
import com.sxyht.common.utils.MD5Utils;
import com.sxyht.common.utils.R;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MianController extends BaseController {
	@Autowired MenuService menuService;
<<<<<<< HEAD
	
	@GetMapping({"","/xmx"})
=======
	@GetMapping({"","/","index","index.html"})
	public String redirect() {
		return "redirect:/xmx/index";
	}
	@GetMapping({"/xmx/index"})
>>>>>>> temp
	public String index() {
		return "/xmx/index";
	}
	/**请求访问登录页面 */
	@GetMapping({"/login"})
	String login() {
		return "login";
	}
	/**
	 * 验证用户名和密码，将相关信息放入缓存
	 * @param username
	 * @param password
	 * @return
	 */
	@Log("登录") 
	@PostMapping("/login") @ResponseBody
	R ajaxLogin(String username, String password) {
		password = MD5Utils.encrypt(username, password);
		//创建认证令牌对象
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		} catch (Exception e) {
			return R.error("账号或密码错误");
		}
		return R.ok();
	}
	/**
	 * 登录成功后加载主页的内容
	 * @param model
	 * @return
	 */
	@Log("请求访问主页")
	@GetMapping("/main")
	String main(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());//通过用户id加载菜单选项
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		model.addAttribute("picUrl","/img/photo_s2.png");
		model.addAttribute("username", getUser().getUsername());
		return "system/main/main";
	}
	/**
	 * 首页内容显示
	 * @return
	 */
	@GetMapping("/homePage")
	String homePage() {
		return "system/main/homePage";
	}
}
