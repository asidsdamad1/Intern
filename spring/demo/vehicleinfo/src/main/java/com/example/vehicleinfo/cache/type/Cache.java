package com.example.vehicleinfo.cache.type;

public interface Cache {
    public void setValue(final String key, Object object);
    public String getValue(final String key);
    public void deleteKey(String key);
}
