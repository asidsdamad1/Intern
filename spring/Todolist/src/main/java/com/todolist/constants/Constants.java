package com.todolist.constants;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.Bean;

@UtilityClass
public class Constants {
    private static final ObjectMapper MAPPER;
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";


    static {
        MAPPER = new ObjectMapper();
        //
        MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Bean
    public ObjectMapper mapper() {
        return MAPPER;
    }

}
