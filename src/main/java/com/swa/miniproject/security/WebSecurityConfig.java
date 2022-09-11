package com.swa.miniproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig{

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        // configure AuthenticationManager so that it knows from where to load
        // user for matching credentials  Use BCryptPasswordEncoder
        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeRequests()
//                .antMatchers("/students/load").hasAuthority("ADMIN") //hasRole
//                .antMatchers("/students/client").hasAuthority("CLIENT")
//                .antMatchers("/students/all").hasAnyAuthority("CLIENT","ADMIN")
//                .antMatchers("/students/*").permitAll()
//                .anyRequest().authenticated()
//                .and().formLogin();

        httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/authenticate").permitAll()
                .antMatchers("/students/load").hasAuthority("ADMIN") //hasRole
                .antMatchers("/students/client").hasAuthority("CLIENT")
                .antMatchers("/students/all").hasAnyAuthority("CLIENT","ADMIN")
                .antMatchers("/students/*").permitAll()
                .anyRequest().authenticated()
                .and()
//                .formLogin()
                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                ;

        // Add a filter to validate the tokens with every request
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }

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


}