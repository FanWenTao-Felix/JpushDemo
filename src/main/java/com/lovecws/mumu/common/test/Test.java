package com.lovecws.mumu.common.test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangmx on 2017/6/9.
 */
public class Test {
    public static void main(String a[]){
             /* 推送给设备标识参数的用户
                * @param registrationId 设备标识
                * @param notification_title 通知内容标题
                * @param msg_title 消息内容标题
                * @param msg_content 消息内容
                * @param extrasparam 扩展字段
                * @return 0推送失败，1推送成功
                */
             try{
                 //扩展字段
                 Map<String, Object> jsonmap = new HashMap<>();
                 jsonmap.put("tagCode","");   //201
                 jsonmap.put("applyId", "");

                 //推送设备标识用户
                 /*System.out.println("结果：：：" + JpushClientUtil.sendToRegistrationId("bbb4d955fe544a2593576ab6d601390c",
                         "通知内容标题","消息内容标题", "消息内容。。。。",
                         "扩展字段。。。"));*/
                 System.out.println(FastJSONUtil.toJSONString(jsonmap));
                 //推送别名用户
                 System.out.println("结果：：：" + JpushClientUtil.sendToAliasId("eafd37a139a34952886ba689e2e1fe94",
                         "通知内容标题","消息内容标题", "消息内容。。。。",
                         jsonmap));

                 //推送所有用户
               /*  System.out.println("结果：：：" + JpushClientUtil.sendToAllIos(
                         "通知内容标题","消息内容标题", "消息内容。。。。",  FastJSONUtil.toJSONString(jsonmap)));*/
             }catch (Exception e){
                 e.printStackTrace();
             }

    }

}
