package co.simplon.alt3.kisslulerback.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import co.simplon.alt3.kisslulerback.IntegrationTestConfiguration;
import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.enums.Role;

public class UserRepoTest extends IntegrationTestConfiguration {

  @Autowired
  UserRepo userRepo;

  @Test
  void testExistsByEmail() {
    userRepo.save(new User("firstName", "lastName", "email@email.com", "password", Role.USER));
    assertTrue(userRepo.existsByEmail("email@email.com"));
  }

  @Test
  void testFindByEmail() {
    userRepo.save(new User("firstName", "lastName", "email@email.com", "password", Role.USER));
    assertNotNull(userRepo.findByEmail("email@email.com").get());
    assertEquals(Role.USER, userRepo.findByEmail("email@email.com").get().getRole());
  }

}
