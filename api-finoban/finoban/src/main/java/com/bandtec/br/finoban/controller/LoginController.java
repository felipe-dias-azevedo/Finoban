package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.Login;
import com.bandtec.br.finoban.dominio.resposta.ResponseGeneric;
import com.bandtec.br.finoban.dominio.resposta.UsuarioRespostaSimples;
import com.bandtec.br.finoban.service.usuarios.GestaoUsuariosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api-finoban")
@AllArgsConstructor
public class LoginController {

    private GestaoUsuariosService gestaoUsuariosService;

    @PostMapping("/login")
    public ResponseEntity novoLogin(@RequestBody Login login) {
        return ResponseEntity.status(200).body(new ResponseGeneric<>(gestaoUsuariosService.efetuarLogin(login)));
    }

    @PostMapping("/logoff")
    public ResponseEntity efetuarLogoff(@RequestBody Login login) {
        gestaoUsuariosService.efetuarLogoff(login);
        return ResponseEntity.status(204).build();
    }
}
