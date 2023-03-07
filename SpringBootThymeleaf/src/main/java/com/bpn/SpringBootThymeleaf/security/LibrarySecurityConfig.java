package com.bpn.SpringBootThymeleaf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class LibrarySecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

  @Autowired
  private DataSource dataSource;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.securityContext()
            .and()
            .authorizeRequests()
            .antMatchers("/login")
            .permitAll()
            .anyRequest().authenticated()
            .and().csrf().csrfTokenRepository(new HttpSessionCsrfTokenRepository())
            .and()
            .formLogin().loginPage("/login")
            .defaultSuccessUrl("/books")
            .failureUrl("/login.html?error=true")
            .permitAll()
            .and()
            .logout()
            .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/")
            .and()
            .servletApi()
            .and()
            .exceptionHandling()
            .and()
            .headers();
  }

  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addViewController("/login").setViewName("login");
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.jdbcAuthentication().dataSource(dataSource);
  }
}
