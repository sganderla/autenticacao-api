package br.com.esaplicacoes.autenticacaoapi.entity.cadastro;

import br.com.esaplicacoes.autenticacaoapi.entity.AbstractEntity;
import br.com.esaplicacoes.autenticacaoapi.entity.permissao.Grupo;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@Entity
@Audited
@Table(name = "usuarios", schema = "public")
@AuditTable(value = "usuarios_audit", schema = "public")
public class Usuario extends AbstractEntity {

    @Getter
    @Setter
    @Column(name = "nome", nullable = false, length = 90)
    private String nome;

    @Getter
    @Setter
    @Column(name = "login", nullable = false, length = 45)
    private String login;

    @Getter
    @Setter
    @Column(name = "senha")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @Getter
    @Setter
    @ManyToOne(optional = false)
    @JoinColumn(name = "grupo_id", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Grupo grupo;

}
