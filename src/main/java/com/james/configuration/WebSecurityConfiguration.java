package com.james.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.sql.DataSource;

/**
 * Created by james on 6/1/17.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.
//                jdbcAuthentication()
//                .usersByUsernameQuery("select username, password, enabled from user where username = ?")
//                .authoritiesByUsernameQuery("select u.username, a.authority from user u inner join user_authority ar on (u.user_id = ar.user_id) inner join authority a on(ar.authority_id = a.authority_id) where u.username = ?")
//                .dataSource(dataSource)
//                .passwordEncoder(bCryptPasswordEncoder);

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);

//        auth.inMemoryAuthentication().withUser("Yifan").password("123456").roles("USER");

    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/login").permitAll()
//                .antMatchers("/oauth/authorize").authenticated()
//                .and()
//                .formLogin();
//    }

//    @Configuration
//    @Order(1)
//    public static class ApiWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .antMatcher("/api/**")
//                .authorizeRequests()
//                .anyRequest().authenticated();
////                .and()
////                .httpBasic();
//        }
//    }
//
//    @Configuration
//    @Order(2)
//    public static class FormWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            http
//                    .csrf().disable()
//                    .authorizeRequests()
//                    .anyRequest().authenticated()
//                    .and()
//                    .formLogin();
//        }
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/hello").authenticated()
                .and()
                .formLogin()
;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
