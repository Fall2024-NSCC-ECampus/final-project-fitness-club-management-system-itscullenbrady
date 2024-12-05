/**
 * This class is a configuration class for Spring Security.
 * The purpose of this class is to return a bean of type PasswordEncoder.
 * This bean is used to encode passwords when they are saved to the database.
 * The PasswordEncoder interface is located in the
 * org.springframework.security.crypto.password package.
 * The BCryptPasswordEncoder class is an implementation of the
 * PasswordEncoder interface that is suitable for most applications.
 * It is located in the org.springframework.security.crypto.bcrypt package.
 *
 * The @Configuration annotation is needed to tell Spring that this
 * class is a source of bean definitions.
 *
 * The @Bean annotation is needed to tell Spring that the
 * passwordEncoder() method should be used to create a bean definition
 * for a PasswordEncoder.
 * */
package com.example.fitnessmanagementsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {
    /**
     * This method is annotated with @Bean, which means that Spring will
     * create a bean definition for a PasswordEncoder based on this method.
     * The return value of this method is an instance of the
     * BCryptPasswordEncoder class, which is a suitable implementation of
     * the PasswordEncoder interface for most applications.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
