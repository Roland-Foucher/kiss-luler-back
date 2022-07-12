package co.simplon.alt3.kisslulerback.DTO;

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
