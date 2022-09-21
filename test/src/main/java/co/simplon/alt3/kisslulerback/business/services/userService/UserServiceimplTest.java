package co.simplon.alt3.kisslulerback.business.services.userService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import co.simplon.alt3.kisslulerback.library.DTO.userDto.FullUserDTO;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.UserDTOWithToken;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.UserRegisterDTO;
import co.simplon.alt3.kisslulerback.library.DTO.userDto.UserUpdateDto;
import co.simplon.alt3.kisslulerback.library.dummy.DummyUser;
import co.simplon.alt3.kisslulerback.library.dummy.DummyUserDto;
import co.simplon.alt3.kisslulerback.library.dummy.DummyUserUpdateDTO;
import co.simplon.alt3.kisslulerback.library.enums.Role;
import co.simplon.alt3.kisslulerback.library.exception.UserExistsException;
import co.simplon.alt3.kisslulerback.persistence.entites.User;
import co.simplon.alt3.kisslulerback.persistence.repositories.UserRepo;

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
    assertNotEquals(user.getPassword(), "jeanjean-encoded");

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
  void updateUserNewEmail() throws UserExistsException, IOException {
    UserUpdateDto userUpdateDto = new DummyUserUpdateDTO();
    userUpdateDto.setEmail("j.dujardin.com");
    User user = new DummyUser();

    when(userRepo.save(Mockito.isA(User.class))).thenReturn(user);

    UserDTOWithToken userSaved = userService.updateUser(userUpdateDto, user);

    assertNotNull(userSaved);
    assertEquals("Georges", user.getFirstName());
    assertEquals("Abitbol", user.getLastName());
    assertEquals("j.dujardin.com", user.getEmail());
    assertNotEquals("jeanjean-encoded", user.getPassword());

    User userConnected = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    assertEquals(user.getEmail(), userConnected.getEmail());

  }

  @Test
  void updateUser() throws UserExistsException, IOException {
    UserUpdateDto userUpdateDto = new DummyUserUpdateDTO();
    User user = new DummyUser();

    when(userRepo.save(Mockito.isA(User.class))).thenReturn(user);

    UserDTOWithToken userSaved = userService.updateUser(userUpdateDto, user);

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
