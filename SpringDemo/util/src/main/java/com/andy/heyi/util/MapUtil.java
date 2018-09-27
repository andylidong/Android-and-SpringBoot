package com.andy.heyi.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName MapUtil
 * @Description: TODO
 * @Author: lidong
 * @CreateDate: 2018/9/26$ 4:17 PM$
 * @UpdateUser: lidong
 * @UpdateDate: 2018/9/26$ 4:17 PM$
 * @UpdateRemark: TODO
 * @Version: 1.0
 **/
public class MapUtil {
    /**
     * 实体对象转成Map
     *
     * @param obj 实体对象
     * @return
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }

        ObjectMapper mapper = new ObjectMapper();

        try {
            String json = mapper.writeValueAsString(obj);
            return mapper.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
