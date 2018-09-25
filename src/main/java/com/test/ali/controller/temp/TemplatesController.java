package com.test.ali.controller.temp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: spring-boot-ali-oss
 * @package: com.test.ali.controller.temp
 * @email: cy880708@163.com
 * @date: 2018/9/25 下午5:57
 * @mofified By:
 */
@Controller
@RequestMapping("/templates")
public class TemplatesController {

    /**
     * 映射地址:/templates/hello
     * @return
     */
    @RequestMapping("/hello")
    public ModelAndView hello(Map<String,Object> map){
        ModelAndView mv = new ModelAndView("hello");
        map.put("name", "道哥");
        return mv;
    }

    @RequestMapping("/helloFtl")
    public String helloFtl(Map<String,Object> map){
        map.put("name", "道哥");
        return "hello";
    }

}
