package com.nagarro.training.NoteSync.config;
import com.nagarro.training.NoteSync.utilities.Constants;
import  org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * This class provides configuration for Cross-Origin Resource Sharing (CORS) in the application.
 * @author shreyarathour
 *
 */
@Configuration
public class CorsConfig {
    /**
     * Configures CORS for the application.
     * @return the WebMvcConfigurer with CORS configuration.
     */
    @Bean
     WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            /**
             * Adds CORS mappings to the registry.
             *
             * @param registry the CorsRegistry to add CORS mappings to.
             */
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods(Constants.GET, Constants.PUT, Constants.POST,
                                Constants.DELETE)
                        .allowedHeaders("*")
                        .allowedOriginPatterns("*")
                        .allowCredentials(true);

            }
        };
    }
}

