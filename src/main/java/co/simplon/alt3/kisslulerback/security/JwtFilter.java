package co.simplon.alt3.kisslulerback.security;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.proc.BadJOSEException;
import com.nimbusds.jose.proc.JWSKeySelector;
import com.nimbusds.jose.proc.JWSVerificationKeySelector;
import com.nimbusds.jose.proc.SecurityContext;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import com.nimbusds.jwt.proc.ConfigurableJWTProcessor;
import com.nimbusds.jwt.proc.DefaultJWTProcessor;

public class JwtFilter extends OncePerRequestFilter {

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {

    String token = null;

    if (request.getServletPath().equals("/login") || request.getServletPath().equals("/refreshToken")) {
      
      filterChain.doFilter(request, response);

    } else {

      String authorizationHeader = request.getHeader("Authorization");
      
      if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
        try {
          token = authorizationHeader.substring("Bearer ".length());
          UsernamePasswordAuthenticationToken authenticationToken = parseToken(token);
          SecurityContextHolder.getContext().setAuthentication(authenticationToken);
          filterChain.doFilter(request, response);
        } catch (Exception e) {

          response.setStatus(403);
          Map<String, String> error = new HashMap<>();
          error.put("errorMessage", e.getMessage());
          response.setContentType("application/json");
          new ObjectMapper().writeValue(response.getOutputStream(), error);
        }
      } else {
        filterChain.doFilter(request, response);
      }
    }

  }

  public static UsernamePasswordAuthenticationToken parseToken(String token) throws JOSEException,
      BadJOSEException, ParseException {

    byte[] secretKey = AuthFilter.jwtSecret.getBytes();
    SignedJWT signedJWT = SignedJWT.parse(token);
    signedJWT.verify(new MACVerifier(secretKey));

    ConfigurableJWTProcessor<SecurityContext> jwtProcessor = new DefaultJWTProcessor<>();

    JWSKeySelector<SecurityContext> keySelector = new JWSVerificationKeySelector<>(JWSAlgorithm.HS256,
        new ImmutableSecret<>(secretKey));

    jwtProcessor.setJWSKeySelector(keySelector);
    jwtProcessor.process(signedJWT, null);
    
    JWTClaimsSet claims = signedJWT.getJWTClaimsSet();
    String username = claims.getSubject();

    var roles = (List<String>) claims.getClaim("roles");
    var authorities = roles == null ? null
        : roles.stream()
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());

    return new UsernamePasswordAuthenticationToken(username, null, authorities);
  }

}
