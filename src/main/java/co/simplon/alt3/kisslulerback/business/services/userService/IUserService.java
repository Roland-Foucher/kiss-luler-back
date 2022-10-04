package co.simplon.alt3.kisslulerback.business.services.userService;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.library.DTO.userDto.ChangePasswordDto;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.FullUserDTO;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.UserDTOWithToken;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.UserRegisterDTO;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.UserUpdateDto;
import co.simplon.alt3.kisslulerback.library.entites.User;
import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.library.exception.UserExistsException;
import co.simplon.alt3.kisslulerback.library.exception.WrongPasswordException;

public interface IUserService {

  void changePassowrd(final ChangePasswordDto body, final User user) throws WrongPasswordException;

  FullUserDTO register(final UserRegisterDTO userDto) throws UserExistsException;

  FullUserDTO saveUserPicture(final MultipartFile file, final User user)
      throws IOException, IncorrectMediaTypeFileException;

  UserDTOWithToken updateUser(final UserUpdateDto userDto, final User user) throws UserExistsException, IOException;

}
