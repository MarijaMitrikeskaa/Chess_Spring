//package com.fict.workinggroups.chess_puzzles.config;
//
//
//import lombok.AllArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//@AllArgsConstructor
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
//public class WebSecurityConfiguration {
//    private final PasswordEncoder passwordEncoder;
//    private final CustomUsernamePasswordAuthenticationProvider customAuthenticationProvider;
//
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeRequests(authorize -> {
//
//                            try {
//                                authorize
//
//
//                                        .antMatchers("/homepage", "/viewFens", "/register", "/login", "/h2-console/**", "/guestGame", "/saveGuest", "/api/**").permitAll()
//                                        .anyRequest()
//                                        .authenticated()
//                                        .and().formLogin()
//                                        .loginPage("/login").permitAll()
//                                        .failureUrl("/login?error=BadCredentials")
//                                        .defaultSuccessUrl("/homepage", true)
//                                        .and()
//                                        .logout()
//                                        .logoutUrl("/logout")
//                                        .clearAuthentication(true)
//                                        .invalidateHttpSession(true)
//                                        .deleteCookies("JSESSIONID")
//                                        .and()
//                                        .exceptionHandling().accessDeniedPage("/homepage");
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//
//                        }
//
//
//                )
//                .formLogin(Customizer.withDefaults());
//        http.headers().frameOptions().disable();
//        return http.build();
//
//    }
//
//
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("admin")
//                .roles("ADMIN")
//                .build();
//
//
//        return new InMemoryUserDetailsManager(admin);
//    }
//
//
//
//
//}
