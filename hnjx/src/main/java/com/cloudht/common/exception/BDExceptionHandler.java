package com.cloudht.common.exception;

import com.cloudht.common.controller.BaseController;
import com.cloudht.common.domain.LogDO;
import com.cloudht.common.service.LogService;
import com.cloudht.system.domain.UserDO;
import com.sxyht.common.utils.HttpServletUtils;
import com.sxyht.common.utils.R;

import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * 异常处理器
 */
@RestControllerAdvice
public class BDExceptionHandler extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired LogService logService;

    @ExceptionHandler(AuthorizationException.class)
    public Object handleAuthorizationException(AuthorizationException e, HttpServletRequest request) {
        logger.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request))
            return R.error(403, "未授权");
        return new ModelAndView("error/403");
    }

    @ExceptionHandler({Exception.class})
    public Object handleException(Exception e, HttpServletRequest request) {
        LogDO logDO = new LogDO();
        logDO.setGmtCreate(new Date());
        logDO.setOperation("error");
        logDO.setMethod(request.getRequestURL().toString());
        logDO.setParams(e.toString());
        UserDO current = (UserDO)getUser();
        if(null!=current){
            logDO.setUserId(current.getUserId());
            logDO.setUsername(current.getUsername());
        }
        logService.save(logDO);
        logger.error(e.getMessage(), e);
        if (HttpServletUtils.jsAjax(request))
            return R.error(500, "服务器错误，请联系管理员");
        return new ModelAndView("error/500");
    }
}