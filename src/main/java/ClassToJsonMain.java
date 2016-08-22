import com.linhao007.www.JsonUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by www.linhao007.com on 2016-8-22.
 * 使用案例
 */
public class ClassToJsonMain {
    public static void main(String[] args) throws IllegalAccessException {
        Map map = new HashMap<String,String>();
        map.put("name","www.linhao007.com ");
        map.put("age","18");
        map.put("sex","man");
        System.out.println("map转换成json格式："+ JsonUtils.getJsonString(map));
    }
}
