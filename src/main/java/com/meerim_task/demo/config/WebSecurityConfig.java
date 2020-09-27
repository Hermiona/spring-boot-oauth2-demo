package com.meerim_task.demo.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final OidcUserService customOidcUserService;

    /* Конфигурация безопасности: на все методы открыт доступ без авторизации */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // permit all - ?
        http.authorizeRequests(a ->
                        a.anyRequest().permitAll()
//                a.anyRequest().authenticated()
        )
                .csrf().disable()
                .oauth2Login()
                .userInfoEndpoint()
                .oidcUserService(customOidcUserService)
        ;
    }
}
