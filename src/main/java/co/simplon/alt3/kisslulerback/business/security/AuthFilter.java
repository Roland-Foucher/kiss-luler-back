package co.simplon.alt3.kisslulerback.business.security;

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

import co.simplon.alt3.kisslulerback.library.DTO.userDto.FullUserDTO;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.LoginDTO;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.UserDTOWithToken;
import co.simplon.alt3.kisslulerback.business.utils.LocalDateAdapter;
import co.simplon.alt3.kisslulerback.library.entites.User;
import co.simplon.alt3.kisslulerback.webApp.configuration.PropertiesLoader;

/**
 * class permettant d'authentifier un utilisateur et de créer un token si
 * username et password OK
 */
public class AuthFilter extends UsernamePasswordAuthenticationFilter {

  private AuthService authService;

  public AuthFilter(AuthenticationManager authenticationManager, AuthService authService) {
    super(authenticationManager);
    this.authService = authService;
    // definition de l'url de login
    setFilterProcessesUrl(SecurityConstants.SIGN_UP_URL);
  }

  /**
   * methode permettant de connecter l'utilisateur avec username et password
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
      authService.setDateConnection(loginDTO);

      // on authentifie le user si le password est OK
      return getAuthenticationManager()
          .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword(),
              new ArrayList<>())); // l'arraylist vide permet d'activer les roles

    } catch (IOException e) {
      throw new AuthenticationServiceException(e.getMessage());
    }

  }

  /**
   * methode renvoi un token si l'utilisateur st bien connecté
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
   * récupère la clé secure de création de token dans application.properties
   * 
   * @throws IOException
   */
  public static String createToken(String username) throws IOException {
    // récupération de la clé
    String secure = PropertiesLoader
        .loadProperties("/application.properties")
        .getProperty("secret.key");

    return JWT.create()
        .withSubject(username)
        .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
        .sign(Algorithm.HMAC512(secure.getBytes()));
  }
}