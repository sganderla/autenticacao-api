package br.com.esaplicacoes.autenticacaoapi.entity.permissao;

import br.com.esaplicacoes.autenticacaoapi.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.AuditTable;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@Entity
@Audited
@NoArgsConstructor
@Table(name = "grupos", schema = "public")
@AuditTable(value = "grupos_audit", schema = "public")
public class Grupo extends AbstractEntity {

    @Getter
    @Setter
    @Column(name = "nome", nullable = false, unique = true, length = 50)
    private String nome;

    @Getter
    @Setter
    @Column(name = "descricao", nullable = false, length = 250)
    private String descricao;

    @Getter
    @Setter
    @OneToMany(mappedBy = "grupo", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Permissao> permissaos;

    /**
     *
     * @param id
     * @param nome
     */
    public Grupo(Long id, String nome){
        super(id);
        this.nome = nome;
    }

    /**
     *
     * @param id
     * @param nome
     * @param ativo
     */
    public Grupo(Long id, String nome, boolean ativo){
        super(id, ativo);
        this.nome = nome;
    }
}
