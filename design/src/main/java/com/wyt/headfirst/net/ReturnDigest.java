package com.wyt.headfirst.net;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/27.
 */
public class ReturnDigest extends Thread {
    private String fileName;
    private byte[] digest;

    public ReturnDigest(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getDigest() {
        return digest;
    }

    @Override
    public void run() {
        try (InputStream in = new FileInputStream(fileName)) {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1) ;
            din.close();
            digest = sha.digest();
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}
