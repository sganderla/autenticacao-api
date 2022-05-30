package br.com.esaplicacoes.autenticacaoapi.entity.permissao;

import br.com.esaplicacoes.autenticacaoapi.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@Entity
@Audited
@NoArgsConstructor
@Table(name = "permissoes", schema = "public")
@AuditTable(value = "permissoes_audit", schema = "public")
public class Permissao extends AbstractEntity {

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "grupo_id", nullable = false)
    private Grupo grupo;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "autorizacao_id", nullable = false)
    private Autorizacao autorizacao;

    /**
     *
     * @param grupo
     * @param autorizacao
     */
    public Permissao(Grupo grupo, Autorizacao autorizacao){
       super();
       this.grupo = grupo;
       this.autorizacao = autorizacao;
    }
}
