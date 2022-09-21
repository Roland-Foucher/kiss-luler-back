package co.simplon.alt3.kisslulerback.library.DTO.userDto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * simple DTO provenant d'un POST pour changer le password de l'utilisateur
 */
public class ChangePasswordDto {

  @NotBlank
  private String oldPassword;

  @NotBlank
  @Size(min = 6, message = "le mot de passe doit contenir au minimun 6 caractères")
  private String newPassword;

  public String getOldPassword() {
    return oldPassword;
  }

  public void setOldPassword(String oldPassword) {
    this.oldPassword = oldPassword;
  }

  public String getNewPassword() {
    return newPassword;
  }

  public void setNewPassword(String newPassword) {
    this.newPassword = newPassword;
  }
}
