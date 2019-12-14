package cn.edu.zjut.util;

import java.util.UUID;

public class Utils {
    /**
     * 生成uuid
     *
     * @return
     */
    public static String createUUID() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
