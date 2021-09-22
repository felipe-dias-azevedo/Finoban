package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.repository.UsuarioRepository;
import com.bandtec.br.finoban.models.Login;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import com.bandtec.br.finoban.service.usuarios.GestaoUsuariosService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api-finoban")
@AllArgsConstructor
public class LoginController {

    private GestaoUsuariosService gestaoUsuariosService;

    @PostMapping("/login")
    public ResponseEntity novoLogin(@RequestBody Login login) {
        return gestaoUsuariosService.efetuarLogin(login);
    }

    @PostMapping("/logoff")
    public ResponseEntity efetuarLogoff(@RequestBody Login login) {
        return gestaoUsuariosService.efetuarLogoff(login);
    }
}
