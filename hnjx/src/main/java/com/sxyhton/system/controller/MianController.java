package com.sxyhton.system.controller;

import com.sxyht.common.utils.MD5Utils;
import com.sxyht.common.utils.R;
import com.sxyhton.common.annotation.Log;
import com.sxyhton.common.controller.BaseController;
import com.sxyhton.common.domain.Tree;
import com.sxyhton.common.utils.WebSiteMapUtils;
import com.sxyhton.cont.dao.ContSitemapDao;
import com.sxyhton.cont.domain.ContSitemapDO;
import com.sxyhton.system.domain.MenuDO;
import com.sxyhton.system.service.MenuService;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Collection;
import java.util.List;

@Controller
public class MianController extends BaseController {
	@Autowired MenuService menuService;
	@Autowired private ContSitemapDao contSitemapDao;

	@Log("/xmx/*")
	@GetMapping({"","/"})
	public String indexView(HttpServletRequest request,Model model) {
		String pageAddress="index";
		this.commonSesssion(pageAddress,request,model);
		return "xmx/"+pageAddress;
	}
	
	/**请求访问登录页面 */
	@GetMapping("/login")
	String login() {
		return "main/login";
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
	@GetMapping("/main")
	String main(Model model) {
		List<Tree<MenuDO>> menus = menuService.listMenuTree(getUserId());//通过用户id加载菜单选项
		model.addAttribute("menus", menus);
		model.addAttribute("name", getUser().getName());
		model.addAttribute("picUrl","/img/photo_s2.png");
		model.addAttribute("username", getUser().getUsername());
		return "main/mainPage";
	}
	/**
	 * 首页内容显示
	 * @return
	 */
	@GetMapping("/homePage")
	String homePage() {
		return "main/homePage";
	}
	/**
	 * 谷歌搜索专用
	 * @return
	 */
	@Log("sitemap爬虫一次")
	@GetMapping("/sitemap.xml")@ResponseBody
	public String WebSiteMap() {
		Collection<ContSitemapDO> s = this.contSitemapDao.list(null);
		
		
		StringBuffer bf=new StringBuffer();
		bf.append(WebSiteMapUtils.URLSET_START);
		for(ContSitemapDO u:s)
			bf.append(u.getUrl());
		bf.append(WebSiteMapUtils.URLSET_END);
		return bf.toString();
	}
}
