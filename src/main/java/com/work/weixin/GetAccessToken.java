package com.work.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.work.weixin.utils.WeChatUtils;

/**
 * 获取access_token
 */
public class GetAccessToken {
    //corpid 企业id
    private static final String CORPID = "wwc1acce22c6fd12d3";
    //corpsecret 凭证密钥
    private static final String CORPSECRET="5MUguGBmttPgx-zh8TTQeCyb7EwTAJx3YVOjHQbxtYs";

    /**
     * 获取access_token
     * @return  access_token值
     */
    private static String AccessToken(){
        //获取access_token的API地址
        String url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid="+CORPID+"&corpsecret="+CORPSECRET;
        //获取access_token
        String data = WeChatUtils.httpsRequest(url, "GET", null);
        //转json
        JSONObject jsonData = JSON.parseObject(data);
        //获取access_token
        String access_token = jsonData.get("access_token").toString();
        return access_token;
    }

    /**
     * 开放一个获取access_token的方法
     * @return access_token值
     */
    public String getAccessToken(){
        return AccessToken();
    }
}
