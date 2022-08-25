package com.example.physicalhealthapplication.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final CustomUsernameAndPasswordAuthenticationProvider customUsernameAndPasswordAuthenticationProvider;

    public WebSecurityConfig (PasswordEncoder passwordEncoder, CustomUsernameAndPasswordAuthenticationProvider customUsernameAndPasswordAuthenticationProvider) {
        this.passwordEncoder = passwordEncoder;
        this.customUsernameAndPasswordAuthenticationProvider = customUsernameAndPasswordAuthenticationProvider;
    }

    @Override
    protected void configure (HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/","plan","/home", "/register", "/login", "/static/**", "/templates/**","/resources/**")
                .permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .failureUrl("/login?error=BadCredentials")
                .defaultSuccessUrl("/home", true)
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login")
                .and()
                .exceptionHandling().accessDeniedPage("/access_denied");
    }

    @Override
    protected void configure (AuthenticationManagerBuilder auth) throws Exception {

        auth.authenticationProvider(customUsernameAndPasswordAuthenticationProvider);
    }
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }
}
