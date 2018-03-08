package com.tao.json;

import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Author: Douglass
 * Date: 2016/4/5
 * E-mail: xuetao_ni@163.com
 */
public class JsonOper {
    private static Map<String, Object> datas = new HashMap<>();

    public static void add(String key, Object value){
        datas.put(key, value);
    }

    public static String put2Json(Map<String, Object> data){
        /**
         * put2Json 将数据打包成json格式
         * @author: Douglass
         * @time: 2016/4/6 18:48
         * @params: [data]
         * @return: java.lang.String
         * @throw:
         */
        JSONObject jsonObject = new JSONObject();
        for (Map.Entry<String, Object> entry : data.entrySet()){
            jsonObject.put(entry.getKey(), entry.getValue());
        }

        for (Map.Entry<String, Object> entry : datas.entrySet()){
            jsonObject.put(entry.getKey(), entry.getValue());
        }
        return jsonObject.toString();
    }


}
