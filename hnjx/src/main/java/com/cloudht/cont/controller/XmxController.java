package com.cloudht.cont.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/xmx")@Controller
public class XmxController {
	//====返回首页推荐产品=========
	@GetMapping("/recommendedProducts")@ResponseBody
	public void getListProduct() {
		
	}
}
