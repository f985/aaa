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
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public WebSecurityConfig(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity httpSecurity) throws Exception {
            httpSecurity
                    .antMatcher("/api/**")
                    .cors().disable()
                    .csrf().disable()
                    .logout().disable()
                    .authorizeRequests()
                    .antMatchers("/api/users").permitAll()
                    .antMatchers("/api/users/activate").permitAll()
                    .antMatchers("/api/users/current-user").authenticated()
                    .antMatchers("/api/products/*").hasAuthority("ADMIN")
                    .anyRequest().authenticated()
                    .and()
                    .addFilterBefore(new JWTLoginFilter("/api/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
                    .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        }
    }


    @Order(2)
    @Configuration
    public static class BasicAuthWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.antMatcher("/monitoring/**")
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/monitoring/**").hasAuthority("ADMIN")
                    .anyRequest()
                    .authenticated()
                    .and()
                    .httpBasic();
        }
    }

    @Order(3)
    @Configuration
    public static class Swagger extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(final HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .mvcMatchers("/swagger-ui.html", "/v2/api-docs",
                            "/configuration/ui",
                            "/swagger-resources/**",
                            "/configuration/security",
                            "/swagger-ui.html",
                            "/webjars/**").permitAll();
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
