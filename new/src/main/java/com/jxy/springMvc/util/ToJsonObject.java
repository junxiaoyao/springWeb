package com.jxy.springMvc.util;
import com.alibaba.fastjson.JSONObject;
public class ToJsonObject {
    public static JSONObject ticketData(int[] data, String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("data", data);
        jsonObject.put("type", "bar");
        return jsonObject;
    }

    public static JSONObject turnoversData(double[] data, String name) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", name);
        jsonObject.put("data", data);
        jsonObject.put("type", "bar");
        return jsonObject;
    }
}
