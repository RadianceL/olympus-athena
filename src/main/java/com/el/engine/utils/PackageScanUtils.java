package com.el.engine.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.util.ClassUtils;
import org.springframework.util.SystemPropertyUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * 包扫描 <br>
 * since 2020/8/25
 *
 * @author eddie.lys
 */
@Slf4j
public class PackageScanUtils {

    /**
     * 扫描  scanPackages 下的文件的匹配符
     */
    protected static final String DEFAULT_RESOURCE_PATTERN = "**/*.class";

    /**
     * 根据扫描包的,查询下面的所有类
     *
     * @param scanPackages 扫描的package路径
     */
    public static Set<String> findPackageClass(String scanPackages, Class<? extends Annotation> anno) {
        Set<String> classSet = new HashSet<>();
        if (StringUtils.isBlank(scanPackages)) {
            return classSet;
        }
        // 验证及排重包路径,避免父子路径多次扫描
        Set<String> packages = checkPackage(scanPackages);
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

        for (String basePackage : packages) {
            if (StringUtils.isBlank(basePackage)) {
                continue;
            }
            String basePackagePlaceholders = SystemPropertyUtils.resolvePlaceholders(basePackage);
            String packageSearchPath =
                    ResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX
                            + ClassUtils.convertClassNameToResourcePath(basePackagePlaceholders)
                            + "/"
                            + DEFAULT_RESOURCE_PATTERN;
            try {
                Resource[] resources = resourcePatternResolver.getResources(packageSearchPath);
                for (Resource resource : resources) {
                    // 检查resource，这里的resource都是class
                    String className = loadClassName(metadataReaderFactory, resource);
                    if (Objects.nonNull(anno)) {
                        if (Objects.isNull(Class.forName(className).getAnnotation(anno))) {
                            continue;
                        }
                    }
                    classSet.add(className);
                }
            } catch (Exception e) {
                log.error("获取包下面的类信息失败,package:" + basePackage, e);
            }
        }
        return classSet;
    }

    /**
     * 排重、检测package父子关系，避免多次扫描
     *
     * @param scanPackages 目标包
     * @return 返回检查后有效的路径集合
     */
    private static Set<String> checkPackage(String scanPackages) {
        Set<String> packages = new HashSet<>();
        if (StringUtils.isBlank(scanPackages)) {
            return packages;
        }
        // 排重路径
        Collections.addAll(packages, scanPackages.split(","));
        for (String pInArr : packages.toArray(new String[0])) {
            if (StringUtils.isBlank(pInArr) || ".".equals(pInArr) || pInArr.startsWith(".")) {
                continue;
            }
            if (pInArr.endsWith(".")) {
                pInArr = pInArr.substring(0, pInArr.length() - 1);
            }
            Iterator<String> packageIte = packages.iterator();
            boolean needAdd = true;
            while (packageIte.hasNext()) {
                String pack = packageIte.next();
                if (pInArr.startsWith(pack + ".")) {
                    // 如果待加入的路径是已经加入的pack的子集，不加入
                    needAdd = false;
                } else if (pack.startsWith(pInArr + ".")) {
                    // 如果待加入的路径是已经加入的pack的父集，删除已加入的pack
                    packageIte.remove();
                }
            }
            if (needAdd) {
                packages.add(pInArr);
            }
        }
        return packages;
    }

    /**
     * 加载资源，根据resource获取className
     *
     * @param metadataReaderFactory spring中用来读取resource为class的工具
     * @param resource              这里的资源就是一个Class
     */
    private static String loadClassName(
            MetadataReaderFactory metadataReaderFactory, Resource resource) {
        try {
            if (resource.isReadable()) {
                MetadataReader metadataReader = metadataReaderFactory.getMetadataReader(resource);
                return metadataReader.getClassMetadata().getClassName();
            }
        } catch (Exception e) {
            log.error("根据resource获取类名称失败", e);
        }
        return null;
    }
}
