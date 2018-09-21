package com.test.ali.controller.oss;

import com.test.ali.util.OssUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: spring-boot-ali-util
 * @package: com.test.ali
 * @email: cy880708@163.com
 * @date: 2018/9/18 下午3:47
 * @mofified By:
 */
@Controller
public class OssTestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        OssUtil.downloadFileContent("test_string");
        return "";
    }

}
