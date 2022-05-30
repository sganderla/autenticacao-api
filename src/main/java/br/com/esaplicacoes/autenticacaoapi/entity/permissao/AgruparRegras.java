package br.com.esaplicacoes.autenticacaoapi.entity.permissao;

import java.lang.annotation.*;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AgruparRegras {

    String value() default "";

}
