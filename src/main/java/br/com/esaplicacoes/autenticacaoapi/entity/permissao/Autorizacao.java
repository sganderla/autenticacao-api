package br.com.esaplicacoes.autenticacaoapi.entity.permissao;

import br.com.esaplicacoes.autenticacaoapi.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@Entity
@Audited
@NoArgsConstructor
@Table(name = "autorizacoes", schema = "public", uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_funcionalidade_acao",
                columnNames = { "funcionalidade", "acao" }
        )
})
@AuditTable(value = "autorizacoes_audit", schema = "public")
public class Autorizacao extends AbstractEntity implements GrantedAuthority {

    @Getter
    @Setter
    @Column(name = "funcionalidade", nullable = false)
    private String funcionalidade;

    @Getter
    @Setter
    @Column(name = "acao", nullable = false)
    private String acao;

    /**
     *
     * @param funcionalidade
     * @param acao
     */
    public Autorizacao(
            String funcionalidade,
            String acao
    ){
        super();
        this.funcionalidade = funcionalidade;
        this.acao = acao;
    }

    /**
     *
     * @return
     */
    @Override
    public String getAuthority() {
        return this.funcionalidade + ":" + this.acao;
    }
}
