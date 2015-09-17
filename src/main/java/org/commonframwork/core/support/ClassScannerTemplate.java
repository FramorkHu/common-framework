package org.commonframwork.core.support;

import org.commonframwork.core.ClassScanner;
import org.commonframwork.util.ClassUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by huyan on 2015/9/17.
 */
public abstract class ClassScannerTemplate {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClassScannerTemplate.class);

    final String packageName;

    protected ClassScannerTemplate(String packageName){
        this.packageName = packageName;
    }

    public List<Class<?>> getClassList() {
        List<Class<?>> classList = new ArrayList<Class<?>>();
        try {
            Enumeration<URL> urls = ClassUtil.getClassLoader().getResources(packageName.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                if (url != null) {
                    String protocol = url.getProtocol();
                    if (protocol.equals("file")) {
                        String packagePath = url.getPath();
                        addClass(classList, packagePath, packageName);

                    } else if (protocol.equals("jar")) {
                        JarURLConnection jarURLConnection = (JarURLConnection) url.openConnection();
                        JarFile jarFile = jarURLConnection.getJarFile();
                        Enumeration<JarEntry> jarEntries = jarFile.entries();
                        while (jarEntries.hasMoreElements()) {
                            JarEntry jarEntry = jarEntries.nextElement();
                            String jarEntryName = jarEntry.getName();
                            if (jarEntryName.endsWith(".class")) {
                                String className = jarEntryName.substring(0, jarEntryName.lastIndexOf(".")).replaceAll("/", ".");
                                doAddClass(classList, className);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("获取类出错！", e);
            throw new RuntimeException(e);
        }
        return classList;
    }

    private void addClass(List<Class<?>> classList, String packagePath, String packageName) {
        try {
            File[] files = ClassUtil.getClassFiles(packagePath);
            if (files != null) {
                for (File file : files) {
                    String fileName = file.getName();
                    if (file.isFile()) {
                        String className = ClassUtil.getClassName(packageName, fileName);
                        doAddClass(classList,className);
                    } else {
                        String subPackagePath = ClassUtil.getSubPackagePath(packagePath, fileName);
                        String subPackageName = ClassUtil.getSubPackageName(packageName, fileName);
                        addClass(classList, subPackagePath, subPackageName);
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error("添加类出错！", e);
            throw new RuntimeException(e);
        }
    }

    private void doAddClass(List<Class<?>> classList, String className){
        Class<?> clz = ClassUtil.loadClass(className, false);
        if (checkAddClass(clz)){
            classList.add(clz);
        }
    }

    abstract boolean checkAddClass(Class<?> clz);



}
