package br.com.cwi.crescer.api.controller;


import br.com.cwi.crescer.api.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired
    private StorageService service;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.OK)
    public String upload(@RequestParam("file") MultipartFile file) {
        return service.uploadFile(file);
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestParam("file") String file) {
        service.deleteFile(file);
    }

}
