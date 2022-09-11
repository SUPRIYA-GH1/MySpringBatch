//package com.supriya.miniproject.securityPrev;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableWebSecurity
//public class AppSecurityConfiguration {
//
//    // authorization feature
//    @Bean
//    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeRequests()
//                .antMatchers("/students/load").hasAuthority("ADMIN") //hasRole
//                .antMatchers("/students/client").hasAuthority("CLIENT")
//                .antMatchers("/students/all").hasAnyAuthority("CLIENT","ADMIN")
//                .antMatchers("/students/*").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and().formLogin()
//        ;
//
//        return httpSecurity.build();
//
//    }
//
//    // authentication feature
//    @Bean
//    protected UserDetailsService configureAutentication(){
//        List<UserDetails> userDetailsList = new ArrayList<>();
//
//        List<GrantedAuthority> clientGAs = new ArrayList<>();
//        clientGAs.add(new SimpleGrantedAuthority("CLIENT"));
//        User user1 = new User("client","$2y$12$Xvq/mqY5SxsLkmEkKZ09eOG3.CkHyFIsN0e0amFgY1vPgN1y0Oy7y",clientGAs);
//        userDetailsList.add(user1);
//
//        List<GrantedAuthority> adminGAs = new ArrayList<>();
//        adminGAs.add(new SimpleGrantedAuthority("ADMIN"));
//        User user2 = new User("admin",
//                "$2y$12$Xvq/mqY5SxsLkmEkKZ09eOG3.CkHyFIsN0e0amFgY1vPgN1y0Oy7y",
//                adminGAs);
//        userDetailsList.add(user2);
//
//        return new InMemoryUserDetailsManager(userDetailsList);
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return new BCryptPasswordEncoder(12);
//    }
//
//
//
//
//
//}
