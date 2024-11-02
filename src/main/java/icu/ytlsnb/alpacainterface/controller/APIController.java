package icu.ytlsnb.alpacainterface.controller;

import cn.hutool.core.util.RandomUtil;
import icu.ytlsnb.alpacainterface.model.User;
import icu.ytlsnb.alpacainterface.utils.SignUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/ytls")
public class APIController {
    @GetMapping("/msg1")
    public String getMsg1(HttpServletRequest request) {
//        String accessKey = request.getHeader("accessKey");
//        String secretKey = request.getHeader("secretKey");
//        if (!"ytls".equals(accessKey) || !"ytls".equals(secretKey)) {
//            throw new RuntimeException("权限认证错误...");
//        }
        String accessKey = request.getHeader("accessKey");
        String nonce = request.getHeader("nonce");
        String timestamp = request.getHeader("timestamp");
        String sign = request.getHeader("sign");
        // TODO sk将来应该从数据库里查询
        String secretKey = "ytls";
        // TODO 这里的所有参数都应该校验
        HashMap<String, String> headers = new HashMap<>();
        headers.put("accessKey", accessKey);
        // TODO 这里的随机数可以保存在Redis等地方
        headers.put("nonce", nonce);
        headers.put("timestamp", timestamp);
        String serverSign = SignUtils.getSign(headers, secretKey);
        if (serverSign.equals(sign)) { // 签名一致，放行
            return "GET 你好";
        } else {
            throw new RuntimeException("无认证权限...");
        }
    }

    @PostMapping("/msg2")
    public String getMsg2(@RequestParam String username) {
        return "POST 你好；username: " + username;
    }

    @PostMapping("/msg3")
    public String getMsg3(@RequestBody User user) {
        return "POST 你好，我有请求体；username: " + user.getUsername();
    }
}
