package com.lovecws.mumu.common.jpush.service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.TimeUnit;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.report.MessagesResult;
import cn.jpush.api.report.MessagesResult.Message;
import cn.jpush.api.report.ReceivedsResult;
import cn.jpush.api.report.ReceivedsResult.Received;
import cn.jpush.api.report.UsersResult;
import cn.jpush.api.report.UsersResult.User;
import com.lovecws.mumu.common.jpush.exception.JPushReportException;
import org.apache.log4j.Logger;

import java.util.List;

/**
 * 极光推送 送达统计、用户统计、消息统计
 * 
 * @author ganliang
 */
public class JPushReportService {
	private static final Logger log = Logger.getLogger(JPushReportService.class);

	public JPushClient jPushClient;// 极光推送客户端

	public void setjPushClient(JPushClient jPushClient) {
		this.jPushClient = jPushClient;
	}

	// ------------- 送达统计
	/**
	 * 根据msgId获取该msgId的送达统计数据
	 * 
	 * @param msgIds
	 *            推送消息id
	 * @return
	 * @throws JPushReportException
	 */
	public List<Received> getReportReceiveds(String msgIds) throws JPushReportException {
		log.info("getReportReceiveds,msgIds[" + msgIds + "]");
		try {
			ReceivedsResult reportReceiveds = jPushClient.getReportReceiveds(msgIds);
			List<Received> receiveds = reportReceiveds.received_list;
			log.info("receiveds" + receiveds);
			return receiveds;
		} catch (APIConnectionException e) {
			throw new JPushReportException("Connection error", e);
		} catch (APIRequestException e) {
			throw new JPushReportException("API request error", e);
		}
	}

	// ------------- 消息统计（VIP专属接口）
	/**
	 * 根据msgId获取一个msg_id的统计数据
	 * 
	 * @param msgIds
	 *            推送消息id
	 * @return
	 * @throws JPushReportException
	 */
	public List<Message> getReportMessages(String msgIds) throws JPushReportException {
		log.info("getReportMessages,msgIds[" + msgIds + "]");
		try {
			MessagesResult reportMessages = jPushClient.getReportMessages(msgIds);
			List<Message> messages = reportMessages.messages;
			log.info("messages" + messages);
			return messages;
		} catch (APIConnectionException e) {
			throw new JPushReportException("Connection error", e);
		} catch (APIRequestException e) {
			throw new JPushReportException("API request error", e);
		}
	}

	// ------------- 用户统计（VIP专属接口）
	/**
	 * 查询某个时间段内的用户统计数据
	 * 
	 * @param timeUnit
	 *            时间单位支持：HOUR（小时）、DAY（天）、MONTH（月）。
	 * @param start
	 *            如果单位是小时，则起始时间是小时（包含天），格式例：2014-06-11 09
	 *            如果单位是天，则起始时间是日期（天），格式例：2014-06-11
	 *            如果单位是月，则起始时间是日期（月），格式例：2014-06
	 * @param duration
	 *            如果单位是天，则是持续的天数。以此类推。
	 *            只支持查询60天以内的用户信息，对于time_unit为HOUR的，只支持输出当天的统计结果。
	 * @return {"time":"2014-06-10"},{"time":"2014-06-11","android":{"active":1}},{"time":"2014-06-12","android":{"active":1,"online":2}}
	 *         new 新增用户 online 在线用户 active 活跃用户
	 * @throws JPushReportException
	 */
	public List<User> getReportUsers(TimeUnit timeUnit, String start, int duration) throws JPushReportException {
		log.info("getReportUsers,timeUnit[" + timeUnit + "],start[" + start + "],duration[" + duration + "]");
		try {
			UsersResult reportUsers = jPushClient.getReportUsers(timeUnit, start, duration);
			List<User> users = reportUsers.items;
			log.info("users:" + users);
			return users;
		} catch (APIConnectionException e) {
			throw new JPushReportException("Connection error", e);
		} catch (APIRequestException e) {
			throw new JPushReportException("API request error", e);
		}
	}

}
