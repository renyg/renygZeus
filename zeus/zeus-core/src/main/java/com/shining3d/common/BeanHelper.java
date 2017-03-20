package com.shining3d.common;

import com.shining3d.zeus.constant.BizConstant;
import com.shining3d.zeus.exception.BizException;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by fe on 2017/1/6.
 */
public class BeanHelper {

    public static void copyProperties(Object desc,Object src) {
        try {
            BeanUtils.copyProperties(desc,src);
        } catch (IllegalAccessException e) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR,"copy异常");
        } catch (InvocationTargetException e) {
            throw new BizException(BizConstant.COMMON_PARAM_ERROR,"copy异常");
        }
    }
}
