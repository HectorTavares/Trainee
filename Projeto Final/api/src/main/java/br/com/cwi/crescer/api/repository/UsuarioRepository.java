package br.com.cwi.crescer.api.repository;

import br.com.cwi.crescer.api.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Usuario findUsuarioById(Long id);


    @Query(value = "SELECT * from usuario u where u.is_monitor = false order by u.reputacao desc", nativeQuery = true)
    Page<Usuario> findAllByOrderByReputacaoDesc(Pageable pageable);

    @Query("SELECT u from Usuario u where (u.username like lower(concat('%',:busca,'%')) or u.email like lower(concat('%',:busca,'%'))) and (u.id <> :usuarioId and u.id <> :adminId)")
    Page<Usuario> usuariosBusca(String busca, Long usuarioId, Long adminId, Pageable pageable);

}
