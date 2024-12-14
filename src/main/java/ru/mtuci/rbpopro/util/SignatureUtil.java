package ru.mtuci.rbpopro.util;

import java.security.*;
import java.util.Base64;

public class SignatureUtil {
    public static String generateSignature(String data, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes());
        return Base64.getEncoder().encodeToString(signature.sign());
    }
}
