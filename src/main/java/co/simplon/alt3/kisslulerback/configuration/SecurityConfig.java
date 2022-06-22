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

  /**
   * Configuration de Spring Security
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration)
      throws Exception {

    // Ajout de la configuration cors
    http.cors().configurationSource(request -> corsConfiguration());

    // configuration des routes et Droits
    http.authorizeRequests()
        .mvcMatchers("/api/user/account/*").authenticated()
        .mvcMatchers("/api/admin/*").hasAuthority(Role.ADMIN.name())
        .anyRequest().permitAll()
        .and().csrf().disable()
        .logout().logoutSuccessHandler((req, res, auth) -> {
          res.setStatus(204);
        });

    // configuration de jwt
    http.addFilter(new AuthFilter(authenticationConfiguration.getAuthenticationManager()))
        .addFilterBefore(new JwtFilter(authenticationConfiguration.getAuthenticationManager()),
            UsernamePasswordAuthenticationFilter.class);

    // mode stateless disable les sessions
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }

  /**
   * Configuration des cors pour tous les controllers
   */
  private CorsConfiguration corsConfiguration() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOrigin("http://localhost:3000");
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");

    return configuration;
  }

}