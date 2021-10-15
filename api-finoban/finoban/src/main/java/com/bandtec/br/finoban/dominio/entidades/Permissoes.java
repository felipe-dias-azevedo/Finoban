package com.bandtec.br.finoban.dominio.entidades;

import com.bandtec.br.finoban.dominio.enums.AcessosAdminEnum;
import com.bandtec.br.finoban.dominio.enums.CargoEnum;
import com.bandtec.br.finoban.dominio.exceptions.CargoInexistenteException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Getter
@Setter
public class Permissoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPermissao;
    private CargoEnum cargo;
    private boolean status;

    public List<AcessosAdminEnum> getAcessos() {
        switch (cargo) {
            case JUNIOR:
                return new ArrayList<>(Arrays.asList(AcessosAdminEnum.ACESSO_DASHBOARD));
            case ANALISTA:
                return new ArrayList<>(Arrays.asList(
                        AcessosAdminEnum.ACESSO_DASHBOARD,
                        AcessosAdminEnum.ACESSO_TESTES));
            case CONSULTOR:
                return new ArrayList<>(Arrays.asList(
                        AcessosAdminEnum.ACESSO_DASHBOARD,
                        AcessosAdminEnum.ACESSO_TESTES,
                        AcessosAdminEnum.ACESSO_USUARIOS));
            case GERENTE:
                return new ArrayList<>(Arrays.asList(
                        AcessosAdminEnum.ACESSO_DASHBOARD,
                        AcessosAdminEnum.ACESSO_TESTES,
                        AcessosAdminEnum.ACESSO_USUARIOS,
                        AcessosAdminEnum.ACESSO_CRIACAO_USUARIOS));
            default:
                throw new CargoInexistenteException();
        }
    }

}
