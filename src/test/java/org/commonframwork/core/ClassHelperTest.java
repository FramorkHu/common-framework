package org.commonframwork.core;

import org.commonframwork.ioc.annotation.Bean;
import org.commonframwork.util.ClassUtil;
import org.junit.Test;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by huyan on 2015/8/25.
 */
public class ClassHelperTest {

    @Test
    public void findClassByAnnotationTest(){

        try {
            Enumeration<URL> enumeration =
                    this.getClass().getClassLoader().getResources("");
            while (enumeration.hasMoreElements()){
                URL url = enumeration.nextElement();
                System.out.println(url.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*List<Class<?>> classes =
                ClassUtil.getClassListByAnnotation("", Bean.class);

        System.out.println(classes.size());*/
    }
}
