package com.james.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;


/**
 * Created by james on 6/2/17.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
    @Autowired
    private TokenStore tokenStore;

    @Value("${oauth.resourceId}")
    private String resourceId;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // Just for laughs, apply OAuth protection to only 2 resources
                .requestMatcher(new OrRequestMatcher(
                        new AntPathRequestMatcher("/"),
                        new AntPathRequestMatcher("/admin/beans")
                ))
                .authorizeRequests()
                .anyRequest().access("#oauth2.hasScope('read')");
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources
                .resourceId(resourceId)
                .tokenStore(tokenStore);
    }
}
