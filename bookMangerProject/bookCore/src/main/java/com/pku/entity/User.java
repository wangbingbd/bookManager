package com.pku.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.pku.base.BaseEntity;

@Entity
@Table(name="pku_user")
public class User extends BaseEntity{

	private static final long serialVersionUID = -2615791009973268348L;
	
	@Column(name = "user_name",columnDefinition="varchar(10) COMMENT '用户的姓名'")
	private String userName;
	
	@Column(name = "sex",columnDefinition="varchar(4) COMMENT '性别'")
	private String sex;
	
	@Column(name = "user_account",columnDefinition="varchar(50) COMMENT '登录账号'")
	private String userAccount;

	@Column(name = "password",columnDefinition="varchar(64) COMMENT '登录密码'")
	private String password;
	
	@Column(name = "telnumber",columnDefinition="varchar(11) COMMENT '手机号码'")
	private String telnumber;
	
	@Column(name = "email",columnDefinition="varchar(11) COMMENT '邮箱'")
	private String email;
	
	@Column(name = "regist_date",columnDefinition="datetime COMMENT '注册日期'")
	private String registDate;

	
	public static User getUserByAccount(String account){
		User user = null;
		try {
			String sql = " SELECT * FROM pku_user WHERE user_account = '"+account+"' AND IS_DELETED = FALSE ";
			List<User> users =  getEntityManager().createNativeQuery(sql, User.class).getResultList();
			if(users!=null && users.size()>0){
				user = users.get(0);
			}
		} catch (Exception e) {
			//logger.error("getUserByAccount exception==>"+e.getMessage(), e);
		}
		return user;
	}
	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTelnumber() {
		return telnumber;
	}

	public void setTelnumber(String telnumber) {
		this.telnumber = telnumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRegistDate() {
		return registDate;
	}

	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

