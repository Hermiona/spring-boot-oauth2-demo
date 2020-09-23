package com.meerim_task.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests(a ->
                a.mvcMatchers("/").permitAll()
                        .anyRequest().authenticated()
        )
                .oauth2Login()
                .and()
                .csrf().disable()
        ;
    }

//    @Bean
//    public PrincipalExtractor principalExtractor(UserRepository userRepository) {
//        return map -> {
//            return new User();
//        };
//    }

}
