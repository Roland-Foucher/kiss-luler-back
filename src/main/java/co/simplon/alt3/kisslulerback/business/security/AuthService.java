package co.simplon.alt3.kisslulerback.business.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.simplon.alt3.kisslulerback.library.DTO.userDto.LoginDTO;
import co.simplon.alt3.kisslulerback.library.entites.User;
import co.simplon.alt3.kisslulerback.library.repositories.UserRepo;

/**
 * Service permettant de récupérer l'utilisateur via son email
 */
@Service
public class AuthService implements UserDetailsService {

  @Autowired
  private UserRepo userRepo;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    return userRepo
        .findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("user not found"));
  }

  public void setDateConnection(LoginDTO loginDto) throws UsernameNotFoundException {
    User user = userRepo.findByEmail(loginDto.getUsername())
        .orElseThrow(() -> new UsernameNotFoundException("user not found"));

    userRepo.setDateConnectionOnConnect(user.getUserId());
  }
}
