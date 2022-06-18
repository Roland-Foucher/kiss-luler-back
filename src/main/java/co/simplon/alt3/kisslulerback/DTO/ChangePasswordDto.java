package co.simplon.alt3.kisslulerback.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ChangePasswordDto {

  @NotBlank
  public String oldPassword;

  @NotBlank
  @Size(min = 6, message = "le mot de passe doit contenir au minimun 6 caractères")
  public String newPassword;
}
