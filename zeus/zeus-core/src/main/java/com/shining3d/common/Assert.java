package com.shining3d.common;

import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.exception.BizException;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * Created by fe on 2017/1/6.
 */
public abstract class Assert {

    public static void assertNotMinus(Integer i,String msg) {
        if (i.compareTo(0) == -1) throw new BizException(BizConstant.COMMON_PARAM_ERROR,msg);
    }

    public static void assertNotZero(Integer i,String msg) {
        if (i.compareTo(0) == 0) throw new BizException(BizConstant.COMMON_PARAM_ERROR,msg);
    }

    public static void assertNotMinusAndZero(Integer i,String msg) {
        if (i.compareTo(0) == -1 || i.compareTo(0) == 0) throw new BizException(BizConstant.COMMON_PARAM_ERROR,msg);
    }

    public static void assertNotNull(Object param) {
        Assert.assertNotNull(Arrays.asList(param), BizConstant.COMMON_PARAM_ERROR,"参数缺失!");
    }

    public static void assertNotNull(Object param,String msg) {
        Assert.assertNotNull(Arrays.asList(param), BizConstant.COMMON_PARAM_ERROR,msg);
    }

    public static void assertNotEquals(String paramOne,String paramTwo,String msg) {
        if (!StringUtils.equals(paramOne,paramTwo)) throw new BizException(BizConstant.COMMON_PARAM_ERROR,msg);
    }

    public static void assertNotNull(Object param,String code,String msg) {
        Assert.assertNotNull(Arrays.asList(param),code,msg);
    }

    public static void assertNotNull(List<Object> paramList) {
        assertNotNull(paramList, BizConstant.COMMON_PARAM_ERROR, "参数缺失!");
    }

    public static void assertNotNull(List<Object> paramList,String msg) {
        assertNotNull(paramList, BizConstant.COMMON_PARAM_ERROR, msg);
    }

    public static void assertNotNull(List<Object> paramList,String code,String msg) {
        Optional.ofNullable(paramList).ifPresent(list -> {
            list.forEach(item -> {
                if (item instanceof String) {
                    String str = (String)item;
                    if (StringUtils.isEmpty(str)) throw new BizException(code,msg);
                } else if (item instanceof Collection) {
                    Collection<?> collections = (Collection<?>)item;
                    if (CollectionUtils.isEmpty(collections)) throw new BizException(code,msg);
                } else {
                    Optional.ofNullable(item).orElseThrow(() -> {
                        return new BizException(code,msg);
                    });
                }
            });
        });
    }
}
