package com.wyt.headfirst.net;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Darcy
 *         Created by Administrator on 2016/12/27.
 */
public class InstanceCallbackDigest implements Runnable {
    private String fileName;
    private InstanceCallbackDigestUserInterface callback;

    public InstanceCallbackDigest(String fileName, InstanceCallbackDigestUserInterface callback) {
        this.fileName = fileName;
        this.callback = callback;
    }

    @Override
    public void run() {
        try (InputStream in = new FileInputStream(fileName)) {
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            DigestInputStream din = new DigestInputStream(in, sha);
            while (din.read() != -1) ;
            din.close();
            byte[] digest = sha.digest();
            callback.receiveDigest(digest);
        } catch (NoSuchAlgorithmException | IOException e) {
            e.printStackTrace();
        }
    }
}
