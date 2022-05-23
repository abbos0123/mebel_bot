package uz.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(@Lazy UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .authorizeRequests()

                // these are permissions for user resource

                .antMatchers("/api/user/client/register").permitAll()
                .antMatchers("/api/user/admin/register").permitAll()
                .antMatchers("/api/user/update").permitAll()
                .antMatchers("/api/user/delete/{id}").permitAll()
                .antMatchers("/api/user/*").permitAll()
                .antMatchers("/api/user/all").permitAll()
                .antMatchers("/api/user/search").permitAll()


                // these are permissions for  files

                .antMatchers("/api/file/upload").permitAll()
                .antMatchers("/api/file/upload/image").permitAll()
                .antMatchers("/api/file/preview/*").permitAll()
                .antMatchers("/api/file/download/*").permitAll()
                .antMatchers("/api/file/delete/*").permitAll()

                //permissions for product rest apis

                .antMatchers("/api/product/add_new_product").permitAll()
                .antMatchers("/api/product/{id}").permitAll()
                .antMatchers("/api/product/all/{name}").permitAll()
                .antMatchers("/api/product/delete/{id}").permitAll()
                .antMatchers("/api/product/update").permitAll()
                .antMatchers("/api/product/checking/{id}").permitAll()
                .antMatchers("/api/product/checking/all").permitAll()
                .and()
                .httpBasic();

    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
