package com.eazybytes.eazyschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    //SecurityFilterChain is a chain of security filters that process each HTTP request.
        //It decides which requests are allowed or blocked based on authentication and authorization rules.
        //It helps in securing web applications by enforcing user roles, login, and access control.

    //HttpSecurity is a class in Spring Security that allows you to configure security settings for HTTP requests.
        // It helps define authentication, authorization, login/logout settings, CSRF protection, session management, and more.
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{
        //Permit all Requests inside the  Web Application
//        http.authorizeHttpRequests(requests->requests.anyRequest().permitAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());

        // Deny All Requests inside the Web Application

//        http.authorizeHttpRequests(requests-> requests.anyRequest().denyAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());


        // Allow Specific Request inside the Web Application
        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveMsg").ignoringRequestMatchers("/public/**")
                .ignoringRequestMatchers("/api/**")
                        .ignoringRequestMatchers("/eazyschool/actuator/**"))

                .authorizeHttpRequests((requests) -> requests.requestMatchers("/dashboard").authenticated()
                        .requestMatchers("/displayMessages/**").hasRole("ADMIN")
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                        .requestMatchers("/eazyschool/actuator/**").hasRole("ADMIN")
                        .requestMatchers("/api/**").authenticated()
                        .requestMatchers("/displayProfile").authenticated()
                        .requestMatchers("/updateProfile").authenticated()
                        .requestMatchers("/student/**").hasRole("STUDENT")
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/holidays/**").permitAll()
                        .requestMatchers("/contact").permitAll()
                        .requestMatchers("/saveMsg").permitAll()
                        .requestMatchers("/courses").permitAll()
                        .requestMatchers("/about").permitAll()
                        .requestMatchers("/assets/**").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/logout").permitAll()

                        .requestMatchers("/public/**").permitAll())


                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());


        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
