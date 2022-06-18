package co.simplon.alt3.kisslulerback.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;

import co.simplon.alt3.kisslulerback.enums.Role;
import co.simplon.alt3.kisslulerback.security.AuthFilter;
import co.simplon.alt3.kisslulerback.security.AuthService;
import co.simplon.alt3.kisslulerback.security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  AuthService authService;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration)
      throws Exception {

    http.cors().configurationSource(request -> corsConfiguration());

    http.authorizeRequests()
        .mvcMatchers("/api/user/account/*").authenticated()
        .mvcMatchers("/api/admin/*").hasAuthority(Role.ADMIN.name())
        .anyRequest().permitAll()
        .and().csrf().disable()
        .logout().logoutSuccessHandler((req, res, auth) -> {
          res.setStatus(204);
        })
        .and()
        .addFilter(new AuthFilter(authenticationConfiguration.getAuthenticationManager()))
        .addFilterBefore(new JwtFilter(authenticationConfiguration.getAuthenticationManager()),
            UsernamePasswordAuthenticationFilter.class)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // mode stateless disable les
                                                                                     // sessions

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }

  /**
   * Cette méthode ne changera à priori pas de projet en projet, ya juste le
   * allowedOrigin
   * qu'on pourra changer selon où est le front
   */
  private CorsConfiguration corsConfiguration() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOrigin("http://localhost:3000");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");

    return configuration;
  }

}