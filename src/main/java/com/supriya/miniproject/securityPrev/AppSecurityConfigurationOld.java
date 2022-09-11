//package com.supriya.miniproject.securityPrev;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.asm.TypeReference;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//
//@EnableWebSecurity
//public class AppSecurityConfiguration1 extends WebSecurityConfigurerAdapter {
//
//    // authentication feature
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.inMemoryAuthentication()
//                .withUser("client")
//                .password("$2y$12$Xvq/mqY5SxsLkmEkKZ09eOG3.CkHyFIsN0e0amFgY1vPgN1y0Oy7y")
//                .roles("CLIENT")
//                .and()
//                .withUser("admin")
//                .password("$2y$12$Xvq/mqY5SxsLkmEkKZ09eOG3.CkHyFIsN0e0amFgY1vPgN1y0Oy7y")
//                .roles("ADMIN");
//    }
//
//
//    // authorization feature
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/students/load").hasRole("ADMIN")
//                .antMatchers("/students/client").hasRole("CLIENT")
//                .antMatchers("/students/all").hasAnyRole("ADMIN", "CLIENT")
//                .antMatchers("/students/*").permitAll()
//                .and().formLogin();
//
////        http.authorizeRequests()
////                .antMatchers(getSecureUrls()).authenticated()
////                .and().formLogin();
//
//
//    }
//
//    private String[] getSecureUrls(){
//        InputStream is = TypeReference.class.getResourceAsStream("/static/secureUrls.json");
//        ObjectMapper objectMapper = new ObjectMapper();
//        List<String> urls = new ArrayList<>();
//
//        try {
//            urls = objectMapper.readValue(is, ArrayList.class);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//        String[] urlArray = urls.stream().toArray(String[]::new);
//        return urlArray;
//
//    }
//
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return new BCryptPasswordEncoder(12);
//    }
//
//}
