package com.bandtec.br.finoban.dominio.resposta;

import com.bandtec.br.finoban.dominio.enums.BancoEnums;

public class BancoRespostaApi extends RespostaApi{

    private BancoEnums bancoEnums;

    public BancoRespostaApi(BancoEnums bancoEnums) {
        super();
        this.bancoEnums = bancoEnums;
    }

    public BancoEnums getBancoEnums() {
        return bancoEnums;
    }

    public void setBancoEnums(BancoEnums bancoEnums) {
        this.bancoEnums = bancoEnums;
    }
}
