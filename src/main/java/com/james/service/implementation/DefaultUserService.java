package com.james.service.implementation;

import com.james.entity.User;
import com.james.repository.UserRepository;
import com.james.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by james on 6/1/17.
 */
@Service
public class DefaultUserService implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findOneByUsername(s);
    }
}
