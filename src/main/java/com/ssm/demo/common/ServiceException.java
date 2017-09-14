package com.ssm.demo.common;

/**
 * 服务运行异常
 *
 * @author huangyong
 * @since 1.0.0
 */
public class ServiceException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
        super(message);
    }

}