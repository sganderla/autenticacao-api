package br.com.esaplicacoes.autenticacaoapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Eduardo Sganderla
 *
 * @version 1.0.0, 29/05/2022
 * @since 1.0.0
 */
@Controller
@RequestMapping("/api/informacoes")
/** @PreAuthorize("isAuthenticated()") */
public class InformacaoController {

    /**
     *
     * @return
     */
    @GetMapping("/sem")
    public ResponseEntity<String> semPermissao(){
        return new ResponseEntity<String>("Acesso sem login", HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping("/com")
    @PreAuthorize("hasAuthority(@regras.INFORMACAO_ACESSAR)")
    public ResponseEntity<String> comPermissao(){
        return new ResponseEntity<String>("Acesso com login e com permissão", HttpStatus.OK);
    }
}
