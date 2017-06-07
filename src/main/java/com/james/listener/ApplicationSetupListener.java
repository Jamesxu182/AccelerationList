package com.james.listener;

import com.james.entity.Authority;
import com.james.entity.User;
import com.james.enumeration.AuthorityName;
import com.james.repository.AuthorityRepository;
import com.james.repository.UserRepository;
import com.james.service.UserService;
import com.james.service.implementation.DefaultUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by james on 6/3/17.
 */
@Component
public class ApplicationSetupListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    AuthorityRepository authorityRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    DefaultUserService userService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        Authority userAuthority = new Authority(AuthorityName.READ);
        Authority adminAuthority = new Authority(AuthorityName.WRITE);

        authorityRepository.save(userAuthority);
        authorityRepository.save(adminAuthority);

        Set<Authority> authorities = new HashSet<>();
        authorities.add(userAuthority);

        userRepository.save(new User("Yifan", bCryptPasswordEncoder.encode("123456"), true, authorities));

        User user = userService.loadUserByUsername("Yifan");

        for(Authority authority : user.getAuthorities())
        {
            System.out.println(authority.getAuthority());
        }
    }
}
