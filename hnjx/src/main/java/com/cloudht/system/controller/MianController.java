package com.cloudht.system.controller;

import com.cloudht.blog.domain.ContentDO;
import com.cloudht.blog.service.ContentService;
import com.cloudht.common.annotation.Log;

import com.cloudht.common.controller.BaseController;
import com.cloudht.common.domain.Tree;
import com.cloudht.system.domain.MenuDO;
import com.cloudht.system.service.MenuService;
import com.sxyht.common.utils.MD5Utils;
import com.sxyht.common.utils.PageUtils;
import com.sxyht.common.utils.Query;
import com.sxyht.common.utils.R;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
@Controller
public class MianController extends BaseController {
	@Autowired MenuService menuService;
	@Autowired private ContentService contentService;
	
	@GetMapping({"","/"})
	public String redirect() {
		return "xmx/index";
	}
	/**
	 * 待完善
	 * @param params
	 * @param name
	 * @param cid
	 * @param news
	 * @param model
	 * @return
	 */

//	@RequestMapping("/xmx/{name}")
//	public String tongYong(@RequestParam Map<String, Object> params,
//			@PathVariable("name") String name,Long cid,Integer news,Model model) {
//		if(cid!=null&&cid>0) {//用来处理这个请求的getArticleDetails,返回到articleDetails
//			ContentDO contentDO = this.contentService.get(cid);
//			model.addAttribute("contentDO", contentDO);
//		}
//		if("News".equals(name)||"News.html".equals(name)) {
//			//将新闻与事件放入model中
//			Query map =new Query(params);
//			List<ContentDO> list = this.contentService.list(map);
//			int count = this.contentService.count(map);
//			model.addAttribute("news", new PageUtils(list, count));
//		}
//		return "xmx/"+name;
//	}

	/**请求访问登录页面 */
	@GetMapping("/login")
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
