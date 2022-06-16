package co.simplon.alt3.kisslulerback.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import co.simplon.alt3.kisslulerback.auth.ChangePasswordDto;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.enums.Role;
import co.simplon.alt3.kisslulerback.exception.UserExistsException;
import co.simplon.alt3.kisslulerback.exception.WrongPasswordException;
import co.simplon.alt3.kisslulerback.repo.UserRepo;

@Service
public class UserService {

  @Autowired
  private UserRepo userRepo;

  @Autowired
  private PasswordEncoder encoder;

  public User register(User user) throws UserExistsException {
    if (userRepo.findByEmail(user.getEmail()).isPresent()) {
      throw new UserExistsException();
    }
    user.setRole(Role.USER);
    hashPassword(user);
    userRepo.save(user);

    // Optionnel, mais avec ça, on peut connecter le User automatiquement lors de
    // son inscription
    SecurityContextHolder.getContext()
        .setAuthentication(new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities()));
    
    return user;
  }

  private void hashPassword(User user) {
    String hashed = encoder.encode(user.getPassword());
    user.setPassword(hashed);
  }

  public void changePassowrd(ChangePasswordDto body, User user) throws WrongPasswordException {
    if (!encoder.matches(body.oldPassword, user.getPassword())) {
      throw new WrongPasswordException();
    }
    user.setPassword(body.newPassword);
    hashPassword(user);
    userRepo.save(user);
  }
}
