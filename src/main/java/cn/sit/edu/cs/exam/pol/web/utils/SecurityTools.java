package cn.sit.edu.cs.exam.pol.web.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * Created by Administrator on 2015/6/2.
 */
public class SecurityTools {
    private static Random random = new Random();

    public static String generateHash(String source){
        MessageDigest messageDigest = null;
        String hashedPass="";
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(source.getBytes(),0, source.length());
            hashedPass = new BigInteger(1,messageDigest.digest()).toString(16);
            if (hashedPass.length() < 32) {
                hashedPass = "0" + hashedPass;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPass;
    }

    /**
     *
     * */
    public static String generateNumberPassword(){
        return String.format("%04d", random.nextInt(10000));
    }

}
