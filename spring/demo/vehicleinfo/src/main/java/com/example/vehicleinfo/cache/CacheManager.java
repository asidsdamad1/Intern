package com.example.vehicleinfo.cache;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CacheManager {
    protected abstract String getValue(final String key);

    protected abstract void setValue(final String key, Object object);

    protected abstract void deleteKey(String key);
}
