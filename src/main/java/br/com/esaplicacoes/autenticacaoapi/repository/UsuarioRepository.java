package br.com.esaplicacoes.autenticacaoapi.repository;

import br.com.esaplicacoes.autenticacaoapi.entity.cadastro.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    /**
     *
     * @param login
     * @return
     */
    Optional<Usuario> findByLogin(String login);
}
