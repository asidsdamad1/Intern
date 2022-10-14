package com.example.vehicleinfo.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:/dynamicProperties.yaml")
public class AppProperties extends ReloadableProperties {
    public AppProperties(StandardEnvironment environment) {
        super(environment);
    }

    public String enableCacheProperty() {
        return environment.getProperty("cache.enable");
    }

    @Override
    protected void propertiesReloaded() {
        // do something after a change in property values was done
    }
}
