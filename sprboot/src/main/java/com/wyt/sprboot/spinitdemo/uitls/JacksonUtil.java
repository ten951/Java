package com.wyt.sprboot.spinitdemo.uitls;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @author Darcy
 *         Created by Administrator on 2016/5/5.
 */
public class JacksonUtil {
    public static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(sdf);
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List,如List<Student>,将第二个参数传递为Student
     * [].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List
     *
     * @param jsonStr   json格式字符串
     * @param valueType 模板Class
     * @param <T>       泛型
     * @return T类型的对象
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            return objectMapper.readValue(jsonStr, valueType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * json数组转List
     *
     * @param jsonStr         json字符串
     * @param collectionClass 容器类的类型
     * @param elementClasses  需要转换的实体类的类型
     * @return
     */
    public static <T> T readValue(String jsonStr, Class<?> collectionClass, Class<?>... elementClasses) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        try {
            JavaType javaType = getCollectionType(collectionClass, elementClasses);
            return objectMapper.readValue(jsonStr, javaType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 把JavaBean转换为json字符串
     *
     * @param object 目标对象
     * @return json字符串
     */
    public static String toJSon(Object object) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            objectMapper.setDateFormat(sdf);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }

        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取泛型的Collection Type
     *
     * @param collectionClass 泛型的Collection
     * @param elementClasses  元素类
     * @return JavaType Java类型
     * @since 1.0
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * @param object   javaBean 实体
     * @param response 响应实体
     */
    public static void writeJson(Object object, HttpServletResponse response) {
        response.setHeader("Content-Language", "zh-cn");
        response.setHeader("Cache-Control", "no-cache");
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            String jsonStr = JacksonUtil.toJSon(object);
            if (StringUtils.isEmpty(jsonStr)) {
                jsonStr = "{ \"code\":\"" + "-600" + "\"," + "\"message\":\"" + "序列化参数错误!\" }";
            }
            response.getWriter().write(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
