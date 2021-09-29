package com.bandtec.br.finoban.service.usuarios;

import com.bandtec.br.finoban.entidades.RedefinicaoSenha;
import com.bandtec.br.finoban.repository.GestaoRedefinicaoSenhaRepository;
import com.bandtec.br.finoban.repository.RedefinicaoSenhaRepository;
import com.bandtec.br.finoban.resposta.ResponseGeneric;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GestaoRedefinicaoSenhaService implements GestaoRedefinicaoSenhaRepository {

    private final RedefinicaoSenhaRepository redefinicaoSenhaRepository;

    @Override
    public ResponseEntity listarRedefinicoes() {
        List<RedefinicaoSenha> redefinicaoSenhaList = redefinicaoSenhaRepository.findAll();
        if (redefinicaoSenhaList.isEmpty())
            return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(new ResponseGeneric(redefinicaoSenhaList));
    }
}
