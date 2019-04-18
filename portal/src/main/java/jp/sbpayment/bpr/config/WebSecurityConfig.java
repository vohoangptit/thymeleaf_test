package jp.sbpayment.bpr.config;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  private final UserDetailsService userDetailsService;

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  public void configure(HttpSecurity httpSecurity) throws Exception {

    httpSecurity
            .csrf().disable()
            .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);

    httpSecurity
            .authorizeRequests()
              .antMatchers("/", "/top-page", "/notice/**")
                .access("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
              .antMatchers("/user/**").access("hasAnyRole('ROLE_ADMIN')")
            .and()
              .formLogin()
              .loginPage("/login")
              .successForwardUrl("/")
            .and()
              .logout()
              .invalidateHttpSession(true)
              .deleteCookies()
              .permitAll();
  }

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  /**
   * Setting Service to find User in the database and Setting PasswordEncoder.
   */
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }

}
