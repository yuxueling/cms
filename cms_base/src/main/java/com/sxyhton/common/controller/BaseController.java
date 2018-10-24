package com.sxyhton.common.controller;

import java.util.HashMap;
import java.util.List;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.sxyht.common.utils.ShiroUtils;
import com.sxyhton.cont.domain.ContSeoDO;
import com.sxyhton.cont.service.ContSeoService;
import com.sxyhton.system.domain.UserDO;

@Controller
public class BaseController {
	@Autowired private ContSeoService contSeoService;
	public UserDO getUser() {
		return (UserDO)ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}

	public Session getSession() {
		return ShiroUtils.getSession();
	}
	/**
	 * 获取浏览器支持的语言类型，并放入session中，将页面seo放入model中
	 * 此方法在多处使用，修改请慎重
	 * @param pageAddress 访问的页面地址 如index
	 * @param request HttpServletRequest
	 * @param model 页面内容域对象
	 */
	public void commonSesssion(String pageAddress,HttpServletRequest request,Model model) {
		if("null".equals(this.getSession().getAttribute("langType")+"")) {
			String langType = request.getHeader("accept-language");//获取浏览器支持的语言类型
			try {
				langType = langType.substring(0, langType.indexOf(","));//获取浏览器首要支持的语言类型
			} catch (Exception e) {}
			this.getSession().setAttribute("langType", langType);
		}
		HashMap<String,Object> map = new HashMap<>();
		map.put("pageAddress", pageAddress);
		map.put("langType", this.getSession().getAttribute("langType"));//如有需要传入语言类型
		List<ContSeoDO> list = this.contSeoService.list(map);
		if(list.size()==0) return;
		ContSeoDO contSeoDO = list.get(0);
		model.addAttribute("seoTitle", contSeoDO.getSeoTitle());
		model.addAttribute("keywords", contSeoDO.getKeywords());
		model.addAttribute("description", contSeoDO.getDescription());
	}
}