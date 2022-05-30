package br.com.esaplicacoes.autenticacaoapi.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@MappedSuperclass
@NoArgsConstructor
public abstract class AbstractEntity {

    @Id
    @Getter
    @GenericGenerator(
            name = "name_generic_generator",
            strategy = "enhanced-sequence",
            parameters = {
                    @Parameter(name = "initial_value", value = "1"),
                    @Parameter(name = "increment_size", value = "5"),
                    @Parameter(name = "optimizer", value = "pooled-lo")
            }
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "name_generic_generator"
    )
    @Column(name = "id", unique = true, updatable = false)
    private Long id;

    @Getter
    @Setter
    @Column(name = "cadastro", nullable = false)
    private LocalDateTime cadastro;

    @Getter
    @Setter
    @Column(name = "atualizacao")
    private LocalDateTime atualizacao;

    @Getter
    @Setter
    @Column(name = "ativo", nullable = false, columnDefinition = "boolean default true")
    private boolean ativo;

    /**
     *
     * @param id
     */
    public AbstractEntity(Long id){
        super();
        this.id = id;
    }

    /**
     *
     * @param id
     * @param ativo
     */
    public AbstractEntity(Long id, boolean ativo){
        super();
        this.id = id;
        this.ativo = ativo;
    }

    /**
     *
     */
    @PrePersist
    protected void prePersist(){
        this.cadastro = LocalDateTime.now();
    }

    /**
     *
     */
    @PreUpdate
    protected void preUpdate(){
        this.atualizacao = LocalDateTime.now();
    }
}
