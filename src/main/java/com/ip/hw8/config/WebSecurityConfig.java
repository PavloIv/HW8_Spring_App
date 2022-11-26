package com.ip.hw8.config;

import com.ip.hw8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/user/createUser","/user/updateUser","/user/deleteUser")
                    .hasRole("ADMIN")
                    .antMatchers("/product/createProduct","/product/updateProduct","/product/deleteProduct")
                    .hasRole("ADMIN")
                    .antMatchers("/producer/createProducer","/producer/updateProducer","/producer/deleteProducer")
                    .hasRole("ADMIN")
                    .antMatchers("/","/registrationNewUser")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin()
                    .loginPage("/login")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder)
                .usersByUsernameQuery("SELECT email,password,active FROM users WHERE email = ?")
                .authoritiesByUsernameQuery("SELECT u.email,r.name FROM users u INNER JOIN roles r ON u.id = r.id WHERE u.email = ?");
    }
}