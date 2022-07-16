package co.simplon.alt3.kisslulerback.services;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.DTO.ChangePasswordDto;
import co.simplon.alt3.kisslulerback.DTO.UserRegisterDTO;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.exception.UserExistsException;
import co.simplon.alt3.kisslulerback.exception.WrongPasswordException;

public interface IUserService {

  void changePassowrd(final ChangePasswordDto body, final User user) throws WrongPasswordException;

  User register(final UserRegisterDTO userDto) throws UserExistsException;

  void saveUserPicture(final MultipartFile file, final User user) throws IOException, IncorrectMediaTypeFileException;
}
