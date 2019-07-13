package com.xishanqu.redpacket.config;

import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;

/**
 * 配置授权服务器
 * @Author BaoNing 2019/7/12
 */
//@Configuration
//@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//    @Autowired
//    private AuthenticationManager authenticationManager;
//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//    @Autowired
//    private UserDetailsService userDetailsService;
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//
//        clients.inMemory()
//                .withClient("password")
//                .authorizedGrantTypes("password", "refresh_token")
//                .accessTokenValiditySeconds(1800)
//                .resourceIds("rid")
//                .scopes("all")
//                .secret("");
//        super.configure(clients);
//    }






}
