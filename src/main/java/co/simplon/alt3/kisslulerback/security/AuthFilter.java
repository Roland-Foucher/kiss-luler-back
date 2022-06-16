package co.simplon.alt3.kisslulerback.security;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.KeyLengthException;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;

import co.simplon.alt3.kisslulerback.entites.User;

public class AuthFilter extends UsernamePasswordAuthenticationFilter {

  public static String jwtSecret = "mqldsfqsvnqskdnmoezncqmlskdncmlsqdcqmsodnc";

  public AuthFilter(AuthenticationManager authenticationManager) {
    super(authenticationManager);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
      throws AuthenticationException {

    String username;
    String password;

    try {
      Map<String, String> requestMap = new ObjectMapper().readValue(request.getInputStream(), Map.class);
      username = requestMap.get("username");
      password = requestMap.get("password");
    } catch (IOException e) {
      throw new AuthenticationServiceException(e.getMessage(), e);
    }

    if (username == null || password == null) {
      throw new AuthenticationServiceException("username or password are null - can't auth");
    }

    return this.getAuthenticationManager()
        .authenticate(new UsernamePasswordAuthenticationToken(username, password));
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
      Authentication authResult) throws IOException, ServletException {

    try {
      User user = (User) authResult.getPrincipal();
      JWTClaimsSet claims = new JWTClaimsSet.Builder()
          .subject(user.getEmail())
          .issuer(request.getRequestURL().toString())
          .claim("role", user.getRole())
          .expirationTime(Date.from(Instant.now().plusSeconds(1 * 3600)))
          .issueTime(new Date())
          .build();

      Payload payload = new Payload(claims.toJSONObject());

      JWSObject jwsObject = new JWSObject(new JWSHeader(JWSAlgorithm.HS256),
          payload);

      jwsObject.sign(new MACSigner(jwtSecret));

      response.addHeader("access_token", jwsObject.serialize());
    } catch (KeyLengthException e) {
      e.printStackTrace();
    } catch (JOSEException e) {
      e.printStackTrace();
    }
  }

}
