package com.imooc.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(MyUserDetailService.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        logger.info("登陆的用户名为：" + username);

        // 根据查找到的用户信息判断用户是否被冻结
//        return new User(username,"123456",
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        return new User(username,passwordEncoder.encode("123456"),
                true,true,true,
                true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
