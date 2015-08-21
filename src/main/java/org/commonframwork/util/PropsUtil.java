package org.commonframwork.util;

import org.apache.commons.lang.ClassUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Created by huyan on 2015/8/21.
 */
public class PropsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    /**
     * 加载配置文件
     * @param propsPath
     * @return
     */
    public static Properties loadProps(String propsPath){

        Properties properties = new Properties();
        InputStream inputStream = null;

        try {

            if (StringUtils.isEmpty(propsPath)){
                throw new IllegalArgumentException("[propsPath] should not be empty");
            }
            String suffix = ".properties";
            if (propsPath.lastIndexOf(suffix) == -1){
                propsPath += suffix;
            }
            inputStream = ClassUtil.getClassLoader().getResourceAsStream(propsPath);
            if (inputStream!=null){
                properties.load(inputStream);
            }

        } catch (Exception e){
            LOGGER.error("load Properties is error,",e);
            throw new RuntimeException(e);
        } finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
            } catch (Exception e){
                LOGGER.error("资源释放出错",e);
            }
        }
        return properties;
    }

    /**
     * 获取字符型属性
     */
    public static String getString(Properties props, String key) {
        String value = "";
        if (props.containsKey(key)) {
            value = props.getProperty(key);
        }
        return value;
    }
}
