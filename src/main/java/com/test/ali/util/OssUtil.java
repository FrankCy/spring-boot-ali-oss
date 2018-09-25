package com.test.ali.util;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;

import java.io.*;

/**
 * @version 1.0
 * @description：
 * @author: Yang.Chang
 * @project: spring-boot-ali-util
 * @package: com.test.ali.util
 * @email: cy880708@163.com
 * @date: 2018/9/18 下午3:05
 * @mofified By:
 */
public class OssUtil {

    /**
     * @description：正式环境accessKeyId
     */
    private static String keyId = "";

    /**
     * @description：正式环境accessKeySecret
     */
    private static String keySecret = "";

    /**
     * @description：默认bucket
     */
    private static String defaultBucket = "test-bucket";

    /**
     * @description：默认endpoint
     */
    private static String defaultEndpoint = "http://util-cn-beijing.aliyuncs.com";;

    /**
     * @description：动态创建Oss-Bucket
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/9/18 下午3:07
     * @mofified By:
     */
    public static void createOssBucket() {
        // Endpoint以北京为例，其它Region请按实际情况填写。
        String endpoint = defaultEndpoint;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = keyId;
        String accessKeySecret = keySecret;
        String bucketName = "new-bucket";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 创建存储空间。
        ossClient.createBucket(bucketName);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * @description：上传字符串到OSS
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/9/18 下午3:09
     * @mofified By:
     */
    public static void uploadStringFile(){
        // Endpoint以北京为例，其它Region请按实际情况填写。
        String endpoint = defaultEndpoint;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = keyId;
        String accessKeySecret = keySecret;
        String bucketName = defaultBucket;
        String objectName = "test_string";
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件。
        String content = "这是一条测试存储";
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content.getBytes()));
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * @description：下载OSS文件内容
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/9/18 下午3:24
     * @mofified By:
     */
    public static void downloadFileContent(String fileKey) {
        // Endpoint以北京为例，其它Region请按实际情况填写。
        String endpoint = defaultEndpoint;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = keyId;
        String accessKeySecret = keySecret;
        String bucketName = defaultBucket;
        String objectName = fileKey;
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 调用ossClient.getObject返回一个OSSObject实例，该实例包含文件内容及文件元信息。
        OSSObject ossObject = ossClient.getObject(bucketName, objectName);
        // 调用ossObject.getObjectContent获取文件输入流，可读取此输入流获取其内容。
        InputStream content = ossObject.getObjectContent();

        try {
            if (content != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                while (true) {
                    String line = null;
                    line = reader.readLine();
                    if (line == null){
                        break;
                    }
                    System.out.println("\n" + line);
                }
                // 数据读取完成后，获取的流必须关闭，否则会造成连接泄漏，导致请求无连接可用，程序无法正常工作。
                content.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * @description：上传Byte
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/9/18 下午3:09
     * @mofified By:
     */
    public static void uploadByteFile(){
        // Endpoint以北京为例，其它Region请按实际情况填写。
        String endpoint = defaultEndpoint;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = keyId;
        String accessKeySecret = keySecret;
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);
        // 上传Byte数组。
        byte[] content = "Hello OSS".getBytes();
        ossClient.putObject(defaultBucket, "test_byte", new ByteArrayInputStream(content));
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * @description：上传文件流
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/9/18 下午3:09
     * @mofified By:
     */
    public static void uploadFileStream(){
        // Endpoint以北京为例，其它Region请按实际情况填写。
        String endpoint = defaultEndpoint;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId = keyId;
        String accessKeySecret = keySecret;
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("/WORK/WorkBook/thumb_IMG_0949_1024.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ossClient.putObject("cyoung-bucket-img", "test_img", inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
    }

    /**
     * @description：上传本地文件
     * @version 1.0
     * @author: Yang.Chang
     * @email: cy880708@163.com
     * @date: 2018/9/18 下午3:09
     * @mofified By:
     */
    public static void uploadFile(){
        // Endpoint以北京为例，其它Region请按实际情况填写。
        String endpoint = defaultEndpoint;
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = keyId;
        String accessKeySecret = keySecret;
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
        ossClient.putObject("cyoung-bucket-img", "test_local_img", new File("/WORK/WorkBook/thumb_IMG_0949_1024.jpg"));
        // 关闭OSSClient。
        ossClient.shutdown();
    }


    public static void main(String[] args) {
        uploadFile();
    }

}
