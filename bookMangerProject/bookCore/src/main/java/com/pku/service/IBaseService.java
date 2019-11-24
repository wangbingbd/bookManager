package com.pku.service;

import java.io.Serializable;

import javax.transaction.Transactional;

import com.pku.common.BaseDTO;
import com.pku.common.InvokeResult;
import com.pku.exception.PkuException;

/**
 * 基础操作接口
 * Package : com.pku.service
 * 
 * @author YixinCapital -- wangwenlong
 *		   2017年4月28日 下午3:33:00
 *
 */
public interface IBaseService<S extends BaseDTO,P extends Serializable>{
	
	/**
	 * 保存接口	
	 * @param entity
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2017年4月28日 下午3:35:16
	 */
	@Transactional
	public InvokeResult<S> save(S dto) throws PkuException;
	
	/**
	 * 更新接口
	 * @param entity
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2017年4月28日 下午3:35:23
	 */
	@Transactional
	public InvokeResult<S> update(S dto) throws PkuException;
	
	/**
	 * 根据ID 查找图书实体
	 * @param id
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2017年4月28日 下午3:35:31
	 */
	public InvokeResult<S> findById(P p) throws PkuException;
	
	
	/**
	 * 根据ID 逻辑删除图书实体
	 * @param p
	 * @return
	 * @throws PkuException 
	 * @author YixinCapital -- wangwenlong
	 *	       2017年5月4日 上午9:18:43
	 */
	public InvokeResult<P> logicRemove(P p) throws PkuException;
	
	/**
	 * 根据ID 物理删除图书实体
	 * @param p
	 * @return
	 * @throws PkuException 
	 * @author YixinCapital -- wangwenlong
	 *	       2017年5月4日 上午9:18:43
	 */
	public InvokeResult<P> realRemove(P p) throws PkuException;
}

