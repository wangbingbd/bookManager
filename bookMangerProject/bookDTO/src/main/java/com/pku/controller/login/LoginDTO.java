package com.pku.controller.login;

import lombok.Data;

import com.pku.common.WebDTO;

/**
 * 登陆信息DTO
 * @author pc
 *
 */
public class LoginDTO extends WebDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 908824844266623301L;
	
	//登陆账号
	private String account;
	
	//登陆密码
	private String password;
	
	//验证码
	private String verifyCode;
	

	public String getAccount() {
		return account;
	}



	public void setAccount(String account) {
		this.account = account;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getVerifyCode() {
		return verifyCode;
	}



	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}



	@Override
	public String toString() {
		return "LoginDTO [account=" + account + ", password=" + password
				+ ", verifyCode=" + verifyCode + "]";
	}
	
	

}
