//package com.java.sm.util;
//
//import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
//import com.aliyun.teaopenapi.models.Config;
//import lombok.extern.log4j.Log4j;
//
//import java.util.ResourceBundle;
//
///**
// * @author 722A-08-CXB
// * @version 1.0.0
// * @ClassName AsyncFactory.java
// * @DescriPtion 短信发送工厂
// * @CreateTime 2021年06月20日 13:48:00
// */
//@Log4j
//public class AsyncFactory {
//
//    private static String endpoint;
//    private static String accessKeyId;
//    private static String accessKeySecret;
//    private static String signName;
//    private static String templateCode;
//
//    static{
//        ResourceBundle bundle = ResourceBundle.getBundle("sms");
//        endpoint = bundle.getString("endpoint");
//        accessKeyId = bundle.getString("accessKeyId");
//        accessKeySecret = bundle.getString("accessKeySecret");
//        signName = bundle.getString("signName");
//        templateCode = bundle.getString("templateCode");
//    }
//    /**
//     * 使用AK&SK初始化账号Client
//     *
//     * @param accessKeyId
//     * @param accessKeySecret
//     * @return Client
//     * @throws Exception
//     */
//    private static com.aliyun.dysmsapi20170525.Client createClient(String accessKeyId, String accessKeySecret) throws Exception {
//        Config config = new Config()
//                // 您的AccessKey ID
//                .setAccessKeyId(accessKeyId)
//                // 您的AccessKey Secret
//                .setAccessKeySecret(accessKeySecret);
//        // 访问的域名
//        config.endpoint = endpoint;
//        return new com.aliyun.dysmsapi20170525.Client(config);
//    }
//
//    public static Runnable sendSms(String  phone,String code){
//        com.aliyun.dysmsapi20170525.Client client = null;
//        try {
//            client = SmsUtils.createClient(accessKeyId, accessKeySecret);
//            SendSmsRequest sendSmsRequest = new SendSmsRequest()
//                    .setPhoneNumbers(phone)
//                    .setSignName(signName)
//                    .setTemplateCode(templateCode)
//                    .setTemplateParam("{\"code\":"+code+"}");
//            // 复制代码运行请自行打印 API 的返回值
//
//        Runnable runnable = ()->{
//                client.sendSms(sendSmsRequest);
//        };
//            return  runnable;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
