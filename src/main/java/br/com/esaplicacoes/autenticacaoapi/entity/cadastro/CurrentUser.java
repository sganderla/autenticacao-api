package br.com.esaplicacoes.autenticacaoapi.entity.cadastro;


import br.com.esaplicacoes.autenticacaoapi.entity.permissao.Permissao;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 28/05/2022
 * @since 1.0.0
 */
@NoArgsConstructor
public class CurrentUser implements UserDetails {

    @Getter
    @Setter
    private String name;
    @Setter
    private String username;

    @JsonIgnore
    @Setter
    private boolean active;
    @JsonIgnore
    @Setter
    private String password;
    @JsonIgnore
    @Setter
    private Collection<? extends GrantedAuthority> authorities;

    /**
     *
     * @param name
     * @param username
     * @param active
     * @param password
     * @param authorities
     */
    public CurrentUser(
            String name,
            String username,
            boolean active,
            String password,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.name = name;
        this.username = username;
        this.active = active;
        this.password = password;
        this.authorities = authorities;
    }

    /**
     *
     * @param usuario
     * @return
     */
    public static CurrentUser build(final Usuario usuario) {
        final Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        usuario.getGrupo().getPermissaos().forEach((Permissao permissao) -> {
            grantedAuthoritySet.add(permissao.getAutorizacao());
        });

        CurrentUser currentUser = new CurrentUser();
        currentUser.setName(usuario.getNome());
        currentUser.setUsername(usuario.getLogin());
        currentUser.setActive(usuario.isAtivo());
        currentUser.setPassword(usuario.getSenha());
        currentUser.setAuthorities(grantedAuthoritySet);

        return currentUser;
    }

    /**
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     *
     * @return
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     *
     * @return
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return this.active;
    }

    /**
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return this.active;
    }

    /**
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return this.active;
    }

    /**
     *
     * @return
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.active;
    }
}
