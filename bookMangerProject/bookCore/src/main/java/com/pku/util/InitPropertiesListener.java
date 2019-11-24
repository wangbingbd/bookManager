package com.pku.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitPropertiesListener implements ServletContextListener{

	public static String rootPath;
	
	public InitPropertiesListener(){
		Connection con = null;
		try {
			//创建数据连接
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/book?useUnicode=true&characterEncoding=UTF-8","root","mysqlpwd");
			PreparedStatement ps = con.prepareStatement( "SELECT COUNT(1) FROM `pku_user` WHERE user_account = 'admin' AND IS_DELETED = FALSE ");
    		ResultSet rs = ps.executeQuery();
    		if(rs.next()){
    			if(rs.getInt(1)==0){
    				ps.executeUpdate("INSERT INTO `pku_user` "
    						+ "(`ID`, `CREATE_TIME`, `CREATOR_DEPARTMENT_ID`, `CREATOR_DEPARTMENT_NAME`, `CREATOR_ID`, `CREATOR_NAME`, `IS_DELETED`, `UPDATE_TIME`, `UPDATOR_DEPARTMENT_ID`, `UPDATOR_DEPARTMENT_NAME`, `UPDATOR_ID`, `UPDATOR_NAME`, `VERSION`, `email`, `password`, `regist_date`, `sex`, `telnumber`, `user_name`, `user_account`) "
    						+ " VALUES (UUID_SHORT(), CURRENT_TIMESTAMP(), NULL, NULL, NULL, NULL, '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'admin', NULL, NULL, NULL, 'admin', 'admin') ");
    			};
    		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
    		if(con!=null) {
    			try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
    		}
		}
	}
	
	public void contextInitialized(ServletContextEvent sce) {
		rootPath = sce.getServletContext().getRealPath("/");
	}

	public void contextDestroyed(ServletContextEvent sce) {
		
	}
}

