package com.github.java4wro.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.authorizeRequests()
                .antMatchers("/api/users/getAll").hasRole("ADMIN")
                .antMatchers("/api/users/**" , "/registration.html" , "/login.html", "/logoutSuccess.html").permitAll()
                .anyRequest().authenticated();



        http.formLogin()
                .loginPage("/api/users/login")
                .usernameParameter("email")
                .passwordParameter("password")
                .defaultSuccessUrl("/hello.html")
                .failureHandler((request, response, exception) -> response.sendError(HttpStatus.BAD_REQUEST.value(),
                        "Email or password invalid"));

        http.logout()
                .logoutUrl("/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/logoutSuccess.html")
                .permitAll();

    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}