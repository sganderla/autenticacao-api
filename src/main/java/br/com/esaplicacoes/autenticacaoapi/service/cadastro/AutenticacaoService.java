package br.com.esaplicacoes.autenticacaoapi.service.cadastro;

import br.com.esaplicacoes.autenticacaoapi.entity.cadastro.CurrentUser;
import br.com.esaplicacoes.autenticacaoapi.entity.cadastro.Usuario;
import br.com.esaplicacoes.autenticacaoapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        final Usuario usuario = this.usuarioRepository
                .findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException(
                        "O usuário:"+ username +" não foi localizado no banco de dados."));

        return CurrentUser.build(usuario);
    }
}