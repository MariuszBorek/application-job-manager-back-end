package com.manager.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/users").hasRole("ADMIN")
                .antMatchers("/api/users/**").hasRole("USER")
                .antMatchers("api/tools/scuppers/user/**").hasRole("USER")
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .logout();
//                .and()
//                .headers().frameOptions().disable()
//                .and();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("0_mail@gmail.com").password("{noop}qw").roles("USER")
                .and()
                .withUser("1_mail@gmail.com").password("{noop}qw").roles("ADMIN");
    }
}
