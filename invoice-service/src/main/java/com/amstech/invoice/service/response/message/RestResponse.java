package com.amstech.invoice.service.response.message;

import java.util.*;

public class RestResponse implements Map<String, Object> {
    
    private final Map<String, Object> response;

    private RestResponse() {
        this.response = new HashMap<>();
        this.timestamp();
    }

    public static RestResponse build() {
        return new RestResponse();
    }
    

    private RestResponse timestamp() {
        this.response.put("timestamp", new Date().getTime());
        return this;
    }

    public RestResponse data(Object data) {
        this.response.put("data", data);
        return this;
    }

    public RestResponse statusName(String statusName) {
        this.response.put("statusName", statusName);
        return this;
    }

    private RestResponse statusCode(int statusCode) {
        this.response.put("statusCode", statusCode);
        return this;
    }

    public RestResponse success(int successCode) {
        return this.statusCode(successCode).statusName("success");
    }

    public RestResponse error(int errorCode) {
        return this.statusCode(errorCode);
    }

    public RestResponse page(long page) {
        this.response.put("page", page);
        return this;
    }

    public RestResponse size(long size) {
        this.response.put("size", size);
        return this;
    }

    public RestResponse totalRecord(long totalRecord) {
        this.response.put("totalRecord", totalRecord);
        return this;
    }

    public RestResponse message(String message) {
        this.response.put("message", message);
        return this;
    }

    @Override
    public int size() {
        return response.size();
    }

    @Override
    public boolean isEmpty() {
        return response.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return response.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return response.containsValue(value);
    }

    @Override
    public Object get(Object key) {
        return response.get(key);
    }

    @Override
    public Object put(String key, Object value) {
        return response.put(key, value);
    }

    @Override
    public Object remove(Object key) {
        return response.remove(key);
    }

    @Override
    public void putAll(Map<? extends String, ? extends Object> m) {
        response.putAll(m);
    }

    @Override
    public void clear() {
        response.clear();
    }

    @Override
    public Set<String> keySet() {
        return response.keySet();
    }

    @Override
    public Collection<Object> values() {
        return response.values();
    }

    @Override
    public Set<Entry<String, Object>> entrySet() {
        return response.entrySet();
    }
}
