package org.commonframwork.core;

import org.commonframwork.ioc.annotation.Bean;
import org.commonframwork.util.ClassUtil;
import org.junit.Test;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by huyan on 2015/8/25.
 */
public class ClassHelperTest {

    @Test
    public void findClassByAnnotationTest(){


        //getPackageAllClasses("org.apache.commons.io", true);
        /*try {
            Enumeration<URL> enumeration =
                    this.getClass().getClassLoader().getResources("org.apache");
            while (enumeration.hasMoreElements()){
                URL url = enumeration.nextElement();
                System.out.println(url.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        List<Class<?>> classes =
                ClassUtil.getClassListByAnnotation(" ", Bean.class);

        System.out.println(classes.size());
    }


    public static Set<Class<?>> getPackageAllClasses(String basePackage,
                                              boolean recursive) {
        Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
        String packageName = basePackage;
        if (packageName.endsWith(".")) {
            packageName = packageName
                    .substring(0, packageName.lastIndexOf('.'));
        }
        String package2Path = packageName.replace('.', '/');

        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(
                    package2Path);
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");

                } else if ("jar".equals(protocol)) {

                    doScanPackageClassesByJar(packageName, url, recursive,
                            classes);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }


    private static void doScanPackageClassesByJar(String basePackage, URL url,
                                           final boolean recursive, Set<Class<?>> classes) {
        String packageName = basePackage;
        String package2Path = packageName.replace('.', '/');
        JarFile jar;
        try {
            jar = ((JarURLConnection) url.openConnection()).getJarFile();
            Enumeration<JarEntry> entries = jar.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                String name = entry.getName();
                if (!name.startsWith(package2Path) || entry.isDirectory()) {
                    continue;
                }

                // 判断是否递归搜索子包
                if (!recursive
                        && name.lastIndexOf('/') != package2Path.length()) {
                    continue;
                }

                String classSimpleName = name
                        .substring(name.lastIndexOf('/') + 1);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
