package co.simplon.alt3.kisslulerback.business.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import co.simplon.alt3.kisslulerback.webApp.configuration.PropertiesLoader;

/**
 * class permettant de verifier le token envoyer avec les requêtes
 */
public class JwtFilter extends BasicAuthenticationFilter {

  private AuthService authService;

  public JwtFilter(AuthenticationManager authenticationManager, AuthService authService) {
    super(authenticationManager);
    this.authService = authService;
  }

  /**
   * authentification grace au token si present
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws IOException, ServletException {

    // recueration du header de la requete
    final String header = request.getHeader(SecurityConstants.HEADER_STRING);

    // si pas de token : on renvoi directement vers le fitre suivant (comme un
    // next() en nodeJS)
    if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
      filterChain.doFilter(request, response);
      return;
    }

    // on authentifie l'utilisateur en parsant le token

    SecurityContextHolder.getContext().setAuthentication(getAuthentication(request));

    // -> next opération
    filterChain.doFilter(request, response);

  }

  /**
   * lie le token JWT depuis le header, et utilise JWT pour valider le token
   * 
   * @param request la requète reçu par le serveur contenant un potentiel token
   * @return authentification ok de l'utilisateur si le token est OK
   * @throws IOException
   */
  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) throws IOException {

    // recupération du token
    final String token = request.getHeader(SecurityConstants.HEADER_STRING);
    // récupération de la clé
    String secure;
    secure = PropertiesLoader
        .loadProperties("/application.properties")
        .getProperty("secret.key");

    if (token != null) {

      // parse the token.
      final String userName = JWT.require(Algorithm.HMAC512(secure.getBytes()))
          .build()
          .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
          .getSubject();

      if (userName != null) {

        UserDetails user = authService.loadUserByUsername(userName);
        return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
      }

      return null;
    }

    return null;
  }

}
