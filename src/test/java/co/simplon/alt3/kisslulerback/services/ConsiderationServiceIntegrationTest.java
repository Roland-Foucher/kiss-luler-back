package co.simplon.alt3.kisslulerback.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;

import co.simplon.alt3.kisslulerback.IntegrationTestConfiguration;
import co.simplon.alt3.kisslulerback.DTO.considerationDTO.ConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.dummy.DummyConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.dummy.DummyUser;
import co.simplon.alt3.kisslulerback.entites.Consideration;
import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;

public class ConsiderationServiceIntegrationTest extends IntegrationTestConfiguration {

  @Autowired
  IConsiderationService considerationService;

  @Test
  void testSaveConsideration() throws IOException, IncorrectMediaTypeFileException {
    MockMultipartFile image = new MockMultipartFile("file", "file.png", MediaType.IMAGE_PNG.toString(),
        "some img".getBytes());

    Consideration considerationBdd = considerationService.saveConsideration(new DummyConsiderationSaveDto(), image,
        new DummyUser());

    assertNotNull(considerationBdd);
    assertEquals(1, considerationBdd.getProject().getId());
    assertNotNull(considerationBdd.getPhoto());
  }

  @Test
  void testSaveConsiderationWithoutFile() throws IOException, IncorrectMediaTypeFileException {
    Consideration considerationBdd = considerationService.saveConsideration(new DummyConsiderationSaveDto(), null,
        new DummyUser());

    assertNotNull(considerationBdd);
    assertEquals(1, considerationBdd.getProject().getId());
    assertNull(considerationBdd.getPhoto());
  }

  @Test
  void testSaveConsiderationWithoutProjectIdThrowsException() {
    ConsiderationSaveDto consideration = new DummyConsiderationSaveDto();
    consideration.setProjectId(null);
    assertThrows(IllegalArgumentException.class,
        () -> considerationService.saveConsideration(consideration, null, new DummyUser()));
  }

  @Test
  void testSaveConsiderationNullThrowsException() {
    ConsiderationSaveDto consideration = null;
    assertThrows(IllegalArgumentException.class,
        () -> considerationService.saveConsideration(consideration, null, new DummyUser()));
  }
}
