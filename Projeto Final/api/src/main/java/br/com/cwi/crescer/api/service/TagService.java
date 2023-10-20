package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.Tag;
import br.com.cwi.crescer.api.exception.BadRequestException;
import br.com.cwi.crescer.api.repository.TagRepository;
import br.com.cwi.crescer.api.representation.tag.TagResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagService {

    ModelMapper modelMapper = new ModelMapper();

    @Autowired
    TagRepository tagRepository;

    public void criarTag(String novaTag) {

        Tag tag = new Tag();
        tag.setNome(novaTag);


        if (verificarSeTagExiste(tag) || novaTag.length() == 0) {
            throw new BadRequestException("Esta tag já existe.");
        }
        tagRepository.save(tag);
    }

    public List<TagResponse> listarTodasTags() {

        List<Tag> tagList = tagRepository.findAll();

        return tagList
                .stream()
                .map(tag -> modelMapper.map(tag, TagResponse.class))
                .collect(Collectors.toList());
    }

    public boolean verificarSeTagExiste(Tag tag) {
        return listarTodasTags()
                .stream()
                .anyMatch(t -> t.getNome().equalsIgnoreCase(tag.getNome()));
    }
}

