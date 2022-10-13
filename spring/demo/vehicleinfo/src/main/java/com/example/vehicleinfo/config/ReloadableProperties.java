package com.example.vehicleinfo.config;

import lombok.Cleanup;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.scheduling.annotation.Scheduled;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor
public abstract class ReloadableProperties {
    protected final StandardEnvironment environment;
    private long lastModTime = 0L;
    private PropertySource<?> appConfigPropertySource = null;
    private Path configPath;
    private static final String PROPERTY_NAME = "enableCache.yaml";

    @PostConstruct
    private void stopIfProblemsCreatingContext() {
        MutablePropertySources propertySources = environment.getPropertySources();
        Optional<PropertySource<?>> appConfigPsOp =
                StreamSupport.stream(propertySources.spliterator(), false)
                        .filter(ps -> ps.getName().equals("class path resource [" + PROPERTY_NAME + "]"))
                        .findFirst();
        if (appConfigPsOp.isEmpty()) {
            // this will stop context initialization
            // (i.e. kill the spring boot program before it initializes)

            throw new RuntimeException("Unable to find property Source as file");
        }
        appConfigPropertySource = appConfigPsOp.get();

        String filename = appConfigPropertySource.getName();
        filename = filename
                .replace("class path resource [", "src/main/resources/")
                .replace("]", "");
        configPath = Paths.get(filename);

    }

    @Scheduled(fixedRate = 2000)
    private void reload() throws IOException {
        long currentModTs = Files.getLastModifiedTime(configPath).toMillis();
        if (currentModTs > lastModTime) {
            lastModTime = currentModTs;
            Properties properties = new Properties();
            @Cleanup InputStream inputStream = Files.newInputStream(configPath);
            properties.load(inputStream);
            environment.getPropertySources()
                    .replace(
                            appConfigPropertySource.getName(),
                            new PropertiesPropertySource(
                                    appConfigPropertySource.getName(),
                                    properties
                            )
                    );
            propertiesReloaded();
        }
    }

    protected abstract void propertiesReloaded();
}
