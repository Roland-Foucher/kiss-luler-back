package co.simplon.alt3.kisslulerback.services;

import co.simplon.alt3.kisslulerback.DTO.ChangePasswordDto;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.exception.UserExistsException;
import co.simplon.alt3.kisslulerback.exception.WrongPasswordException;

public interface IUserService {

  void changePassowrd(final ChangePasswordDto body, final User user) throws WrongPasswordException;

  User register(final User user) throws UserExistsException;

}
