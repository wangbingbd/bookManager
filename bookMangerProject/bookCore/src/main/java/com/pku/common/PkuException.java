package com.pku.common;

/**
 * 自定义异常
 * Package : com.pku.common
 * 
 * @author YixinCapital -- wangwenlong
 *		   2017年4月28日 下午3:44:38
 *
 */
public class PkuException extends RuntimeException{

	private static final long serialVersionUID = 3195089146148185950L;

	public PkuException() {
		super();
	}

	public PkuException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PkuException(String message, Throwable cause) {
		super(message, cause);
	}

	public PkuException(String message) {
		super(message);
	}

	public PkuException(Throwable cause) {
		super(cause);
	}
	
}

