package com.zwj.blog.config;

import com.zwj.blog.auth.JwtAuthenticationEntryPoint;
import com.zwj.blog.auth.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityAdapter extends WebSecurityConfigurerAdapter {

    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Autowired
    private UserDetailsService userDetailsService;


    /**
     * 配置authenticationBuilder,并使用自定义的UserDetailsService,设置加密方式.
     * @param authenticationManagerBuilder
     * @throws Exception
     */
    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // we don't need CSRF because our token is invulnerable
                .csrf().disable()

                // don't create session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                .authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                .antMatchers(HttpMethod.GET, "/", "/favicon.ico", "/**/*.html", "/**/*.js", "/**/*.css", "/**/*.png",
                        "/**/*.gif", "/**/*.jpg", "/**/*.svg", "/**/*.eot", "/**/*.woff2", "/**/*.woff", "/**/*.ttf",
                        "/css/**", "/font/**", "/libs/**", "/img/**", "/js/**")
                .permitAll()

                .antMatchers(HttpMethod.POST,"/api/login").permitAll()

                .antMatchers("/swagger*/**", "/v2/**").permitAll()

                .antMatchers("/webjars/**").permitAll()

                .anyRequest().authenticated()

                .and().exceptionHandling().authenticationEntryPoint(unauthorizedHandler);

        // Custom JWT based security filter
        httpSecurity.addFilterBefore(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // disable page caching
        httpSecurity.headers().cacheControl();
    }


}
