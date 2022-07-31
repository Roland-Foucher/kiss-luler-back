package co.simplon.alt3.kisslulerback.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.simplon.alt3.kisslulerback.DTO.UserDto.FullUserDTO;
import co.simplon.alt3.kisslulerback.DTO.UserDto.UserRegisterDTO;
import co.simplon.alt3.kisslulerback.DTO.UserDto.UserUpdateDto;
import co.simplon.alt3.kisslulerback.dummy.DummyUser;
import co.simplon.alt3.kisslulerback.dummy.DummyUserDto;
import co.simplon.alt3.kisslulerback.dummy.DummyUserUpdateDTO;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.enums.Role;
import co.simplon.alt3.kisslulerback.exception.UserExistsException;
import co.simplon.alt3.kisslulerback.repo.UserRepo;

@ExtendWith(MockitoExtension.class)
public class UserServiceimplTest {

  @Mock
  UserRepo userRepo;

  @Mock
  PasswordEncoder encoder;

  @InjectMocks
  UserServiceimpl userService;

  @Test
  void testRegisterOk() throws UserExistsException {
    UserRegisterDTO userRegisterDTO = new DummyUserDto();
    User user = new User(userRegisterDTO);

    // mock userRepo sans connaitre le parametre exact à donner
    when(userRepo.save(Mockito.isA(User.class))).thenReturn(user);
    when(encoder.encode("jeanjean")).thenReturn("jeanjean-encoded");

    FullUserDTO userSaved = userService.register(userRegisterDTO);
    assertNotNull(userSaved);
    assertEquals("Jean", user.getFirstName());
    assertEquals(Role.USER, user.getRole());
    assertNotEquals("jeanjean-encoded", user.getPassword());

    User userConnected = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

    assertEquals(user.getEmail(), userConnected.getEmail());
  }

  @Test
  void testRegisterUserExist() throws UserExistsException {
    UserRegisterDTO userRegisterDTO = new DummyUserDto();
    when(userRepo.existsByEmail("j.dujardin.com")).thenReturn(true);
    assertThrows(UserExistsException.class, () -> userService.register(userRegisterDTO));
  }

  @Test
  void updateUserNewEmail() throws UserExistsException {
    UserUpdateDto userUpdateDto = new DummyUserUpdateDTO();
    userUpdateDto.setEmail("j.dujardin.com");
    User user = new DummyUser();

    when(userRepo.save(Mockito.isA(User.class))).thenReturn(user);

    FullUserDTO userSaved = userService.updateUser(userUpdateDto, user);

    assertNotNull(userSaved);
    assertEquals("Georges", user.getFirstName());
    assertEquals("Abitbol", user.getLastName());
    assertEquals("j.dujardin.com", user.getEmail());
    assertNotEquals("jeanjean-encoded", user.getPassword());

    User userConnected = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    assertEquals(user.getEmail(), userConnected.getEmail());

  }

  @Test
  void updateUser() throws UserExistsException {
    UserUpdateDto userUpdateDto = new DummyUserUpdateDTO();
    User user = new DummyUser();

    when(userRepo.save(Mockito.isA(User.class))).thenReturn(user);

    FullUserDTO userSaved = userService.updateUser(userUpdateDto, user);

    assertNotNull(userSaved);
    assertEquals("Georges", user.getFirstName());
    assertEquals("Abitbol", user.getLastName());
    assertEquals("email", user.getEmail());
    assertNotEquals("jeanjean-encoded", user.getPassword());

    User userConnected = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    assertEquals(user.getEmail(), userConnected.getEmail());
  }

  @Test
  void updateUserAlreadyExist() throws UserExistsException {
    UserUpdateDto userUpdateDto = new DummyUserUpdateDTO();
    userUpdateDto.setEmail("j.dujardin.com");
    User user = new DummyUser();

    when(userRepo.existsByEmail(userUpdateDto.getEmail())).thenReturn(true);
    assertThrows(UserExistsException.class, () -> userService.updateUser(userUpdateDto, user));
  }
}
