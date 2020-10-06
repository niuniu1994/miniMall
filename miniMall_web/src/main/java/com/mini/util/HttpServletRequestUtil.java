package com.mini.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: miniMall
 * @description:
 * @author: xin
 * @create: 2020-09-28 15:55
 **/
public class HttpServletRequestUtil {
    public static int getInt(HttpServletRequest request, String key) {
        try {
            return Integer.decode(request.getParameter(key));
        } catch (Exception e) {
            return 0;
        }
    }

    public static long getLong(HttpServletRequest request, String key) {
        try {
            return Long.parseLong(request.getParameter(key));
        } catch (Exception e) {
            return 0;
        }
    }

    public static Double getDouble(HttpServletRequest request, String key) {
        try {
            return Double.valueOf(request.getParameter(key));
        } catch (Exception e) {
            return 0D;
        }
    }

    public static boolean getBoolean(HttpServletRequest request, String key) {
        try {
            return Boolean.parseBoolean(request.getParameter(key));
        } catch (Exception e) {
            return false;
        }
    }

    public static String getString(HttpServletRequest request, String key) {
        try {
            String result = request.getParameter(key);
            if (result != null) {
                result = result.trim();
            }
            if ("".equals(result)) {
                result = null;
            }
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
