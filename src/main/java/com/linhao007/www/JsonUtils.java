package com.linhao007.www;




import com.linhao007.www.exception.JsonException;
import com.linhao007.www.innerJsonImpl.ClassToJson;
import com.linhao007.www.innerJsonImpl.CollectionTojson;
import com.linhao007.www.innerJsonImpl.MapToJson;

import java.util.Collection;
import java.util.Map;

/**
 * Created by www.linhao007.com on 2016-8-4.
 * 用于到家request转换成json
 */
public class JsonUtils {
    public static String getJsonString(Object daoJiaRequest) throws IllegalAccessException, JsonException {
        System.out.println("*********对象转换成json入参:" + daoJiaRequest.toString() + "********");
        JsonIstrategy jsonIstrategy;
        //如果传入的对象为集合 采用集合策略
        if (daoJiaRequest instanceof Collection) {
            jsonIstrategy = new JsonIstrategy(new CollectionTojson());
            String jsonString = jsonIstrategy.operate(daoJiaRequest);
            System.out.println("*********对象转换成json出参:" + jsonString + "********");
            return jsonString;
        }
        //如果获取的对象类型为一个Map集合  采用map策略
        if (daoJiaRequest instanceof Map) {
            jsonIstrategy = new JsonIstrategy(new MapToJson());
            String jsonString = jsonIstrategy.operate(daoJiaRequest);
            System.out.println("*********对象转换成json出参:" + jsonString + "********");
            return jsonString;
        }
        jsonIstrategy = new JsonIstrategy(new ClassToJson());
        String jsonString = jsonIstrategy.operate(daoJiaRequest);
        System.out.println("*********对象转换成json出参:" + jsonString + "********");
        return jsonString;
    }
}
