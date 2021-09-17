package com.liang.iotmall.iotmall.thirdparty;

import com.aliyun.oss.OSSClient;
import com.liang.iotmall.iotmall.thirdparty.component.SmsComponent;
import com.liang.iotmall.iotmall.thirdparty.util.HttpUtils;
import org.apache.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@SpringBootTest
class IotmallThirdPartyApplicationTests {

    @Autowired
    SmsComponent smsComponent;

    @Test
    void sendcode(){
        smsComponent.sendSmsCode("17390052571","3334");
    }

    @Test
    void sendSms(){
            String host = "https://dfsns.market.alicloudapi.com";
            String path = "/data/send_sms";
            String method = "POST";
            String appcode = "ed7e1b36a7e74dc4b496624897a0b55c";
            Map<String, String> headers = new HashMap<String, String>();
            //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
            headers.put("Authorization", "APPCODE " + appcode);
            //根据API的要求，定义相对应的Content-Type
            headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
            Map<String, String> querys = new HashMap<String, String>();
            Map<String, String> bodys = new HashMap<String, String>();
            bodys.put("content", "code:1111");
            bodys.put("phone_number", "17390052571");
            bodys.put("template_id", "TPL_0000");


            try {
                /**
                 * 重要提示如下:
                 * HttpUtils请从
                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
                 * 下载
                 *
                 * 相应的依赖请参照
                 * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
                 */
                HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
                System.out.println(response.toString());
                //获取response的body
                //System.out.println(EntityUtils.toString(response.getEntity()));
            } catch (Exception e) {
                e.printStackTrace();
        }
    }

    @Autowired
    OSSClient ossClient;

    @Test
    public void testUpload() throws FileNotFoundException {

        InputStream inputStream = new FileInputStream("C:\\Users\\Administrator\\Pictures\\人 物\\鬼刀\\gz.jpg");
        ossClient.putObject("iotmall", "公主测试.jpg", inputStream);
        ossClient.shutdown();
        System.out.println("上传完成");
    }

//    @Test
//    public void testChar(){
//        String str="A";
//        char[] ch={'A'};
//        System.out.println(str.equals(ch));
//
//    }

}
