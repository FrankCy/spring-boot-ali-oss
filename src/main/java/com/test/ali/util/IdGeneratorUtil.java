package com.test.ali.util;

import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: spring-boot-ali-oss
 * @package: com.test.ali.util
 * @email: cy880708@163.com
 * @date: 2018/9/21 下午7:23
 * @mofified By:
 */
public class IdGeneratorUtil {

    /**
     * @description：创建无 "-" 的32位uuid
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/9/21 下午7:25
     * @mofified By:
     */
    public static String get32UUID() {
        String uuid = (UUID.randomUUID().toString()).replace("-", "");

        if(StringUtils.isEmpty(uuid)) {
            return "";
        }
        return uuid;
    }

}
