package co.simplon.alt3.kisslulerback.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import co.simplon.alt3.kisslulerback.DTO.UserDto.ChangePasswordDto;
import co.simplon.alt3.kisslulerback.DTO.UserDto.FullUserDTO;
import co.simplon.alt3.kisslulerback.DTO.UserDto.UserRegisterDTO;
import co.simplon.alt3.kisslulerback.DTO.UserDto.UserUpdateDto;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.exception.UserExistsException;
import co.simplon.alt3.kisslulerback.exception.WrongPasswordException;
import co.simplon.alt3.kisslulerback.repo.UserRepo;

@Service
public class UserServiceimpl implements IUserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private PasswordEncoder encoder;

  @Autowired
  private IUploadFileService uploadFileService;

  @Override
  public FullUserDTO register(final UserRegisterDTO userDto) throws UserExistsException {
    if (userRepo.existsByEmail(userDto.getEmail())) {
      throw new UserExistsException();
    }

    // conversion du DTO en user
    User user = new User(userDto);

    hashPassword(user);
    userRepo.save(user);

    // Optionnel, mais avec ça, on peut connecter le User automatiquement lors de
    // son inscription
    SecurityContextHolder.getContext()
        .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));

    return new FullUserDTO(user);
  }

  private void hashPassword(final User user) {
    final String hashed = encoder.encode(user.getPassword());
    user.setPassword(hashed);
  }

  @Override
  public void changePassowrd(final ChangePasswordDto body, final User user) throws WrongPasswordException {
    if (!encoder.matches(body.getOldPassword(), user.getPassword())) {
      throw new WrongPasswordException();
    }
    user.setPassword(body.getNewPassword());

    hashPassword(user);
    userRepo.save(user);
  }

  /**
   * sauvegarde la photo de profil et supprime l'ancienne s'il y en avait une
   */
  @Override
  public void saveUserPicture(final MultipartFile file, final User user)
      throws IOException, IncorrectMediaTypeFileException {
    String path = uploadFileService.saveImgageFile(file);
    String oldPhoto = user.getPhoto();
    user.setPhoto(path);

    if (oldPhoto != null) {
      uploadFileService.deleteFile(oldPhoto);

    }

    userRepo.save(user);
  }

  /**
   * Update User avec le DTO update
   */
  @Override
  public FullUserDTO updateUser(UserUpdateDto userDto, User user) throws UserExistsException {
    // Update user
    user.setBirthdate(userDto.getBirthdate());
    user.setJob(userDto.getJob());
    user.setFirstName(userDto.getFirstName());
    user.setLastName(userDto.getLastName());
    user.setPseudo(userDto.getPseudo());

    // si l'email est différent et n'est pas déjà utilisé, on le change dans le user
    if (!userDto.getEmail().equals(user.getEmail())) {
      if (userRepo.existsByEmail(userDto.getEmail())) {
        throw new UserExistsException();
      }
      user.setEmail(userDto.getEmail());
    }

    userRepo.save(user);

    // reconnection du user au cas ou l'email est changé
    SecurityContextHolder.getContext()
        .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));

    // save and return new userDTO
    return new FullUserDTO(user);

  }

}
