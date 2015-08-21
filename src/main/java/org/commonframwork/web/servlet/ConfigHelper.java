package org.commonframwork.web.servlet;

import org.commonframwork.core.Constant;
import org.commonframwork.util.PropsUtil;

import java.util.Properties;

/**
 * Created by huyan on 2015/8/21.
 */
public class ConfigHelper {

    private static final Properties configProperties = PropsUtil.loadProps(Constant.CONFIG_PROPS);

    public static String getString(String key){
        return PropsUtil.getString(configProperties, key);
    }

}
