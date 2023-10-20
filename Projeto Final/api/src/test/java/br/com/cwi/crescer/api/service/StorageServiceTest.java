package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.exception.BadRequestException;
import com.amazonaws.services.s3.AmazonS3;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class StorageServiceTest {

    @InjectMocks
    StorageService storageService;

    @Mock
    AmazonS3 s3Client;

    @Test
    public void deveFazerUploadDeArquivoCorretamente(){

        MultipartFile file = new MultipartFile() {
            @Override
            public String getName() {
                return "teste.png";
            }

            @Override
            public String getOriginalFilename() {
                return "teste.png";
            }

            @Override
            public String getContentType() {
                return "png";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };

        storageService.uploadFile(file);

        verify(s3Client,times(1)).putObject(any());

    }

    @Test(expected = BadRequestException.class)
    public void deveRetornarExceptionQuandoArquivoNaoForDoTipoCorreto(){

        MultipartFile file = new MultipartFile() {
            @Override
            public String getName() {
                return "teste.png";
            }

            @Override
            public String getOriginalFilename() {
                return "teste.svg";
            }

            @Override
            public String getContentType() {
                return "png";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };

        storageService.uploadFile(file);

        verify(s3Client,times(1)).putObject(any());

    }

    @Test
    public void deveDeletarCorretamente(){
        storageService.deleteFile("teste.png");

        verify(s3Client,times(1)).deleteObject(any(),any());
    }

    @Test(expected = BadRequestException.class)
    public void QuandoNaoConseguirConverterArquivoDeveRetornarException(){
        MultipartFile file = new MultipartFile() {
            @Override
            public String getName() {
                return "teste";
            }

            @Override
            public String getOriginalFilename() {
                return "";
            }

            @Override
            public String getContentType() {
                return "png";
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public long getSize() {
                return 0;
            }

            @Override
            public byte[] getBytes() throws IOException {
                return new byte[0];
            }

            @Override
            public InputStream getInputStream() throws IOException {
                return null;
            }

            @Override
            public void transferTo(File dest) throws IOException, IllegalStateException {

            }
        };

        storageService.convertMultiPartFileToFile(file);
    }

}
