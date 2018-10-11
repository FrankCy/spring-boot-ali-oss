package com.test.ali.controller.oss;

import com.test.ali.controller.pojo.FileBean;
import com.test.ali.util.OssUtil;
import com.test.ali.util.ZipUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
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
    public void download(HttpServletResponse response) {
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
            String[] files = new String[]{"http://m.xiaopaosudai.com//uploads_mnt/images/2018/09/26/98cb9c00a34647838ed3289d765df92d.jpg",
                    "http://m.xiaopaosudai.com//uploads_mnt/images/2018/09/26/e7360d485b68428b95f084e15caaa37d.jpg",
            "http://m.xiaopaosudai.com//uploads_mnt/images/2018/09/26/dc21674c099244e095b37c1bb00c0a91.jpg",
            "http://m.xiaopaosudai.com//uploads_mnt/images/2018/09/26/8f7fd2c0dad7471d8d2a891961691caf.jpg"};
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
            zos.flush();
            zos.close();
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * @description：实现多文件导出（本地文件导出）
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/10/11 上午10:42
     * @mofified By:
     */
    @RequestMapping("/downloads")
    public void downloads(HttpServletRequest request, HttpServletResponse response){
        String zipName = "myfile.zip";
//        List<FileBean> fileList = fileService.getFileList();//查询数据库中记录

        List<FileBean> fileList = new ArrayList<FileBean>();
        for(int i=0;i<5;i++) {
            FileBean fb = new FileBean();
            fb.setFileId(i++);
            fb.setFileName("图片名"+i);
            fb.setFilePath("文件所在地址");
            fileList.add(fb);
        }

        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition","attachment; filename="+zipName);
        ZipOutputStream out = null;
        try {
            out = new ZipOutputStream(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            for(Iterator<FileBean> it = fileList.iterator(); it.hasNext();){
                FileBean file = it.next();
                ZipUtils.doCompress(file.getFilePath(), out);
                response.flushBuffer();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
