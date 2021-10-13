package com.bandtec.br.finoban.service.admin;

import com.bandtec.br.finoban.dominio.entidades.Permissoes;
import com.bandtec.br.finoban.repository.PermissoesInterface;
import com.bandtec.br.finoban.repository.PermissoesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

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
    public void atualizarPermissao(Permissoes permissoes) {
        permissoesRepository.updateCargo(permissoes.getCargo(), permissoes.getIdPermissao());
    }
}
