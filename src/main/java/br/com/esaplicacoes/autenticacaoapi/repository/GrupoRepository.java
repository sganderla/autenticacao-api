package br.com.esaplicacoes.autenticacaoapi.repository;

import br.com.esaplicacoes.autenticacaoapi.entity.permissao.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 29/05/2022
 * @since 1.0.0
 */
@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    /**
     *
     * @param nome
     * @return
     */
    Optional<Grupo> findByNome(String nome);
}
