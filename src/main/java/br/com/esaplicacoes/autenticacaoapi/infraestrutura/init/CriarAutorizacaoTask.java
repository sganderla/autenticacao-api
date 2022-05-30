package br.com.esaplicacoes.autenticacaoapi.infraestrutura.init;

import br.com.esaplicacoes.autenticacaoapi.entity.permissao.Autorizacao;
import br.com.esaplicacoes.autenticacaoapi.entity.permissao.Regras;
import br.com.esaplicacoes.autenticacaoapi.repository.AutorizacaoRepository;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@Order(0)
@Component
public class CriarAutorizacaoTask implements InitialTask {

    private Regras regras;
    private AutorizacaoRepository autorizacaoRepository;

    /**
     *
     * @param regras
     * @param autorizacaoRepository
     */
    public CriarAutorizacaoTask(
            Regras regras,
            AutorizacaoRepository autorizacaoRepository
    ){
        this.regras = regras;
        this.autorizacaoRepository = autorizacaoRepository;
    }

    /**
     *
     */
    @Override
    public void perform() {
        if(this.autorizacaoRepository.findAll().isEmpty()){
            this.regras.toList().forEach((Autorizacao autorizacao) -> {
                this.autorizacaoRepository.save(autorizacao);
            });
        }
    }
}