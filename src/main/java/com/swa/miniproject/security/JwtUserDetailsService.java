package com.swa.miniproject.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if ("admin".equals(username)) {
            grantedAuthorities.add(new SimpleGrantedAuthority("ADMIN"));
            User user = new User("admin",
                    "$2y$12$Xvq/mqY5SxsLkmEkKZ09eOG3.CkHyFIsN0e0amFgY1vPgN1y0Oy7y",
                    grantedAuthorities);
            return user;
        } else if ("client".equals(username)) {
            grantedAuthorities.add(new SimpleGrantedAuthority("CLIENT"));
            User user = new User("client","$2y$12$Xvq/mqY5SxsLkmEkKZ09eOG3.CkHyFIsN0e0amFgY1vPgN1y0Oy7y",grantedAuthorities);
            return user;
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
