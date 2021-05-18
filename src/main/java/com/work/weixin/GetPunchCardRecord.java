package com.work.weixin;

import com.alibaba.fastjson.JSONObject;
import com.work.weixin.utils.WeChatUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 获取打卡记录
 */
public class GetPunchCardRecord {

    public static JSONObject getPunchCardRecord(){
        //设置json类型userid
        String userid="";
        //获取access_token
        GetAccessToken get = new GetAccessToken();
        String access_token = get.getAccessToken();
        //设置请求地址
        String url = "https://qyapi.weixin.qq.com/cgi-bin/checkin/getcheckindata?access_token="+access_token;
        //获取用户信息
        GetUseridList getlist = new GetUseridList();
        HashMap<String,String> useridMap = getlist.getUseridList();
        //获取key
        Set<Map.Entry<String, String>> entries = useridMap.entrySet();
        for (Map.Entry<String,String> entrie: entries) {
            userid = userid+"\""+entrie.getKey()+"\""+",";
        }
        //设置传参
        String body ="{\n" +
                "   \"opencheckindatatype\": 3,\n" +
                "   \"starttime\": 1619798400,\n" +
                "   \"endtime\": 1622476800,\n" +
                "   \"useridlist\": ["+userid.substring(0,userid.length()-1)+"]\n" +
                "}";
        //获取打卡记录
        String punchCartRecord = WeChatUtils.httpsRequest(url, "POST", body);
        System.out.println(punchCartRecord);
        return null;
    }
    public static void main(String[] args) {
//        String access_token = "uvaLqoT-iUS60vki_0es7-lqKoYdL3vpCihWJpsA-_JU4N7iE6gyk7FcHt6-1U_QTiH5bKHnUCFbBQ1z9qLpFlEnI6yLTeNxLgQsmZd7EYOKSeQ2zSYcEAdl1keIvoMHt5twjt8cD-5FSZrPQxykYgdWiNe16gFnx1nxPlSkYkyPox1gp4cz6B37eklH_SHJtBOO1HiS6xitpmN1EH0aIg";
//        String url = "https://qyapi.weixin.qq.com/cgi-bin/checkin/getcheckindata?access_token="+access_token;
//        String body = "{\n" +
//                "   \"opencheckindatatype\": 3,\n" +
//                "   \"starttime\": 1619798400,\n" +
//                "   \"endtime\": 1622476800,\n" +
//                "   \"useridlist\": [\"XuJiangNan\"]\n" +
//                "}";
//        String post = WeChatUtils.httpsRequest(url, "POST", body);
//        System.out.println(post);
        getPunchCardRecord();
    }
}
