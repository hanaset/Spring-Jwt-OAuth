package com.rufree.dobi.api.config.security

import com.rufree.dobi.common.entity.enums.Role
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

//@EnableWebSecurity
//class SecurityConfig: WebSecurityConfigurerAdapter() {
//
//    override fun configure(http: HttpSecurity?) {
//
//        if(http == null)
//            return
//
//        http.csrf().disable()
//            .authorizeRequests()
//            .antMatchers("/", "/health").permitAll()
//            .antMatchers("/api/v1/**").hasRole(Role.LEVEL1.name)
//            .anyRequest().authenticated()
//    }
//}