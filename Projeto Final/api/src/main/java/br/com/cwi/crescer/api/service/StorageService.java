package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.exception.BadRequestException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

@Service
@Slf4j
public class StorageService {

    private static final String FILE_URL = "https://formimagestorage.s3.sa-east-1.amazonaws.com/";

    private static final String[] FILE_TYPES = {"jpeg", "jpg", "png"};

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;

    public String uploadFile(MultipartFile file) {
        File fileObj = convertMultiPartFileToFile(file);

        verificarTipoDeArquivo(file.getOriginalFilename());

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
        boolean delete = fileObj.delete();
        if (!delete){
            throw new com.amazonaws.services.mq.model.BadRequestException("não foi possivel deletar o arquivo");
        }
        String fileNameUrl = FILE_URL + fileName;
        return fileNameUrl;
    }

    private void verificarTipoDeArquivo(String nomeArquivo) {
        String extensao = "";
        int i = nomeArquivo.lastIndexOf('.');
        if (i >= 0) {
            extensao = nomeArquivo.substring(i + 1).toLowerCase();
        }
        if (!Arrays.asList(FILE_TYPES).contains(extensao)) {
            throw new BadRequestException("Este não é um tipo de arquivo suportado");
        }
    }

    public void deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
    }

    public File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new BadRequestException("Não foi possível converter o arquivo");
        }
        return convertedFile;
    }

}
