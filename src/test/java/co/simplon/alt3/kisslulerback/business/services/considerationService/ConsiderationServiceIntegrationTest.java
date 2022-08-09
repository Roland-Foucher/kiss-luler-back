package co.simplon.alt3.kisslulerback.business.services.considerationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import co.simplon.alt3.kisslulerback.IntegrationTestConfiguration;
import co.simplon.alt3.kisslulerback.library.DTO.considerationDTO.ConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.library.DTO.considerationDTO.ConsiderationUpdateDto;
import co.simplon.alt3.kisslulerback.library.dummy.DummyConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.library.dummy.DummyConsiderationUpdateDto;
import co.simplon.alt3.kisslulerback.library.dummy.DummyUser;
import co.simplon.alt3.kisslulerback.library.enums.ConsiderationStatus;
import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.library.entites.Consideration;
import co.simplon.alt3.kisslulerback.library.repositories.ConsiderationRepo;

public class ConsiderationServiceIntegrationTest extends IntegrationTestConfiguration {

  @Autowired
  IConsiderationService considerationService;

  @Autowired
  ConsiderationRepo considerationRepo;

  @Nested
  class saveConsideration {

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

  @Nested
  class updateConsideration {

    @Test
    void updateConsiderationOk() throws IOException, IncorrectMediaTypeFileException {
      Consideration updateConsideration = considerationService.updateConsideration(new DummyConsiderationUpdateDto(),
          null, new DummyUser());
      assertNotNull(updateConsideration);
      assertEquals(200, updateConsideration.getConsidAmount());
      assertEquals("Consideration 2", updateConsideration.getTitle());
      assertEquals("myFile", updateConsideration.getPhoto());
    }

    @Test
    void updateConsiderationWithFile() throws IOException, IncorrectMediaTypeFileException {

      MockMultipartFile image = new MockMultipartFile("file", "file.png", MediaType.IMAGE_PNG.toString(),
          "some img".getBytes());

      Consideration updateConsideration = considerationService.updateConsideration(new DummyConsiderationUpdateDto(),
          image, new DummyUser());

      assertNotNull(updateConsideration);
      assertNotNull(updateConsideration.getPhoto());
      assertNotEquals("myFile", updateConsideration.getPhoto());
    }

    @Test
    void updateConsiderationNotInProgress() throws IOException, IncorrectMediaTypeFileException {
      ConsiderationUpdateDto consideration = new DummyConsiderationUpdateDto();
      consideration.setConsiderationId(3);
      assertThrows(IllegalArgumentException.class,
          () -> considerationService.updateConsideration(consideration, null, new DummyUser()));
    }
  }

  @Nested
  class deleteConsideration {

    @Test
    void deleteConsiderationOk() {
      considerationService.deleteConsideration(2, new DummyUser());
      assertNull(considerationRepo.findById(2).orElse(null));
    }

    @Test
    void deleteConsiderationNotInProgress() {
      assertThrows(IllegalArgumentException.class, () -> considerationService.deleteConsideration(3, new DummyUser()));
    }
  }

  @Nested
  class changeConsiderationStatus {

    @Test
    void changeStatusToReady() {
      Consideration consideration = considerationService.setReadyStatus(2, new DummyUser());
      assertEquals(ConsiderationStatus.READY, consideration.getStatus());
    }

    @Test
    void changeStatusReadyToReadyThrowException() {
      assertThrows(IllegalArgumentException.class, () -> considerationService.setReadyStatus(3, new DummyUser()));
    }

    @Test
    void changeStatusToClosed() {
      Consideration consideration = considerationService.setClosedStatus(3, new DummyUser());
      assertEquals(ConsiderationStatus.CLOSED, consideration.getStatus());
    }

    @Test
    void changeStatusInProgressToClosedThrowException() {
      assertThrows(IllegalArgumentException.class, () -> considerationService.setClosedStatus(2, new DummyUser()));
    }
  }
}
