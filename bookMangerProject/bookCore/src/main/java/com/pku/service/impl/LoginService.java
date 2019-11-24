package com.pku.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.stereotype.Service;

import com.pku.common.InvokeResult;
import com.pku.common.PkuException;
import com.pku.controller.login.LoginDTO;
import com.pku.entity.User;
import com.pku.service.ILoginService;

/**
 * 登录服务实现
 * Package : com.pku.service.impl
 * 
 * @author YixinCapital -- wangwenlong
 *		   2017年5月5日 下午7:35:14
 *
 */
@Service
public class LoginService implements ILoginService {

	public InvokeResult<LoginDTO> login(LoginDTO loginDTO) throws PkuException {
		InvokeResult<LoginDTO> iresult = new InvokeResult<>();
		if(StringUtils.isBlank(loginDTO.getAccount())){
			iresult.failure("登陆账号不能为空");
			return iresult;
		}
		if(StringUtils.isBlank(loginDTO.getPassword())){
			iresult.failure("登陆密码不能为空");
			return iresult;
		}
		User user = User.getUserByAccount(loginDTO.getAccount());
		if(user==null){
			iresult.failure("登陆账号错误");
			return iresult;
		}
		String md5Login = MD5Encoder.encode(loginDTO.getPassword().getBytes());
		String md5user = MD5Encoder.encode(user.getPassword().getBytes());
		if(!md5Login.equals(md5user)){
			iresult.failure("登陆密码错误");
			return iresult;
		}
		iresult.setHasErrors(false);
		return iresult;
	}

}

