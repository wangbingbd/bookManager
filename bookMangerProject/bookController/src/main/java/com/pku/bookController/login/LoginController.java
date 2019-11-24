package com.pku.bookController.login;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pku.common.InvokeResult;
import com.pku.controller.login.LoginDTO;
import com.pku.exception.PkuException;
import com.pku.service.ILoginService;

/**
 * 登陆模块前端响应类
 * @author pc
 *
 */
@RequestMapping("/loginController")
@Controller
public class LoginController {

	private Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	private ILoginService loginService;
	
	@RequestMapping("/loginTest")
	@ResponseBody
	public String login2(){
		return "helloWord!";
	}
	
	@RequestMapping(value = "/login", method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public InvokeResult<LoginDTO> login(LoginDTO loginDTO){
		InvokeResult<LoginDTO> invokeResult = new InvokeResult<>();
		try {
			invokeResult = this.loginService.login(loginDTO);
		} catch (PkuException e) {
			logger.error("登陆失败",e);
			invokeResult.failure(e.getMessage());
		} catch (Exception e) {
			logger.error("登陆异常",e);
			invokeResult.failure("登陆异常");
		}
		
		return invokeResult;
	}

}
