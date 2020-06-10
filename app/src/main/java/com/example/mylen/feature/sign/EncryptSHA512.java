package com.example.mylen.feature.sign;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//default 같은 패키지 내에서만 사용 가능한 클래스
class EncryptSHA512 {
    //비밀번호 암호화 : SHA-512 방식 사용 -> 별도의 복호화 과정 존재 안함.
    public static String encryptSHA512(String target){
        try{
            MessageDigest sh = MessageDigest.getInstance("SHA-512");
            sh.update(target.getBytes());

            StringBuffer sb = new StringBuffer();

            for(byte b : sh.digest())
                sb.append(Integer.toHexString(0xff & b));

            return sb.toString();

        } catch (NoSuchAlgorithmException e){
            throw new RuntimeException(e);
        }
    }
}
