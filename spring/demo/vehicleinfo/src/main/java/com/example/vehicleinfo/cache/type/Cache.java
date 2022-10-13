package com.example.vehicleinfo.cache.type;

public interface Cache {
    void setValue(final String key, Object object);

    String getValue(final String key);

    void deleteKey(String key);
}
