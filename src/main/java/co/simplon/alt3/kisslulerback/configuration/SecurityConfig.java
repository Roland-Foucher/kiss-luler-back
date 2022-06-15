package co.simplon.alt3.kisslulerback.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import co.simplon.alt3.kisslulerback.enums.Role;

@Configuration
public class SecurityConfig {

  
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
  {

      http.cors().configurationSource(request -> corsConfiguration());

      http.authorizeRequests()
          .mvcMatchers("/api/user/account").authenticated()
          .mvcMatchers("/api/admin/*").hasAuthority(Role.ADMIN.toString())
          //exemple avec méthode spécifique
          // .mvcMatchers(HttpMethod.GET, "/api/user").authenticated()
          .anyRequest().permitAll()
          .and().csrf().disable()
          //Changer le comportement pas défaut de la route logout pour lui faire juste renvoyer un 204 (plutôt qu'une redirection)
          .logout().logoutSuccessHandler((req,res,auth) -> {
              res.setStatus(204);
          });

      return http.build();
  }


  @Bean
  public PasswordEncoder passwordEncoder() {
      return new BCryptPasswordEncoder(10);
  }

      /**
     * Cette méthode ne changera à priori pas de projet en projet, ya juste le allowedOrigin
     * qu'on pourra changer selon où est le front
     */
    private CorsConfiguration corsConfiguration() {
      CorsConfiguration configuration = new CorsConfiguration();
      
      configuration.addAllowedOrigin("http://localhost:4200");
      configuration.addAllowedHeader("*");
      configuration.addAllowedMethod("*");
      
      return configuration;
  }

}