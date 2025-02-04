package icu.ytlsnb.alpacainterface.controller;

import icu.ytlsnb.alpacainterface.model.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ytls")
public class APIController {
    @GetMapping("/msg1")
    public String getMsg1() {
        return "GET 你好";
    }

    @PostMapping("/msg2")
    public String getMsg2(@RequestBody User user) {
        return String.format("user: [username=%s, password=%s]", user.getUsername(), user.getPassword());
    }
}
