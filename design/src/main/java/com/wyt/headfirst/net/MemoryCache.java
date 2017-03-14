package com.wyt.headfirst.net;

import org.spark_project.jetty.util.annotation.ManagedOperation;

import java.io.IOException;
import java.net.*;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Darcy
 *         Created by Administrator on 2017/1/20.
 */
public class MemoryCache extends ResponseCache {
    private final Map<URI, SimpleCacheResponse> responses = new ConcurrentHashMap<>();
    private final int maxEntries;

    public MemoryCache() {
        this(100);
    }

    public MemoryCache(int maxEntries) {
        this.maxEntries = maxEntries;
    }

    /**
     * Retrieve the cached response based on the requesting uri,
     * request method and request headers. Typically this method is
     * called by the protocol handler before it sends out the request
     * to get the network resource. If a cached response is returned,
     * that resource is used instead.
     *
     * @param uri         a {@code URI} used to reference the requested
     *                    network resource
     * @param rqstMethod  a {@code String} representing the request
     *                    method
     * @param rqstHeaders - a Map from request header
     *                    field names to lists of field values representing
     *                    the current request headers
     * @return a {@code CacheResponse} instance if available
     * from cache, or null otherwise
     * @throws IOException              if an I/O error occurs
     * @throws IllegalArgumentException if any one of the arguments is null
     * @see URLConnection#setUseCaches(boolean)
     * @see URLConnection#getUseCaches()
     * @see URLConnection#setDefaultUseCaches(boolean)
     * @see URLConnection#getDefaultUseCaches()
     */
    @Override
    public CacheResponse get(URI uri, String rqstMethod, Map<String, List<String>> rqstHeaders) throws IOException {
        if ("GET".equals(rqstMethod)) {
            SimpleCacheResponse response = responses.get(uri);
            if (response != null && response.isExpired()) {
                responses.remove(response);
                response = null;
            }
            return response;
        } else {
            return null;
        }
    }

    /**
     * The protocol handler calls this method after a resource has
     * been retrieved, and the ResponseCache must decide whether or
     * not to store the resource in its cache. If the resource is to
     * be cached, then put() must return a CacheRequest object which
     * contains an OutputStream that the protocol handler will
     * use to write the resource into the cache. If the resource is
     * not to be cached, then put must return null.
     *
     * @param uri  a {@code URI} used to reference the requested
     *             network resource
     * @param conn - a URLConnection instance that is used to fetch
     *             the response to be cached
     * @return a {@code CacheRequest} for recording the
     * response to be cached. Null return indicates that
     * the caller does not intend to cache the response.
     * @throws IOException              if an I/O error occurs
     * @throws IllegalArgumentException if any one of the arguments is
     *                                  null
     */
    @Override
    public CacheRequest put(URI uri, URLConnection conn) throws IOException {
        if (responses.size() >= maxEntries) return null;
        CacheControl control = new CacheControl(conn.getHeaderField("Cache-Control"));
        return null;
    }
}
