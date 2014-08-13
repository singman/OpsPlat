package com.generalsoft.singman.utils;

import java.util.regex.Pattern;

/**
 * User: singman
 * Date: 2014/8/13
 * Time: 16:57
 * Project:CFS_v0.2
 * Usage:公共工具类
 */

/**
 *判断是否是数字类型
 */
public class utils {
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }
}
