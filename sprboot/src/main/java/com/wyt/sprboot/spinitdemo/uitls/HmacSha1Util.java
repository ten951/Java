package com.wyt.sprboot.spinitdemo.uitls;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/3/22.
 */
public class HmacSha1Util {
    private static final String HMAC_SHA1 = "HmacSHA1";
    private static final String ENCODING = "UTF-8";

    /**
     * 生成签名数据
     *
     * @param data 待加密的数据
     * @param key  加密使用的key
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     */
    public static String getSignature(String data, String key) throws Exception {
        byte[] keyBytes = key.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        byte[] rawHmac = mac.doFinal(data.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : rawHmac) {
            sb.append(byteToHexString(b));
        }
        return sb.toString();
    }


    private static String byteToHexString(byte ib) {
        char[] Digit = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'
        };
        char[] ob = new char[2];
        ob[0] = Digit[(ib >>> 4) & 0X0f];
        ob[1] = Digit[ib & 0X0F];
        String s = new String(ob);
        return s;
    }



    public static void main(String[] args) {
        try {
      /*      String[] atp = {"Rafael Nadal", "Novak Djokovic",
                    "Stanislas Wawrinka",
                    "David Ferrer","Roger Federer",
                    "Andy Murray","Tomas Berdych",
                    "Juan Martin Del Potro"};
            List<String> players =  Arrays.asList(atp);

            players.forEach(System.out::println);*/

            String str="http://192.168.1.15:8111/user/list?channelNo=ios&version=9.3.0&secretKey=5c755d05ba50c399ca818eacfa49af54&timeStamp=1437629428";
            String url = URLEncoder.encode(str,"utf-8");
            System.out.println(url);
            System.out.println(HmacSha1Util.getSignature(url, "5c755d05ba50c399ca818eacfa49af54"));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
