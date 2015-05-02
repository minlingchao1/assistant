package assistant.app.common.util;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import assistant.app.common.exception.CheckYourCodeException;

import play.templates.JavaExtensions;
import play.utils.Java;






/**
 * 反射工具类
 */
public class InvokerUtil {
    public final static Logger log = LoggerFactory.getLogger(InvokerUtil.class);

    /**
     * 以MAP的KEY作为字段名VALUE作为值调用方法
     * 
     * @param obj
     * @param params
     */
    public static void invokeMethod(Object obj, Map params) {
        Set<Field> fieds = new HashSet<Field>();
        // 获取所有字段
        Java.findAllFields(obj.getClass(), fieds);

        for (Field field : fieds) {
            for (Iterator<Map.Entry<String, Object>> i = params.entrySet().iterator(); i.hasNext();) {
                Entry<String, Object> next = i.next();

                if (next.getKey().toString().equals(field.getName())) {
                    invokeSet(obj, field, next.getValue());
                }
            }
        }
    }

    public static Object invokeGet(Object obj, Field field) {
        Class objClazz = obj.getClass();

        try {

            if (boolean.class.equals(field.getType()) || Boolean.class.equals(field.getType())) {
                try {
                    if (field.getName().contains("is")) {
                        return objClazz.getMethod(field.getName()).invoke(obj);
                    }
                    return objClazz.getMethod("is" + JavaExtensions.capFirst(field.getName())).invoke(obj);
                } catch (Exception e1) {
                }
            }

            return objClazz.getMethod("get" + JavaExtensions.capFirst(field.getName())).invoke(obj);
        } catch (Exception e) {
            try {
                return field.get(obj);
            } catch (Exception e1) {
                log.error(e.getMessage(), e1);
                throw new CheckYourCodeException(e);
            }
        }
    }

    public static void invokeSet(Object obj, Field field, Object fieldValue) {
        Class objClazz = obj.getClass();

        try {
            objClazz.getMethod("set" + JavaExtensions.capFirst(field.getName()), field.getType()).invoke(obj,
                    fieldValue);
        } catch (Exception e) {
            try {
                field.set(objClazz, fieldValue);
            } catch (Exception e1) {
                log.error(e.getMessage(), e);
                throw new CheckYourCodeException(e);
            }
        }
    }

    public static Object parseType(Field field, Object value) {
        if (value == null || StringUtils.isEmpty(value.toString())) {
            return null;
        }
        Class<?> typeClass = field.getType();
        try {
            if (String.class.isAssignableFrom(typeClass)) {
                return value.toString();
            } else if (Long.class.isAssignableFrom(typeClass)) {
                return Long.parseLong(value.toString());
            } else if (Integer.class.isAssignableFrom(typeClass)) {
                return Integer.parseInt(value.toString());
            } else if (Boolean.class.isAssignableFrom(typeClass)) {
                if ("1".equals(value)) {
                    value = "true";
                }
                return Boolean.valueOf(value.toString());
            } else if (Double.class.isAssignableFrom(typeClass)) {
                return Double.parseDouble(value.toString());
            } else if (Date.class.isAssignableFrom(typeClass)) {

                return getDate(value);
            }
        } catch (Exception e) {
            throw new CheckYourCodeException(e);
        }
        return null;
    }

    public static Date getDate(Object value) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(value.toString());
        } catch (Exception e) {
            try {
                return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").parse(value.toString());
            } catch (Exception e1) {
                try {
                    return new SimpleDateFormat("yyyy/MM/dd").parse(value.toString());
                } catch (Exception e2) {
                    try {
                        return new SimpleDateFormat("yyyy-MM-dd").parse(value.toString());
                    } catch (Exception e3) {
                        return null;
                    }
                }
            }
        }
    }


}
