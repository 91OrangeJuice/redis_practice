package com.tkp.learn.admin.config.secruity;

import com.tkp.learn.admin.security.JwtTokenProvider;
import com.tkp.learn.admin.security.filter.AuthenticationEntryPointFilter;
import com.tkp.learn.admin.security.filter.JwtAuthenticationFilter;
import com.tkp.learn.admin.service.tkpes.TkpopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${config.cross.flag}")
    private boolean flag;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private TkpopService tkpopService;

    @Autowired
    private AuthenticationEntryPointFilter authenticationEntryPoint;//自定义用户认证过期处理组件

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*.html",
                "/swagger-resources/**",
                "/webjars/springfox-swagger-ui/**");// 不需要走过滤器
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)//认证不通过处理
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)//不使用session
                .and()
                .authorizeRequests()
                .antMatchers("/**/*.auth")
                .authenticated()
                .anyRequest()
                .permitAll();

        httpSecurity.headers().frameOptions().disable();
        httpSecurity.addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider,tkpopService),
                UsernamePasswordAuthenticationFilter.class).anonymous();
        if (flag) {
            httpSecurity.addFilterBefore(corsFilter(), JwtAuthenticationFilter.class);
        }

    }

    private CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        urlBasedCorsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(urlBasedCorsConfigurationSource);
    }

}
