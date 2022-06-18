package co.simplon.alt3.kisslulerback.repo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import co.simplon.alt3.kisslulerback.entites.User;
import co.simplon.alt3.kisslulerback.enums.Role;

@SpringBootTest
@ActiveProfiles("test")
@Sql(scripts = { "/schema.sql", "/data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
public class UserRepoTest {

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
