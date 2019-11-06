package com.petrochina.e7.monitor.commons.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName com.petrochina.e7.monitor.utils
 * @ClassName: JsonUtils
 * @Description: TODO
 * @Author: Administrator
 * @Date: 2019/07/26 0026$ 13:50$
 * @Version: 1.0
 */
public class JsonUtils {


    private int code;
    private String msg;
    private int count;
    private List data;






    /**
     * @return
     * @Author mzc
     * @Description //TODO List集合转json
     * @Date 14:03 2019/07/26 0026
     * @Param
     **/
    public static JSONObject List2Json(int code, String msg, int count, List<?> list) {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("count", count);
        jsonObject.put("data", list);
        return jsonObject;
    }

    public static JSONObject List2Json2(List<?> list) {
//        String str = JSON.toJSONString(list);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("data",list);
        return jsonObject;
    }


    /**
     * @Author mzc
     * @Description //TODO 把JSON数据转换成指定的java对象列表
     * @Date 14:19 2019/08/13 0013
     * @Param [jsonString, clazz]
     * @return java.util.List<T>
    **/
    public static <T> List<T> jsonToList(String jsonString, Class<T> clazz) {
        @SuppressWarnings("unchecked")
        List<T> ts = (List<T>) JSONArray.parseArray(jsonString, clazz);
        return ts;
    }

    /**
     * @Author mzc
     * @Description //TODO 对象转换成json数据
     * @Date 14:28 2019/08/12 0012
     * @Param [obj]
     * @return java.lang.String
    **/
    public static String obj2json(Object obj) throws Exception {
        return JSON.toJSONString(obj);
    }

    /**
     * @Author mzc
     * @Description //TODO 把JSON数据转换成对象
     * @Date 14:29 2019/08/12 0012
     * @Param [jsonStr, clazz]
     * @return T
    **/
    public static <T> T json2obj(String jsonStr, Class<T> clazz) throws Exception {
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * @Author mzc
     * @Description //TODO Json 转Map
     * @Date 14:29 2019/08/12 0012
     * @Param [jsonStr]
     * @return java.util.Map<java.lang.String,java.lang.Object>
    **/
    public static <T> Map<String, Object> json2map(String jsonStr) throws Exception {
        return JSON.parseObject(jsonStr, Map.class);
    }

    /**
     * @Author mzc
     * @Description //TODO Map转Json
     * @Date 14:29 2019/08/12 0012
     * @Param [map, clazz]
     * @return T
    **/
    public static <T> T map2obj(Map<?, ?> map, Class<T> clazz) throws Exception {
        return JSON.parseObject(JSON.toJSONString(map), clazz);
    }

    /**
     * @Author mzc 
     * @Description //TODO 把JSON数据转换成较为复杂的List<Map<String, Object>>
     * @Date 14:12 2019/08/29 0029
     * @Param [jsonData]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
    **/
    public static List<Map<String, Object>> getJsonToListMap(String jsonData) throws  Exception{
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        try {
            // 两种写法
            // list = JSON.parseObject(jsonString, new
            // TypeReference<List<Map<String, Object>>>(){}.getType());
            list = JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将map转化为string
     *
     * @param map
     * @return String
     */
    public static JSONObject mapToString(int code, String msg, int count, Map map) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("count", count);
        jsonObject.put("data", map);
        return jsonObject;
    }

    /**
     * 将map转化为string
     *
     * @param map
     * @return String
     */
    public static String mapToString(Map map) {
        String str = JSONObject.toJSONString(map);
        return str;
    }


}
