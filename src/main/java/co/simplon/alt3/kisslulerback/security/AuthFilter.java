package co.simplon.alt3.kisslulerback.security;

import java.io.IOException;
import java.time.LocalDate;
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
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import co.simplon.alt3.kisslulerback.DTO.LoginDTO;
import co.simplon.alt3.kisslulerback.DTO.UserDTOWithToken;
import co.simplon.alt3.kisslulerback.DTO.FullUserDTO;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.configuration.LocalDateAdapter;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {

  public AuthFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);

    // definition de l'url de login
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
      final LoginDTO loginDTO = new ObjectMapper().readValue(request.getInputStream(), LoginDTO.class);

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

    final User user = ((User) auth.getPrincipal());
    // creation du token à partir de l'utilisteur
    final String token = AuthFilter.createToken(user.getUsername());

    final UserDTOWithToken uTokenDTO = new UserDTOWithToken(new FullUserDTO(user), token);

    // configuration de Gson pour parser les localDate
    final Gson gson = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();

    // création du body Json
    final String body = gson.toJson(uTokenDTO);

    // implementation du body dans la reponse
    res.getWriter().write(body);
    res.getWriter().flush();
  }

  /**
   * création d'un token à partir du username
   */
  public static String createToken(String username) {
    return JWT.create()
        .withSubject(username)
        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()));
  }
}