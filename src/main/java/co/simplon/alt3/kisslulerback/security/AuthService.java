package co.simplon.alt3.kisslulerback.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.simplon.alt3.kisslulerback.repo.UserRepo;

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
}
