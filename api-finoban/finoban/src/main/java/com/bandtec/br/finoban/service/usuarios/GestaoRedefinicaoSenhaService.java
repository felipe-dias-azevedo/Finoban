package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.dominio.entidades.RedefinicaoSenha;
import com.bandtec.br.finoban.repository.GestaoRedefinicaoSenhaRepository;
import com.bandtec.br.finoban.repository.database.RedefinicaoSenhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GestaoRedefinicaoSenhaService implements GestaoRedefinicaoSenhaRepository {

    private final RedefinicaoSenhaRepository redefinicaoSenhaRepository;

    @Override
    public List<RedefinicaoSenha> listarRedefinicoes() {
        return redefinicaoSenhaRepository.findAll();
    }
}
