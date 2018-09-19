package com.cloudht.common.controller;

import org.springframework.stereotype.Controller;
import com.cloudht.system.domain.UserDO;
import com.sxyht.common.utils.ShiroUtils;

@Controller
public class BaseController {
	public UserDO getUser() {
		return (UserDO)ShiroUtils.getUser();
	}

	public Long getUserId() {
		return getUser().getUserId();
	}

	public String getUsername() {
		return getUser().getUsername();
	}
}