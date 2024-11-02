package icu.ytlsnb.alpacainterface.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

import java.util.HashMap;

public class SignUtils {
    public static String getSign(HashMap<String, String> headers, String secretKey) {
        String testStr = headers.toString() + "." + secretKey;
        Digester md5 = new Digester(DigestAlgorithm.MD5);
        return md5.digestHex(testStr);
    }
}
