package com.fict.workinggroups.chess_puzzles.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernamePasswordAuthenticationProvider authenticationProvider;

    public WebSecurityConfig(PasswordEncoder passwordEncoder,
                             CustomUsernamePasswordAuthenticationProvider authenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationProvider = authenticationProvider;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/homepage","/new_fen", "/update_fen", "/register").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/home");

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("marija.mitrikeska")
                .password(passwordEncoder.encode("mm"))
                .authorities("ROLE_USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder.encode("admin"))
                .authorities("ROLE_ADMIN");
        auth.authenticationProvider(authenticationProvider);
    }



}

