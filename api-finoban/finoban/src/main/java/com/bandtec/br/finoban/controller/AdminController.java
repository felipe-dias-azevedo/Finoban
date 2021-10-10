package com.bandtec.br.finoban.controller;

import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.resposta.ResponseGeneric;
import com.bandtec.br.finoban.service.admin.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api-finoban/admins")
@AllArgsConstructor
public class AdminController {

    private AdminService adminService;

    @GetMapping
    public ResponseEntity getAllAdmins() {
        List<Admin> adminList = adminService.resgatarTodosAdmins();
        if (adminList.isEmpty())
            return ResponseEntity.status(204).build();

        return  ResponseEntity.status(200).body(new ResponseGeneric<>(adminList));
    }

    @PostMapping
    public ResponseEntity postAdmin(@RequestBody Admin admin) {
        adminService.cadastrarAdmin(admin);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getAdminForId(@PathVariable int id) {
        return ResponseEntity.status(200).body(new ResponseGeneric<>(adminService.resgatarAdminPeloId(id)));
    }
}
