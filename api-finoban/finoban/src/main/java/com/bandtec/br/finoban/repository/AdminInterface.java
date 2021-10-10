package com.bandtec.br.finoban.repository;
import com.bandtec.br.finoban.dominio.entidades.Admin;

import java.util.List;

public interface AdminInterface {
    void cadastrarAdmin(Admin admin);
    List<Admin> resgatarTodosAdmins();
    Admin realizarLogin(Admin admin);
    void atualizarStatusAdmin(Admin admin);
    void removerAdmin(Admin admin);
    Admin resgatarAdminPeloId(int id);
}
