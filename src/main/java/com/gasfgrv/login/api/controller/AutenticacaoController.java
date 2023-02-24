package com.gasfgrv.login.api.controller;

import com.gasfgrv.login.api.model.DadosAutenticacao;
import com.gasfgrv.login.api.model.DadosTokenJWT;
import com.gasfgrv.login.domain.model.Usuario;
import com.gasfgrv.login.domain.service.TokenService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = authenticationManager.authenticate(authenticationToken);
        var tokenJwt = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(tokenJwt));
    }

    @GetMapping("/sucesso")
    @SecurityRequirement(name = "bearerAuth")
    public ResponseEntity<String> loginComSucesso() {
        return ResponseEntity.ok("Login efetuado com sucesso, seu token tem um prazo de 2h após gerado");
    }

}
