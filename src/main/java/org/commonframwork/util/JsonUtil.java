package org.commonframwork.util;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by huyan on 15/8/23.
 */
public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static <T> String toJSON(T data){
        String jsonStr = "";

        try {
            jsonStr = OBJECT_MAPPER.writeValueAsString(data);
        } catch (IOException e) {
            LOGGER.error("数据对象转化json出错",e);
        }
        return jsonStr;
    }
}
