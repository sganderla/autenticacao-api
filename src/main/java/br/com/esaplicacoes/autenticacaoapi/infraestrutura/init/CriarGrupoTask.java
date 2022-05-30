package br.com.esaplicacoes.autenticacaoapi.infraestrutura.init;

import br.com.esaplicacoes.autenticacaoapi.entity.permissao.Autorizacao;
import br.com.esaplicacoes.autenticacaoapi.entity.permissao.Grupo;
import br.com.esaplicacoes.autenticacaoapi.entity.permissao.Permissao;
import br.com.esaplicacoes.autenticacaoapi.repository.AutorizacaoRepository;
import br.com.esaplicacoes.autenticacaoapi.repository.GrupoRepository;
import br.com.esaplicacoes.autenticacaoapi.repository.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@Order(1)
@Component
public class CriarGrupoTask implements InitialTask {

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Autowired
    private AutorizacaoRepository autorizacaoRepository;

    /**
     *
     */
    @Override
    public void perform() {

        if(this.grupoRepository.findByNome("Administrador").isEmpty()){

            Grupo grupo = new Grupo();

            grupo.setAtivo(true);
            grupo.setNome("Administrador");
            grupo.setDescricao("Grupo com acesso total as funcionalidades do sistema.");

            this.grupoRepository.save(grupo);
            this.addPermissoesAdministrador(grupo);
        }

        if(this.grupoRepository.findByNome("Usuário").isEmpty()){

            Grupo grupo = new Grupo();

            grupo.setAtivo(true);
            grupo.setNome("Usuário");
            grupo.setDescricao("Grupo com acesso usuário.");

            this.grupoRepository.save(grupo);
        }
    }

    /**
     *
     * @param grupo
     */
    private void addPermissoesAdministrador(Grupo grupo){
        this.autorizacaoRepository.findAll().forEach((Autorizacao autorizacao) -> {

            Permissao permissao = new Permissao();

            permissao.setGrupo(grupo);
            permissao.setAutorizacao(autorizacao);

            this.permissaoRepository.save(permissao);
        });
    }
}
