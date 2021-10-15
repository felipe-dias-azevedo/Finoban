package com.bandtec.br.finoban.service.admin;

import com.bandtec.br.finoban.dominio.entidades.Permissoes;
import com.bandtec.br.finoban.dominio.exceptions.ErroRemoverDadoRelacionadoException;
import com.bandtec.br.finoban.repository.PermissoesInterface;
import com.bandtec.br.finoban.repository.database.PermissoesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@AllArgsConstructor
@Service
public class PermissoesService implements PermissoesInterface {

    private PermissoesRepository permissoesRepository;

    @Override
    public void cadastrarPermissao(Permissoes permissoes) {
        permissoesRepository.save(permissoes);
    }

    @Override
    public List<Permissoes> resgatarTodasAsPermissoes() {
        return permissoesRepository.findAll();
    }

    @Override
    public Permissoes atualizarPermissao(Permissoes permissoes) {
        permissoesRepository.updateCargo(permissoes.getCargo(), permissoes.getIdPermissao());
        return permissoesRepository.findById(permissoes.getIdPermissao()).get();
    }

    @Override
    public void deletarPermissao(int id) {
        try {
            permissoesRepository.deleteById(id);
        } catch (Exception ex) {
            throw new ErroRemoverDadoRelacionadoException();
        }
    }
}
