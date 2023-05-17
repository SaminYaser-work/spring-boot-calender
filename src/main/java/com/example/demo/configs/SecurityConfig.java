//package com.example.demo.configs;//package com.example.demo.configs;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.net.Authenticator;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//    @Autowired
//    UserDetailsService userDetailsService;
//
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.userDetailsService(userDetailsService);
//
//        auth.inMemoryAuthentication()
//                .withUser("a").password("{noop}1").roles("USER");
//    }
//    protected void configure(HttpSecurity http) throws Exception {
//
////        http.authorizeRequests()
////                .antMatchers("/admin").hasRole("ADMIN")
////                .antMatchers("/user").hasAnyRole("USER","ADMIN")
////                .antMatchers("/").permitAll()
////                .and().formLogin();
//
////        http.authorizeRequests()
////                .anyRequest()
////                .permitAll()
////                .and().formLogin().disable()
////                .csrf().disable();
//        http
//            .authorizeRequests()
//            .antMatchers("/**").permitAll() // Allow access to all URLs
//            .and()
//            .csrf().disable() // Disable CSRF protection
//            .formLogin().disable(); // Disable the default form-based login
//
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
//
