package com.implosion.user.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class RequestLoggingFilterConfig {

    @Bean
    public CommonsRequestLoggingFilter logFilter() {
    	CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter() {
            @Override
            public void afterRequest(HttpServletRequest request, String message) {
            }
        };
        filter.setIncludeQueryString(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(10000);
        filter.setIncludeHeaders(true);
        filter.setBeforeMessagePrefix("{ REST REQUEST: ");
        filter.setBeforeMessageSuffix(" }");
        return filter;
    }
    
}