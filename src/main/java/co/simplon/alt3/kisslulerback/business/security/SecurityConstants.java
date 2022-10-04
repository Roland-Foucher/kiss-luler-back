package co.simplon.alt3.kisslulerback.business.security;

/**
 * Class comprenand les constantes de configuration de sécurité
 */
public class SecurityConstants {

  /**
   * permet de ne pas instancier cette classe.
   */
  private SecurityConstants() {
  }

  public static final String SECRET = "SECRET_KEY";
  public static final long EXPIRATION_TIME = 7_500_000; // 2 heures
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String HEADER_STRING = "Authorization";
  public static final String SIGN_UP_URL = "/api/user/login";
}
