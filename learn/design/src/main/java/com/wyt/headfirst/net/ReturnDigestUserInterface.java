package com.wyt.headfirst.net;

import javax.xml.bind.DatatypeConverter;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/27.
 */
public class ReturnDigestUserInterface {

    public static void receivedigest(byte[] digest, String name) {
        StringBuilder result = new StringBuilder(name);
        result.append(": ");
        result.append(DatatypeConverter.printHexBinary(digest));
        System.out.println(result);
    }

    public static void main(String[] args) {
        for (String fileName : args) {
            CallbackDigest cb = new CallbackDigest(fileName);
            Thread t = new Thread(cb);
            t.start();

        }
    }
}
