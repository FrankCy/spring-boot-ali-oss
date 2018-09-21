package com.test.ali.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import javax.naming.TimeLimitExceededException;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: spring-boot-ali-oss
 * @package: com.test.ali.util
 * @email: cy880708@163.com
 * @date: 2018/9/21 下午6:58
 * @mofified By:
 */
public class FreemarkerUtils {

    public static String loadFtlHtml(File baseDir, String fileName, Map globalMap){

        if(baseDir == null || !baseDir.isDirectory() || globalMap ==null || fileName == null || "".equals(fileName)){
            throw new IllegalArgumentException("Directory file");
        }

        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        try {

            cfg.setDirectoryForTemplateLoading(baseDir);
            cfg.setDefaultEncoding("UTF-8");
            //.RETHROW
            cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            cfg.setClassicCompatible(true);
            Template temp = cfg.getTemplate(fileName);

            StringWriter stringWriter = new StringWriter();
            temp.process(globalMap, stringWriter);

            return stringWriter.toString();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("load fail file");
        } catch (TemplateException te) {
            te.printStackTrace();
            throw new RuntimeException("load fail file");
        }
    }


}
