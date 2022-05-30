package br.com.esaplicacoes.autenticacaoapi.infraestrutura.init;

import br.com.esaplicacoes.autenticacaoapi.entity.cadastro.Usuario;
import br.com.esaplicacoes.autenticacaoapi.repository.GrupoRepository;
import br.com.esaplicacoes.autenticacaoapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 29/05/2022
 * @since 1.0.0
 */
@Order(2)
@Component
public class CriarUsuarioTask implements InitialTask {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Autowired
    public GrupoRepository grupoRepository;

    /**
     *
     */
    @Override
    public void perform() {

        this.usuarioRepository.findByLogin("admin")
                .ifPresentOrElse(saved -> { /* Empty */ }, () -> {

                    Usuario usuario = new Usuario();

                    usuario.setNome("Administrador");
                    usuario.setLogin("admin");
                    usuario.setAtivo(true);
                    usuario.setSenha(this.passwordEncoder.encode("admin"));

                    usuario.setGrupo(
                            this.grupoRepository.findByNome("Administrador").orElseThrow(() ->
                                new IllegalArgumentException(
                                        "Nenhum Grupo cadastrado como Administrador"
                                )
                            ));

                    this.usuarioRepository.save(usuario);
                });

        this.usuarioRepository.findByLogin("usuario")
                .ifPresentOrElse(saved -> { /* Empty */ }, () -> {

                    Usuario usuario = new Usuario();

                    usuario.setNome("Usuário");
                    usuario.setLogin("usuario");
                    usuario.setAtivo(true);
                    usuario.setSenha(this.passwordEncoder.encode("usuario"));

                    usuario.setGrupo(
                            this.grupoRepository.findByNome("Usuário").orElseThrow(() ->
                                    new IllegalArgumentException(
                                            "Nenhum Grupo cadastrado como Usuário"
                                    )
                            ));

                    this.usuarioRepository.save(usuario);
                });
    }
}
