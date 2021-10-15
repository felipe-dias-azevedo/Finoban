package com.bandtec.br.finoban.repository;
import com.bandtec.br.finoban.dominio.Login;
import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLogin;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLoginAdmin;

import java.util.List;

public interface AdminInterface {
    void cadastrarAdmin(Admin admin);
    List<Admin> resgatarTodosAdmins();
    RespostaLogin realizarLogin(Login logoff);
    void realizarLogoff(Login logoff);
    Admin atualizarStatusAdmin(Admin admin);
    void removerAdminPorId(int idAdmin);
    Admin resgatarAdminPeloId(int id);
    Admin atualizarDados(Admin admin);
}
