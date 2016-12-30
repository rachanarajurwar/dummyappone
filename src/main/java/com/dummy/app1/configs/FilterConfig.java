package com.dummy.app1.configs;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Collections;


/**
 * Created by SThapa on 10/28/2016.
 */
@Configuration
public class FilterConfig extends WebMvcConfigurerAdapter implements WebApplicationInitializer {
    @Bean
    public MDCFilter mdcFilter() {
        return new MDCFilter();
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        servletContext.addFilter("MDCFilter", mdcFilter());
    }

    @Bean
    public TimeInterceptor timeInterceptor() {
        return new TimeInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(timeInterceptor()).addPathPatterns("/**");
    }

}
