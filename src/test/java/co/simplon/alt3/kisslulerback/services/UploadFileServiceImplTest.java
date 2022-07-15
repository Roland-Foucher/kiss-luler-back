package co.simplon.alt3.kisslulerback.services;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import co.simplon.alt3.kisslulerback.IntegrationTestConfiguration;
import co.simplon.alt3.kisslulerback.dummy.DummyUser;
import co.simplon.alt3.kisslulerback.exception.IncorrectMediaTypeFileException;

public class UploadFileServiceImplTest extends IntegrationTestConfiguration {

  @Autowired
  IUploadFileService uploadFileService;

  @Test
  void testSaveImgageFileAndDelete() throws IOException, IncorrectMediaTypeFileException {
    String url = uploadFileService.saveImgageFile(
        new MockMultipartFile("file", "file.png", MediaType.IMAGE_PNG.toString(), "some img".getBytes()));
    assertNotNull(url);

  }

  @Test
  void testSaveImgageFile_WithIncorrectMediaType() throws IOException, IncorrectMediaTypeFileException {
    assertThrows(IncorrectMediaTypeFileException.class, () -> uploadFileService
        .saveImgageFile(
            new MockMultipartFile("file", "file.png", MediaType.TEXT_HTML.toString(), "some html".getBytes())));
  }

  @Test
  void deleteFile() throws IOException, IncorrectMediaTypeFileException {
    String url = uploadFileService.saveImgageFile(
        new MockMultipartFile("file", "file.png", MediaType.IMAGE_PNG.toString(), "some img".getBytes()));
    assertTrue(uploadFileService.deleteFile(url));

  }
}