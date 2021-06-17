package com.java.sm.util;


import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.teaopenapi.models.Config;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;

import java.util.ResourceBundle;

/**
 * 开发者：辉哥
 * 特点： 辉哥很帅
 * 开发时间：2021/6/14 16:06
 * 文件说明：
 */
@Log4j
public class SmsUtils {
    private static String endpoint;
    private static String accessKeyId;
    private static String accessKeySecret;
    private static String signName;
    private static String templateCode;

    static{
        ResourceBundle bundle = ResourceBundle.getBundle("sms");
        endpoint = bundle.getString("endpoint");
        accessKeyId = bundle.getString("accessKeyId");
        accessKeySecret = bundle.getString("accessKeySecret");
        signName = bundle.getString("signName");
        templateCode = bundle.getString("templateCode");
    }

//    static Logger logger = Logger.getLogger(SmsUtils.class);
    /**
     * 使用AK&SK初始化账号Client
     *
     * @param accessKeyId
     * @param accessKeySecret
     * @return Client
     * @throws Exception
     */
    private static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
        Config config = new Config()
                // 您的AccessKey ID
                .setAccessKeyId(accessKeyId)
                // 您的AccessKey Secret
                .setAccessKeySecret(accessKeySecret);
        // 访问的域名
        config.endpoint = endpoint;
        return new com.aliyun.dysmsapi20170525.Client(config);
    }

    public static  void  sendSms(String  phone,String code){
        try {
            com.aliyun.dysmsapi20170525.Client client = SmsUtils.createClient(accessKeyId, accessKeySecret);
            SendSmsRequest sendSmsRequest = new SendSmsRequest()
                    .setPhoneNumbers(phone)
                    .setSignName(signName)
                    .setTemplateCode(templateCode)
                    .setTemplateParam("{\"code\":"+code+"}");
            // 复制代码运行请自行打印 API 的返回值
            client.sendSms(sendSmsRequest);
        } catch (Exception e) {
            log.error(phone+"手机验证码发送错误"+e.getMessage());
            e.printStackTrace();
        }
    }

}
