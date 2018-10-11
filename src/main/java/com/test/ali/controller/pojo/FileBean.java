package com.test.ali.controller.pojo;

import java.io.Serializable;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: spring-boot-ali-oss
 * @package: com.test.ali.controller.pojo
 * @email: cy880708@163.com
 * @date: 2018/10/11 上午10:58
 * @mofified By:
 */
public class FileBean implements Serializable {

    private static final long serialVersionUID = -5452801884470115159L;

    private Integer fileId;//主键

    private String filePath;//文件保存路径

    private String fileName;//文件保存名称

    public FileBean(){

    }

    public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
