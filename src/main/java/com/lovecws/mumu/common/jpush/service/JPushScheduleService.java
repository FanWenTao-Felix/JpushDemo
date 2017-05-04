package com.lovecws.mumu.common.jpush.service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.schedule.ScheduleListResult;
import cn.jpush.api.schedule.ScheduleResult;
import com.lovecws.mumu.common.jpush.exception.JPushScheduleException;
import org.apache.log4j.Logger;

/**
 * @desc jpush定时推送消息 
 * @author ganliang
 * @version 2016年9月8日 下午3:59:25
 */
public class JPushScheduleService {

	private static final Logger log = Logger.getLogger(JPushScheduleService.class);

	public JPushClient jPushClient;// 极光推送客户端

	public void setjPushClient(JPushClient jPushClient) {
		this.jPushClient = jPushClient;
	}
	
	/**
     * Create a daily schedule push everyday.
     * @param name The schedule name.
     * @param start The schedule comes into effect date, format 'yyyy-MM-dd HH:mm:ss'.
     * @param end The schedule expiration date, format 'yyyy-MM-dd HH:mm:ss'.
     * @param time The push time, format 'HH:mm:ss'
     * @param push The push payload.
     * @return The created scheduleResult instance.
     * @throws APIConnectionException if a remote or network exception occurs.
     * @throws APIRequestException if a request exception occurs.
     */
	public String createDailySchedule(String name, String start, String end, String time, PushPayload push) throws JPushScheduleException {
		log.info("createDailySchedule..................................");
		try {
			ScheduleResult result = jPushClient.createDailySchedule(name, start, end, time, push);
			if(result.isResultOK()){
				return result.getSchedule_id();
			}else{
				throw new RuntimeException();
			}
		} catch (APIConnectionException e) {
			throw new JPushScheduleException("Connection error",e);
		} catch (APIRequestException e) {
			throw new JPushScheduleException("API request error",e);
		}
	}
	
	/**
	 * 获取第一页定时发送消息处理器
	 * @return
	 * @throws JPushScheduleException
	 */
	public ScheduleListResult getScheduleList() throws JPushScheduleException{
		log.info("getScheduleList..................................");
		try {
			ScheduleListResult scheduleList = jPushClient.getScheduleList();
			return scheduleList;
		} catch (APIConnectionException e) {
			throw new JPushScheduleException("Connection error",e);
		} catch (APIRequestException e) {
			throw new JPushScheduleException("API request error",e);
		}
	}
}
