package com.rufree.dobi.api.config.security

import com.rufree.dobi.api.config.security.jwt.JwtAuthenticationFilter
import com.rufree.dobi.api.config.security.jwt.JwtTokenProvider
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig(
        private val jwtTokenProvider: JwtTokenProvider
) : WebSecurityConfigurerAdapter() {

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity?) {

        http!!
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/**",

                        // swagger
                        "/v2/api-docs", "/configuration/ui",
                        "/swagger-resources", "/configuration/security",
                        "/swagger-ui.html", "/webjars/**", "/swagger/**"
                )
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()

        http
                .addFilterBefore(JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter::class.java)

    }
}