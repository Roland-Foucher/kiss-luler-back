package co.simplon.alt3.kisslulerback.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import co.simplon.alt3.kisslulerback.auth.ChangePasswordDto;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.exception.UserExistsException;
import co.simplon.alt3.kisslulerback.exception.WrongPasswordException;
import co.simplon.alt3.kisslulerback.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

  @Autowired
  UserService userService;

  @GetMapping("/account")
  public User getAccount(@AuthenticationPrincipal User user) {
    return user;
  }

  @PostMapping
  public User register(@RequestBody User user) {
    try {
      return userService.register(user);
    } catch (UserExistsException e) {
      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
    }
  }

  @PatchMapping("/password")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void changePassword(@RequestBody ChangePasswordDto body, @AuthenticationPrincipal User user) {
    try {
      userService.changePassowrd(body, user);
    } catch (WrongPasswordException e) {

      throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Old Password doesn't match");
    }
  }
}
