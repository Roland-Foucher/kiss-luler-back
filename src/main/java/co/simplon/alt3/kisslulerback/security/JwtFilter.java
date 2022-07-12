package co.simplon.alt3.kisslulerback.security;

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

public class JwtFilter extends BasicAuthenticationFilter {

  private AuthService authService;

  public JwtFilter(AuthenticationManager authenticationManager, AuthService authService) {
    super(authenticationManager);
    this.authService = authService;
  }

  /**
   * authentification grace au token si presenet
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

  // Reads the JWT from the Authorization header, and then uses JWT to validate
  // the token
  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {

    // recupération du token
    final String token = request.getHeader(SecurityConstants.HEADER_STRING);

    if (token != null) {
      // parse the token.
      final String userName = JWT.require(Algorithm.HMAC512(SecurityConstants.SECRET.getBytes()))
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
