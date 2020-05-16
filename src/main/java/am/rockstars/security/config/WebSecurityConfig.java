package am.rockstars.security.config;

import am.rockstars.security.filter.JWTAuthenticationFilter;
import am.rockstars.security.filter.JWTLoginFilter;
import am.rockstars.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void configure(final HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors().disable()
                .csrf().disable()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .httpBasic().disable()
                .antMatcher("/api/**")
                .oauth2Login().authorizationEndpoint().baseUri("/api/oauth2/authorization").and()
                .redirectionEndpoint().baseUri("/api/login/oauth2/code/*").and()
                .successHandler((request, response, authentication) -> response.sendRedirect("/api/users/current-user")).and()
                .exceptionHandling().defaultAuthenticationEntryPointFor(new Http403ForbiddenEntryPoint(), new AntPathRequestMatcher("/**"))
                .and()
                .addFilterBefore(new JWTLoginFilter("/api/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/users", "/api/users/activate", "/api/actuator/**", "/api/api/login/**", "/api/api/oauth2/", "/api/header").permitAll()
                .antMatchers("/api/users/current-user").authenticated()
                .antMatchers("/admin/products/*", "/admin/**").hasAuthority("ADMIN")
                .anyRequest().authenticated();

    }


    @Order(2)
    @Configuration
    public static class BasicAuthWebSecurityConfiguration extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.formLogin().disable()
                .csrf().disable()
                .httpBasic()
                .and()
                .antMatcher("/monitoring/**")
                .logout().logoutUrl("/monitoring/logout")
                .and()
                .authorizeRequests()
                .antMatchers("/monitoring/**").hasAuthority("ADMIN")
                .anyRequest()
                .authenticated();
        }

        @Override
        public void configure(final WebSecurity web) throws Exception {
            web.ignoring()
               .antMatchers("/swagger-ui.html", "/v2/api-docs",
                       "/configuration/ui", "/swagger-resources/**",
                       "/configuration/security", "/swagger-ui.html", "/webjars/**");
        }
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userService);
        provider.setPasswordEncoder(passwordEncoder);
        return provider;
    }
}
