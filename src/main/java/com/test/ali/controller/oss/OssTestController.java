package com.test.ali.controller.oss;

import com.test.ali.util.OssUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

    /**
     * @description：实现多文件导出
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/10/11 上午10:42
     * @mofified By:
     */
    @RequestMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response){
        try {
            //文件的名称
            String downloadFilename = "中文.zip";
            //转换中文否则可能会产生乱码
            downloadFilename = URLEncoder.encode(downloadFilename, "UTF-8");
            // 指明response的返回对象是文件流
            response.setContentType("application/octet-stream");
            // 设置在下载框默认显示的文件名
            response.setHeader("Content-Disposition", "attachment;filename=" + downloadFilename);
            ZipOutputStream zos = new ZipOutputStream(response.getOutputStream());
            String[] files = new String[]{"https://www.baidu.com/img/baidu_jgylogo3.gif","https://www.baidu.com/img/baidu_jgylogo3.gif"};
            for (int i=0;i<files.length;i++) {
                URL url = new URL(files[i]);
                zos.putNextEntry(new ZipEntry(i+".jpg"));
                InputStream fis = url.openConnection().getInputStream();
                byte[] buffer = new byte[1024];
                int r = 0;
                while ((r = fis.read(buffer)) != -1) {
                    zos.write(buffer, 0, r);
                }
                fis.close();
            }
            zos.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
