package br.com.esaplicacoes.autenticacaoapi.repository;

import br.com.esaplicacoes.autenticacaoapi.entity.permissao.Autorizacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@Repository
public interface AutorizacaoRepository extends JpaRepository<Autorizacao, Long> { }
