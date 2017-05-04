package com.lovecws.mumu.common.jpush.exception;

/**
 * 定时推送消息出现异常
 * @author ganliang
 */
public class JPushScheduleException extends Exception {

	private static final long serialVersionUID = -398457421021748850L;

	public JPushScheduleException() {
		super();
	}

	public JPushScheduleException(String e, Throwable arg1) {
		super(e, arg1);
	}

	public JPushScheduleException(String e) {
		super();
	}

	public JPushScheduleException(Throwable e) {
		super();
	}

}
