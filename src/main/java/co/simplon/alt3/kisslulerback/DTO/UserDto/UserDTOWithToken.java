package co.simplon.alt3.kisslulerback.DTO.UserDto;

/**
 * DTO comprenant le token de connection et le user complet
 * envoyé lors d ela connection de l'utilisateur
 */
public class UserDTOWithToken {
  private FullUserDTO user;
  private String token;

  public UserDTOWithToken(FullUserDTO user, String token) {
    this.user = user;
    this.token = token;
  }

  public FullUserDTO getFullUserDTO() {
    return user;
  }

  public String getToken() {
    return token;
  }

}
