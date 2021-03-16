package br.com.zup.academy.transacoes.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@Profile("dev")
public class SecurityDevelopment extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .formLogin().disable()
            .headers().frameOptions().disable()
            .and()
            .authorizeRequests()
            .antMatchers("/**").permitAll()
            .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
