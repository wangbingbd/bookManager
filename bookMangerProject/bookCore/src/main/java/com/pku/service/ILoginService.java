package com.pku.service;

import com.pku.common.InvokeResult;
import com.pku.controller.login.LoginDTO;
import com.pku.exception.PkuException;

public interface ILoginService {
	
	public InvokeResult<LoginDTO> login(LoginDTO loginDTO) throws PkuException;
}

