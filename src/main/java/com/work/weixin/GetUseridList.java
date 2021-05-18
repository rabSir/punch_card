package com.work.weixin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.work.weixin.utils.WeChatUtils;

import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 获取部门下所有用户
 */
public class GetUseridList {
    //部门id默认为1
    private static final int DEPARTMENT_ID = 1;
    //开启递归
    private static final int FETCH_CHILD = 1;

    private static HashMap<String,String> useridList(){
        //设置一个存放usrid、name的集合
        HashMap<String,String> useridMap = new HashMap<String, String>();

        //获取access_token
        GetAccessToken get = new GetAccessToken();
        String access_token = get.getAccessToken();
        //设置请求地址
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/simplelist?access_token="+access_token+"&department_id="+DEPARTMENT_ID+"&fetch_child="+FETCH_CHILD;
        //获取部门成员
        String dept = WeChatUtils.httpsRequest(url, "GET", null);
        //转json
        JSONObject json_dept = JSON.parseObject(dept);
        //获取其下的userList
        String userlist_data = json_dept.get("userlist").toString();
        List userlist = JSONObject.parseArray(userlist_data);
        for (int i=0;i<userlist.size();i++){
            //获取每一个用户信息
            String user = JSON.toJSONString(userlist.get(i));
            //获取其中的name和userid
            JSONObject json_user = JSON.parseObject(user);
            String name = json_user.get("name").toString();
            String userid = json_user.get("userid").toString();
            //存放入hashmap中
            useridMap.put(userid, name);
        }
        return useridMap;
    }

    /**
     * 开放一个方法
     * @return
     */
    public HashMap<String,String> getUseridList(){
        return useridList();
    }

    public static void main(String[] args) {
        useridList();
    }
}
