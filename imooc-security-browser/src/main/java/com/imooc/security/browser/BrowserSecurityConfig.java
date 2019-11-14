package com.imooc.security.browser;

import com.imooc.security.core.properties.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler imoocAuthenticationFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 表单认证
//        http.formLogin()
//                .loginPage("/imooc-login.html")
//                .loginProcessingUrl("/authentication/form")
//                .and()
//                .authorizeRequests()
//                .antMatchers("/imooc-login.html").permitAll() // 过滤掉这个页面不进行身份认证
//                .anyRequest()
//                .authenticated()
//                .and()
//                .csrf().disable();

        // 自定义处理方式
          http.formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(imoocAuthenticationSuccessHandler)
                .failureHandler(imoocAuthenticationFailureHandler)
                .and()
                .authorizeRequests()
                .antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll() // 过滤掉这个页面不进行身份认证
                .anyRequest()
                .authenticated()
                .and()
                .csrf().disable();

        // httpBasic 认证
//        http.httpBasic()
//                .and()
//                .authorizeRequests()
//                .anyRequest()
//                .authenticated();

    }
}
