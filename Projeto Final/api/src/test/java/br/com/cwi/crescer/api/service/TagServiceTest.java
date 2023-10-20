package br.com.cwi.crescer.api.service;

import br.com.cwi.crescer.api.domain.Tag;
import br.com.cwi.crescer.api.exception.BadRequestException;
import br.com.cwi.crescer.api.repository.TagRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {

    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository repository;



    @Test
    public void deveCriarTagCorretamente(){

        tagService.criarTag( "tag");

        verify(repository, times(1)).save(any());

    }

    @Test(expected = BadRequestException.class)

    public void deveRetornarExceptionQuandoTagJaExistir(){
        List<Tag> tagList = new ArrayList<>();
        Tag tag1 = new Tag();
        tag1.setId(1L);
        tag1.setNome("tag");
        Tag tag2 = new Tag();
        tag2.setId(2L);
        tag2.setNome("tag2");
        tagList.add(tag1);
        tagList.add(tag2);

        when(repository.findAll())
                .thenReturn(tagList);

        tagService.criarTag("tag");

    }

    @Test(expected = BadRequestException.class)
    public void deveRetornarExceptionQuandoTagNaoTiverCaracteres(){

        tagService.criarTag( "");

    }

    @Test
    public void deveListarTodasTagsCorretamente() {

        List<Tag> tagList = new ArrayList<>();
        Tag tag = new Tag();
        tag.setId(1L);
        tag.setNome("tag");
        Tag tag1 = new Tag();
        tag.setId(2L);
        tag.setNome("tag2");
        tagList.add(tag);
        tagList.add(tag1);

        when(repository.findAll()).thenReturn(tagList);
        tagService.listarTodasTags();

        Assert.assertEquals(tagList.size(), tagService.listarTodasTags().size());
    }

    @Test
    public void deveVerificarSeTagExiste() {

        List<Tag> tagList = new ArrayList<>();

        Tag tag = new Tag();
        tag.setId(1L);
        tag.setNome("tag");

        Tag tag1 = new Tag();
        tag1.setId(2L);
        tag1.setNome("tag2");
        tagList.add(tag);
        tagList.add(tag1);

        when(repository.findAll()).thenReturn(tagList);
        Boolean retorno = tagService.verificarSeTagExiste(tag);

        Assert.assertEquals(true, retorno);
    }

}
