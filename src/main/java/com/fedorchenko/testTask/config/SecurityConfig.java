package com.fedorchenko.testTask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable();

        http.authorizeRequests()
                .antMatchers("/h2-console/**")
                .permitAll()
                .antMatchers("/user_api/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN")
                .antMatchers("/**").hasAuthority("ROLE_ADMIN")
                .anyRequest()
                .authenticated()
                .and().httpBasic()
                .and().sessionManagement().disable();;

        http.headers()
                .frameOptions()
                .sameOrigin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder builder)
            throws Exception {
        builder.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(new BCryptPasswordEncoder())
                .usersByUsernameQuery("select name,password,enabled "
                        + "from users "
                        + "where name = ?")
                .authoritiesByUsernameQuery("select name,authority "
                        + "from authorities "
                        + "where name = ?");
    }

}
