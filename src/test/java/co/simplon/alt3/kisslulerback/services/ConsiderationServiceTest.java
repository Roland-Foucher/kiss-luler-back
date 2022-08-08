package co.simplon.alt3.kisslulerback.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import co.simplon.alt3.kisslulerback.dummy.DummyConsideration;
import co.simplon.alt3.kisslulerback.dummy.DummyConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.dummy.DummyProject;
import co.simplon.alt3.kisslulerback.entites.Consideration;
import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.repo.ConsiderationRepo;
import co.simplon.alt3.kisslulerback.repo.ProjectRepo;

@ExtendWith(MockitoExtension.class)
public class ConsiderationServiceTest {

  @Mock
  ConsiderationRepo considerationRepo;

  @Mock
  ProjectRepo projectRepo;

  @Mock
  IUploadFileService uploadFileService;

  @InjectMocks
  IConsiderationService considerationService;

  @Test
  void testSaveConsideration() throws IOException, IncorrectMediaTypeFileException {
    MockMultipartFile image = new MockMultipartFile("file", "file.png", MediaType.IMAGE_PNG.toString(),
        "some img".getBytes());
    when(uploadFileService.saveImgageFile(image)).thenReturn("/upload/file.png");
    when(projectRepo.findById(1).get()).thenReturn(new DummyProject());
    when(considerationRepo.save(Mockito.isA(Consideration.class))).thenReturn(new DummyConsideration());

    Consideration considerationBdd = considerationService.saveConsideration(new DummyConsiderationSaveDto(), image);
    assertNotNull(considerationBdd);
    assertEquals(1, considerationBdd.getId());

  }
}
