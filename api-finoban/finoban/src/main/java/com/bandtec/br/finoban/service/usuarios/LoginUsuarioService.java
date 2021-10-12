package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.exceptions.UsuarioLogadoException;
import com.bandtec.br.finoban.dominio.exceptions.UsuarioNaoLogadoException;
import com.bandtec.br.finoban.dominio.listaLigada.HashTable;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLoginUsuario;
import com.bandtec.br.finoban.repository.LoginRepository;
import com.bandtec.br.finoban.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginUsuarioService implements LoginRepository {

    @Autowired
    private AuthService authService;
    private HashTable hashTable;

    public LoginUsuarioService() {
        this.hashTable = new HashTable(26);
    }

    @Override
    public boolean verificaUsuarioLogado(Usuario usuario) {
        return hashTable.busca(usuario);
    }

    @Override
    public RespostaLoginUsuario logarUsuario(Usuario usuario) {
        if (verificaUsuarioLogado(usuario))
            throw new UsuarioLogadoException();

        hashTable.insere(usuario);
        return new RespostaLoginUsuario(usuario, authService.generatetokenUsuario(usuario));
    }

    @Override
    public void realizarLogoffUsuario(Usuario usuario) {
        if (!verificaUsuarioLogado(usuario))
            throw new UsuarioNaoLogadoException();

        hashTable.remove(usuario);
    }
}
