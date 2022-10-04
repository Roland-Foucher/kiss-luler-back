package co.simplon.alt3.kisslulerback.business.services.considerationService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import co.simplon.alt3.kisslulerback.business.utils.uploadFileService.IUploadFileService;
import co.simplon.alt3.kisslulerback.library.dummy.DummyConsideration;
import co.simplon.alt3.kisslulerback.library.dummy.DummyConsiderationSaveDto;
import co.simplon.alt3.kisslulerback.library.dummy.DummyProject;
import co.simplon.alt3.kisslulerback.library.dummy.DummyUser;
import co.simplon.alt3.kisslulerback.library.exception.IncorrectMediaTypeFileException;
import co.simplon.alt3.kisslulerback.library.entites.Consideration;
import co.simplon.alt3.kisslulerback.library.repositories.ConsiderationRepo;
import co.simplon.alt3.kisslulerback.library.repositories.ProjectRepo;

@ExtendWith(MockitoExtension.class)
public class ConsiderationServiceTest {

  @Mock
  ConsiderationRepo considerationRepo;

  @Mock
  ProjectRepo projectRepo;

  @Mock
  IUploadFileService uploadFileService;

  @InjectMocks
  ConsiderationServiceImpl considerationService;

  @Test
  void testSaveConsideration() throws IOException, IncorrectMediaTypeFileException {
    MockMultipartFile image = new MockMultipartFile("file", "file.png", MediaType.IMAGE_PNG.toString(),
        "some img".getBytes());
    when(uploadFileService.saveImgageFile(image)).thenReturn("/upload/file.png");
    when(projectRepo.findById(1)).thenReturn(Optional.of(new DummyProject()));
    when(considerationRepo.save(Mockito.isA(Consideration.class))).thenReturn(new DummyConsideration());

    Consideration considerationBdd = considerationService.saveConsideration(new DummyConsiderationSaveDto(), image,
        new DummyUser());
    assertNotNull(considerationBdd);
    assertEquals(1, considerationBdd.getId());
  }
}
