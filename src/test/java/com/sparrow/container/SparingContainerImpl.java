package com.sparrow.container;

import com.sparrow.cg.MethodAccessor;
import com.sparrow.constant.SYS_OBJECT_NAME;
import com.sparrow.core.TypeConverter;
import com.sparrow.enums.CONTAINER;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by harry on 2018/2/9.
 * 这里是示例demo具体代码业务端可以实现
 */
public class SparingContainerImpl implements Container {
    private ApplicationContext applicationContext;

    @Override
    public MethodAccessor getProxyBean(Class<?> clazz) {
        return null;
    }

    @Override
    public List<TypeConverter> getFieldList(Class clazz) {
        return null;
    }

    @Override
    public Map<String, Method> getControllerMethod(String clazzName) {
        return null;
    }

    @Override
    public <T> T getBean(String beanName) {
        return (T)applicationContext.getBean(beanName);
    }

    @Override
    public <T> T getBean(SYS_OBJECT_NAME sysObjectName) {
        return null;
    }

    @Override
    public Map<String, Object> getAllBean() {
        return null;
    }

    @Override
    public void init() {
        applicationContext = new XmlWebApplicationContext();
    }

    @Override
    public void init(String xmlName, String systemConfigPath) {

    }

    @Override
    public Map<String, Object> getBeanMap(CONTAINER container) {
        return null;
    }
}