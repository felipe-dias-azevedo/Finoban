package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.exceptions.UsuarioLogadoException;
import com.bandtec.br.finoban.dominio.exceptions.UsuarioNaoLogadoException;
import com.bandtec.br.finoban.dominio.listaLigada.HashTable;
import com.bandtec.br.finoban.dominio.resposta.RespostaLogin;
import com.bandtec.br.finoban.repository.LoginRepository;
import com.bandtec.br.finoban.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements LoginRepository {

    @Autowired
    private AuthService authService;
    private HashTable hashTable;

    public LoginService() {
        this.hashTable = new HashTable(5000);
    }

    @Override
    public boolean verificaUsuarioLogado(Usuario usuario) {
        return hashTable.busca(usuario);
    }

    @Override
    public RespostaLogin logarUsuario(Usuario usuario) {
        if (verificaUsuarioLogado(usuario))
            throw new UsuarioLogadoException();

        hashTable.insere(usuario);
        RespostaLogin respostaLogin = new RespostaLogin(usuario, authService.generateToken(usuario));
        return respostaLogin;
    }

    @Override
    public void realizarLogoffUsuario(Usuario usuario) {
        if (!verificaUsuarioLogado(usuario))
            throw new UsuarioNaoLogadoException();

        hashTable.remove(usuario);
    }
}
