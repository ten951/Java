package com.wyt.headfirst.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.CacheRequest;

/**
 * @author Darcy
 *         Created by Administrator on 2017/1/20.
 */
public class SimpleCacheRequest extends CacheRequest {

    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    /**
     * Returns an OutputStream to which the response body can be
     * written.
     *
     * @return an OutputStream to which the response body can
     * be written
     * @throws IOException if an I/O error occurs while
     *                     writing the response body
     */
    @Override
    public OutputStream getBody() throws IOException {
        return out;
    }

    /**
     * Aborts the attempt to cache the response. If an IOException is
     * encountered while reading the response or writing to the cache,
     * the current cache store operation will be abandoned.
     */
    @Override
    public void abort() {
        out.reset();
    }

    public byte[] getData() {
        if (out.size() == 0) return null;
        else return out.toByteArray();
    }
}
