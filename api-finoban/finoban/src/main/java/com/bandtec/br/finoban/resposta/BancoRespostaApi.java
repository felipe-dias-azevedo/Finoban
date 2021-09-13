package com.bandtec.br.finoban.resposta;

import com.bandtec.br.finoban.enums.BancoEnums;

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
