package com.study.data.service;

import com.study.data.entity.Users;
import org.springframework.security.core.userdetails.User;
import com.study.data.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository repository;

    public UserDetailsServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final Users user = this.repository.findByUsername(username);
        if(user == null) {
            throw new UsernameNotFoundException("Unknown user "+ username);
        }
        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities("ADMIN")
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }
}
