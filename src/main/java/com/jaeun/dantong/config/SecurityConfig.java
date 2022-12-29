package com.jaeun.dantong.config;

import com.jaeun.dantong.config.auth.JwtAuthenticationFilter;
import com.jaeun.dantong.config.auth.JwtProvider;
import com.jaeun.dantong.error.CustomAuthenticationEntryPoint;
import com.jaeun.dantong.error.WebAccessDeniedHandler;
import com.jaeun.dantong.repository.UserRepository;
import com.jaeun.dantong.service.UserDetailService;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailService userDetailService;

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                        .antMatchers("/login", "/signup","/","/users/**","/users/login","/users/confirm").permitAll()
                        .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                .accessDeniedHandler(new WebAccessDeniedHandler())
                .and()
                        .addFilterBefore(new JwtAuthenticationFilter(jwtProvider), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
                // -- Static resources
                "/css/**", "/images/**", "/js/**"
                // -- Swagger UI v2
                , "/v2/api-docs", "/swagger-resources/**"
                , "/swagger-ui.html", "/webjars/**", "/swagger/**"
                // -- Swagger UI v3 (Open API)
                , "/v3/api-docs/**", "/swagger-ui/**"
        );    }
}
