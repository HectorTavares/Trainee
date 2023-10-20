package br.com.cwi.crescer.api.controller;

import br.com.cwi.crescer.api.representation.tag.TagResponse;
import br.com.cwi.crescer.api.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    @Autowired
    TagService service;

    @PostMapping("/criar")
    @ResponseStatus(HttpStatus.OK)
    public void criarTag(@RequestHeader("authorization") String token, @RequestParam("nome") String novaTag) {
        service.criarTag(novaTag);
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public List<TagResponse> listarTags() {
        return service.listarTodasTags();
    }

}
