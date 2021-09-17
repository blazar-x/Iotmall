package com.liang.iotmall.iotmall.thirdparty.component;

import com.liang.iotmall.iotmall.thirdparty.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.PrimitiveIterator;

///**
// * @author: Mr.Liang
// * @date: 2021/5/28 11:42
// * @Version: 1.0
// */
//@ConfigurationProperties(prefix = "spring.cloud.alicloud.sms")
@Component
public class SmsComponent {
    @Value("${spring.cloud.alicloud.sms.host}")
    private String host;
    @Value("${spring.cloud.alicloud.sms.path}")
    private String path;
    @Value("${spring.cloud.alicloud.sms.template_id}")
    private String template_id;
    @Value("${spring.cloud.alicloud.sms.appcode}")
    private String appcode;
    public void sendSmsCode(String phone, String code) {
        code="code:"+code;
//        String host = "https://dfsns.market.alicloudapi.com";
//        String path = "/data/send_sms";
        String method = "POST";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("content", code);
        bodys.put("phone_number", phone);
        bodys.put("template_id", template_id);
        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
