package co.simplon.alt3.kisslulerback.library.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.simplon.alt3.kisslulerback.IntegrationTestConfiguration;
import co.simplon.alt3.kisslulerback.library.entites.User;
import co.simplon.alt3.kisslulerback.library.enums.Role;

public class UserRepoTest extends IntegrationTestConfiguration {

  @Autowired
  UserRepo userRepo;

  @Test
  void testExistsByEmail() {
    userRepo.save(new User("firstName", "lastName", "email@email.com", "password", Role.USER,
        LocalDate.of(1988, 07, 01), LocalDate.of(2022, 02, 04), null, null, null));
    assertTrue(userRepo.existsByEmail("email@email.com"));
  }

  @Test
  void testFindByEmail() {
    userRepo.save(new User("firstName", "lastName", "email@email.com", "password", Role.USER,
        LocalDate.of(1988, 07, 01), LocalDate.of(2022, 02, 04), null, null, null));
    assertNotNull(userRepo.findByEmail("email@email.com").get());
    assertEquals(Role.USER, userRepo.findByEmail("email@email.com").get().getRole());
  }

}
