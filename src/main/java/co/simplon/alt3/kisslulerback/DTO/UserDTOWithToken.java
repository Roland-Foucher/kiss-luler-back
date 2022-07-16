package co.simplon.alt3.kisslulerback.DTO;

import co.simplon.alt3.kisslulerback.entites.User;

public class UserDTOWithToken {
  private User user;
  private String token;

  public UserDTOWithToken(User user, String token) {
    this.user = user;
    this.token = token;
  }

  public User getUser() {
    return user;
  }

  public String getToken() {
    return token;
  }

}
