package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findById(Long id);

    List<Tag> findAll();

    void delete(Tag tag);
}
