package com.james.configuration;

import com.james.enumeration.AuthorityName;
import com.james.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

/**
 * Created by james on 6/2/17.
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
    @Value("${oauth.resourceId}")
    private String resourceId;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123");
        return converter;
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .userDetailsService(userDetailsService)
                .accessTokenConverter(accessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("trusted-client").secret("password")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                .scopes("read", "write", "trust").resourceIds(resourceId)

                .and()

                .withClient("untrusted-client")
                .authorizedGrantTypes("client_credentials", "password")
                .authorities("ROLE_CLIENT").scopes("read").resourceIds(resourceId)
                .secret("secret");
    }

//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security
//                .tokenKeyAccess("isAnonymous() || hasAuthority('READ') || hasAuthority('WRITE')")
//                .checkTokenAccess("hasAuthority('READ') || hasAuthority('WRITE')");
//    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(new InMemoryTokenStore());
        return tokenServices;
    }

    @Bean
    protected JwtAccessTokenConverter jwtAccessTokenConverter() {
        return new JwtAccessTokenConverter();
    }
}
