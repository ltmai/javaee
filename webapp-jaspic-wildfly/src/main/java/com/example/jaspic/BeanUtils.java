package com.example.jaspic;

import javax.enterprise.inject.spi.CDI;

public class BeanUtils {

    public static <T> T getBeanByType(Class<T> beanClass) {
        try {
            return CDI.current().select(beanClass).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean allNotNull(Object... objects) {
        for (Object object : objects) {
            if (object == null)
                return false;
        }
        return true;
    }
}
