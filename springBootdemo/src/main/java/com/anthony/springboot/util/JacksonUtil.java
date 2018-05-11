package com.anthony.springboot.util;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.sun.xml.bind.marshaller.NamespacePrefixMapper;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.namespace.QName;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by bl03435 on 2015/9/21.
 */
public class JacksonUtil {
    private static final Log logger = LogFactory.getLog(JacksonUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();
    
    static{
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <T> T formJson(Class<T> clazz, String message) {
        try {
            T bean = mapper.readValue(message, clazz);
            return bean;
        } catch (Exception e) {
            logger.error("", e);
        }

        return null;

    }

    public static <T> List<T> formListJson(Class<T> clazz, String message) {
        try {
            List<T> bean = mapper.readValue(message, TypeFactory.defaultInstance().constructCollectionType(List.class,
                    clazz));
            return bean;
        } catch (Exception e) {
            logger.error("", e);
        }

        return null;

    }

    @SuppressWarnings("deprecation")
    public static String toJson(Object target) {
        try {
            StringWriter sw = new StringWriter();
            JsonGenerator gen = new JsonFactory().createJsonGenerator(sw);
            mapper.writeValue(gen, target);
            gen.close();
            return sw.toString();
        } catch (Exception e) {
            logger.error("", e);
        }
        return null;
    }

    /**
     *
     * @param target 要转化的对象
     * @param type 要转换成的类型，目前只支持"xml"、"json"两种
     * @param rootElement type是"xml"时需要定义根节点
     * @param npMapper type是"xml"时需要定义NamespacePrefixMapper
     * @return
     */
    public static String serialization(Object target,String type,String rootElement,NamespacePrefixMapper npMapper) {
        if ("XML".equalsIgnoreCase(type)) {
            return JAXBUtil.toXML(target, null,  new QName("",rootElement), npMapper);
        } else if ("json".equalsIgnoreCase(type)) {
            return toJson(target);
        }
        return null;
    }

    /**
     * 把特定类型的字符串转成一个JavaBean对象的实例
     * @param clazz Java class
     * @param message 字符串
     * @param type 字符串的类型
     * @return
     */
    public static <T> T deSerialization(Class<T> clazz, String message,String type) {
        if ("XML".equalsIgnoreCase(type)) {
            return JAXBUtil.formXML(clazz,message);
        } else if ("json".equalsIgnoreCase(type)) {
            return formJson(clazz,message);
        }
        return null;
    }

    /**
     * 将json字符串转化为map
     * @param jsonString json字符串
     * @return 对象map
     */
    public static Map<String,Object> toMap(String jsonString) {
        TypeReference<HashMap<String,Object>> typeRef = new TypeReference<HashMap<String,Object>>() {};
        HashMap<String, Object> map = null;
        try {
            map = mapper.readValue(jsonString, typeRef);
        } catch (IOException e) {
            logger.error("", e);
        }
        return map;

    }

    /**
     * 将json字符串转化为指定类型
     * @param jsonString json字符串
     * @param typeRef 需要的类型
     * @return 需要的对象
     */
    public static <T> T  toSpecifiedType(String jsonString, TypeReference<T> typeRef) {
        Object obj = null;
        try {
            obj = mapper.readValue(jsonString, typeRef);
        } catch (IOException e) {
            logger.error("", e);
        }
        return (T) obj;
    }

}
