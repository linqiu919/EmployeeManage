package com.java.sm.util;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import java.io.InputStream;
import java.util.ResourceBundle;


/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/15 11:55
 * 文件说明：
 */
public class UploadUtils {
    private static String aLiYunEndPoint;
    private static String aLiYunAccessKey;
    private static String aLiYunAccessKeySecret;
    private static String aLiYunBucket;
    private static String aLiYunUrl;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("upload");
        aLiYunEndPoint = bundle.getString("aliyun_endpoint");
        aLiYunAccessKey = bundle.getString("aliyun_accessKeyId");
        aLiYunAccessKeySecret = bundle.getString("aliyun_accessKeySecret");
        aLiYunBucket = bundle.getString("aliyun_bucket");
        aLiYunUrl = bundle.getString("aliyun_url");
    }

    public static String upload(String fileName, InputStream in) {
        String endpoint = aLiYunEndPoint;
        String accessKeyId = aLiYunAccessKey;
        String accessKeySecret = aLiYunAccessKeySecret;
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        //第一参数： 表示bucket名称   第二个参数：文件名称 携带后缀   第三个参数：文件输入流
        ossClient.putObject(aLiYunBucket, fileName, in);
        ossClient.shutdown();
        String url = aLiYunUrl + fileName + "!niyade";
        return url;
    }


}
