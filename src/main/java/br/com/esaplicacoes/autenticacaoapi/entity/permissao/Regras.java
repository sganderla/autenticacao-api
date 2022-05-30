package br.com.esaplicacoes.autenticacaoapi.entity.permissao;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@Component("regras")
public class Regras {

    /**
     * Regras de Acesso -> Informações do Servidor
     */
    @Getter
    @AgruparRegras("informacao")
    private final String INFORMACAO_ACESSAR = "informacao:acessar";

    /**
     *
     * @return
     */
    public List<Autorizacao> toList(){

        final List<Autorizacao> autorizacaos = new ArrayList<Autorizacao>();

        for (Field field : this.getClass().getDeclaredFields()){

            field.setAccessible(true);

            try{
                 final AgruparRegras agruparRegras = field.getAnnotation(AgruparRegras.class);

                 field.setAccessible(true);
                 final String acao = String.valueOf(field.get(this));
                 final String funcionalidade = agruparRegras.value();

                 autorizacaos.add(new Autorizacao(funcionalidade, acao.replace(funcionalidade + ":", "")));
            }
            catch (IllegalAccessException illegalAccessException){
                illegalAccessException.printStackTrace();
            }
        }

        return autorizacaos;
    }
}
