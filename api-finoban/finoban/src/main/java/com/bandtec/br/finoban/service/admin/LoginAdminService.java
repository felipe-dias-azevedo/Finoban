package com.bandtec.br.finoban.service.admin;

import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.exceptions.AdminLogadoException;
import com.bandtec.br.finoban.dominio.exceptions.AdminNaoLogadoException;
import com.bandtec.br.finoban.dominio.listaLigada.HashTable;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLogin;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLoginAdmin;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLoginUsuario;
import com.bandtec.br.finoban.repository.LoginAdminRepository;
import com.bandtec.br.finoban.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginAdminService implements LoginAdminRepository {

    @Autowired
    private AuthService authService;
    private HashTable hashTable;

    public LoginAdminService() {
        this.hashTable = new HashTable(26);
    }

    @Override
    public boolean verificaUsuarioLogado(Admin usuario) {
        return this.hashTable.busca(usuario);
    }

    @Override
    public RespostaLogin logarUsuario(Admin admin) {
        if (verificaUsuarioLogado(admin))
            throw new AdminLogadoException();

        hashTable.insere(admin);
        return new RespostaLoginAdmin(admin, authService.generateTokenAdmin(admin));
    }

    @Override
    public void realizarLogoffUsuario(Admin admin) {
        if (!verificaUsuarioLogado(admin))
            throw new AdminNaoLogadoException();

        hashTable.remove(admin);
    }
}
