package co.simplon.alt3.kisslulerback.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.simplon.alt3.kisslulerback.DTO.LoginDTO;
import co.simplon.alt3.kisslulerback.entites.User;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {

  public AuthFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
    setFilterProcessesUrl(SecurityConstants.SIGN_UP_URL);
  }

  /**
   * methode vérifie l'authentification du user avec le token de la requete
   */
  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {

    try {

      // on requpère le userName et password de la requete grace au DTO loginDTO
      LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);

      if (loginDTO.getUsername() == null || loginDTO.getPassword() == null) {
        throw new AuthenticationServiceException("username or password are null - can't auth");
      }

      // on authentifie le user en verifiant le token
      return getAuthenticationManager()
          .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword(),
              new ArrayList<>())); // l'arraylist vide permet d'activer les roles

    } catch (IOException e) {
      throw new AuthenticationServiceException(e.getMessage());
    }

  }

  /**
   * methode renvoi un token en fonction du user
   */
  @Override
  protected void successfulAuthentication(HttpServletRequest req,
      HttpServletResponse res,
      FilterChain chain,
      Authentication auth) throws IOException {

    // creation du token à partir de l'utilisteur
    String token = JWT.create()
        .withSubject(((User) auth.getPrincipal()).getUsername())
        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));

    // creation du body
    String body = ((User) auth.getPrincipal()).getUsername() + " " + token;

    // implementation du body dans la reponse
    res.getWriter().write(body);
    res.getWriter().flush();

  }
}