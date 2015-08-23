package org.commonframwork.util;

import org.commonframwork.core.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by huyan on 15/8/23.
 */
public class WebUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebUtil.class);

    public static void writeJSONToResponse(HttpServletResponse response, Object data){

        response.setContentType("application/json");
        response.setCharacterEncoding(Constant.UTF_8);

        try {
            PrintWriter writer = response.getWriter();
            writer.write(JsonUtil.toJSON(data));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            LOGGER.error("writeJSONToResponse is error",e);
        }
    }
}
