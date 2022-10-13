package co.simplon.alt3.kisslulerback.webApp.configuration;

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

import co.simplon.alt3.kisslulerback.business.security.AuthFilter;
import co.simplon.alt3.kisslulerback.business.security.AuthService;
import co.simplon.alt3.kisslulerback.business.security.JwtFilter;
import co.simplon.alt3.kisslulerback.library.enums.Role;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private AuthService authService;
  private static final String FRONT_URL = "https://kiss-luler-front.herokuapp.com";

  /**
   * Configuration de Spring Security
   * configuration de spring security
   * 
   * @param authService                 permet d'injecter le service dans la
   *                                    class JWTfiler dans le but de récupérer le
   *                                    user et l'envoyer au fronrt
   * 
   * @param http                        configuration de la sécurité spring
   * @param authenticationConfiguration récupère les config d'authentification
   * @return la configuration de la sécurité http buildée
   * @throws Exception
   * 
   * @see HttpSecurity
   * @see AuthenticationConfiguration
   */
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http, AuthenticationConfiguration authenticationConfiguration)
      throws Exception {

    // Ajout de la configuration cors
    http.cors().configurationSource(request -> corsConfiguration());

    // configuration des routes et Droits
    http.authorizeRequests()
        .mvcMatchers("/api/user/account/*").authenticated()
        .mvcMatchers("/api/user/consideration/*").authenticated()
        .mvcMatchers("/api/admin/*").hasAuthority(Role.ADMIN.name())
        .anyRequest().permitAll()
        .and().csrf().disable()
        .logout().logoutSuccessHandler((req, res, auth) -> {
          res.setStatus(204);
        });

    // configuration de jwt
    http.addFilter(new AuthFilter(authenticationConfiguration.getAuthenticationManager(), authService))
        .addFilterBefore(new JwtFilter(authenticationConfiguration.getAuthenticationManager(), authService),
            UsernamePasswordAuthenticationFilter.class);

    // mode stateless disable les sessions
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    return http.build();
  }

  /**
   * encode le mot de passe avant de le stocker en bdd
   * 
   * @return le mot de passe encodé avec BCrypt
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder(10);
  }

  /**
   * 
   * Configuration des cors pour tous les controllers avec l'adresse du front
   * 
   * @see CorsConfiguration
   */
  private CorsConfiguration corsConfiguration() {
    CorsConfiguration configuration = new CorsConfiguration();

    configuration.addAllowedOrigin(FRONT_URL);
    configuration.addAllowedHeader("*");
    configuration.addAllowedMethod("*");

    return configuration;
  }

}