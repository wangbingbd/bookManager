package com.pku.base;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pku.common.JpaEntityManager;
import com.pku.util.SpringContextUtil;

public class Entity implements Serializable{
	
	private static final long serialVersionUID = 6510557784630813080L;
	
	protected Logger logger = LoggerFactory.getLogger(Entity.class);
	
	private static JpaEntityManager jpaEntityManager;
	
	public Entity(){
		try {
			if(jpaEntityManager == null){
				jpaEntityManager = SpringContextUtil.getApplicationContext().getBean(JpaEntityManager.class);
			}
		}	catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	public static EntityManager getEntityManager() {
		if(jpaEntityManager==null){
			jpaEntityManager = SpringContextUtil.getApplicationContext().getBean(JpaEntityManager.class);
		}
		return jpaEntityManager.getEntityManager();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return jpaEntityManager.getEntityManagerFactory();
	}
}

