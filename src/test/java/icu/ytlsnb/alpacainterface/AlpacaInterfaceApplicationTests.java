package icu.ytlsnb.alpacainterface;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import icu.ytlsnb.alpacaapiclientsdk.client.AlpacaAPIClient;
import icu.ytlsnb.alpacaapiclientsdk.client.AlpacaAPIClientConfig;
import icu.ytlsnb.alpacainterface.model.User;
import icu.ytlsnb.alpacainterface.utils.SignUtils;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

@SpringBootTest
@EnableConfigurationProperties(AlpacaAPIClientConfig.class)
class AlpacaInterfaceApplicationTests {
    @Autowired
    private AlpacaAPIClient alpacaAPIClient;

    @Test
    public void testSDK() {
        alpacaAPIClient.msg1();
    }

    private String accessKey = "ytls";

    private String secretKey = "ytls";

    @Test
    public void client() {
        // msg1
        HashMap<String, String> headers = new HashMap<>();
        headers.put("accessKey", accessKey);
        // 不能直接发送给后端
//        headers.put("secretKey", secretKey);
        headers.put("nonce", RandomUtil.randomNumbers(5));
        headers.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        headers.put("sign", SignUtils.getSign(headers, secretKey));
        String body = HttpRequest.get("http://localhost:6661/api/ytls/msg1")
                .addHeaders(headers)
                .execute()
                .body();
        System.out.println(body);
//        // msg1
//        String result1= HttpUtil.get("http://localhost:6661/api/ytls/msg1", CharsetUtil.CHARSET_UTF_8);
//        System.out.println(result1);

//        // msg2
//        HashMap<String, Object> paramMap = new HashMap<>();
//        paramMap.put("username", "蔡徐坤");
//        String result2= HttpUtil.post("http://localhost:6661/api/ytls/msg2", paramMap);
//        System.out.println(result2);
//
//        // msg3
//        String json = JSONUtil.toJsonStr(new User("蔡徐坤", "123456"));
//        String result3 = HttpRequest.post("http://localhost:6661/api/ytls/msg3")
//                .body(json)
//                .execute().body();
//        System.out.println(result3);
    }
}
