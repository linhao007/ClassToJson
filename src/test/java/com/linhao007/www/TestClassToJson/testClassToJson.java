package com.linhao007.www.TestClassToJson;

import com.linhao007.www.JsonUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by www.linhao007.com on 2016-8-27.
 */
public class testClassToJson {

    @Test
    public void testClassToJson() throws IllegalAccessException {
        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(1, "5264202");
        map.put(2, "321568");
        map.put(3, "5526987");
        System.out.println(JsonUtils.getJsonString(map));

    }
}
