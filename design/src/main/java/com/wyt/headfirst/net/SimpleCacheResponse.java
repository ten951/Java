package com.wyt.headfirst.net;

import java.io.*;
import java.net.CacheResponse;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.net.*;

/**
 * @author Darcy
 *         Created by Administrator on 2017/1/20.
 */
public class SimpleCacheResponse extends CacheResponse {
    private final Map<String, List<String>> headers;
    private final SimpleCacheRequest request;
    private final Date expires;
    private final CacheControl control;

    public SimpleCacheResponse(SimpleCacheRequest request, URLConnection uc, CacheControl control) {
        this.headers = Collections.unmodifiableMap(uc.getHeaderFields());
        this.request = request;
        this.expires = new Date(uc.getExpiration());
        this.control = control;
    }

    /**
     * Returns the response headers as a Map.
     *
     * @return An immutable Map from response header field names to
     * lists of field values. The status line has null as its
     * field name.
     * @throws IOException if an I/O error occurs
     *                     while getting the response headers
     */
    @Override
    public Map<String, List<String>> getHeaders() throws IOException {
        return headers;
    }

    /**
     * Returns the response body as an InputStream.
     *
     * @return an InputStream from which the response body can
     * be accessed
     * @throws IOException if an I/O error occurs while
     *                     getting the response body
     */
    @Override
    public InputStream getBody() throws IOException {
        return new ByteArrayInputStream(request.getData());
    }

    public CacheControl getControl() {
        return control;
    }

    public boolean isExpired() {
        Date now = new Date();
        if (control.getMaxAge().before(now)) return false;
        else if (expires != null && control.getMaxAge() != null) {
            return expires.before(now);
        } else {
            return false;
        }
    }
}
