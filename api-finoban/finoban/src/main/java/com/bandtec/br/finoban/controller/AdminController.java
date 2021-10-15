package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.Login;
import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.resposta.ResponseGeneric;
import com.bandtec.br.finoban.repository.AdminInterface;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-finoban/admins")
@AllArgsConstructor
public class AdminController {

    private AdminInterface adminRepository;

    @GetMapping
    public ResponseEntity getAllAdmins() {
        List<Admin> adminList = adminRepository.resgatarTodosAdmins();
        if (adminList.isEmpty())
            return ResponseEntity.status(204).build();

        return  ResponseEntity.status(200).body(new ResponseGeneric<>(adminList));
    }

    @PostMapping
    public ResponseEntity postAdmin(@RequestBody Admin admin) {
        adminRepository.cadastrarAdmin(admin);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getAdminForId(@PathVariable int id) {
        return ResponseEntity.status(200).body(new ResponseGeneric<>(adminRepository.resgatarAdminPeloId(id)));
    }

    @PostMapping("/login")
    public ResponseEntity loginAdmin(@RequestBody Login login) {
        return ResponseEntity.status(201).body(new ResponseGeneric<>(adminRepository.realizarLogin(login)));
    }

    @PostMapping("/logoff")
    public ResponseEntity logoffAdmin(@RequestBody Login logoff) {
        adminRepository.realizarLogoff(logoff);
        return ResponseEntity.status(204).build();
    }

    @PutMapping("/atualizar-status")
    public ResponseEntity atualizarStatus(@RequestBody Admin admin) {
        return ResponseEntity.status(200).body(new ResponseGeneric<>(adminRepository.atualizarStatusAdmin(admin)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity removerPorId(@PathVariable int id) {
        adminRepository.removerAdminPorId(id);
        return ResponseEntity.status(204).build();
    }
}
