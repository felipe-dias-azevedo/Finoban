package com.bandtec.br.finoban.infraestrutura.criptografia;

import com.bandtec.br.finoban.infraestrutura.constantes.Constantes;
import org.apache.commons.codec.digest.DigestUtils;

public class Criptografia {

    public static String criptografarParaHashingSHA256(String textoParaCriptografrar) {
        return DigestUtils.sha256Hex(textoParaCriptografrar);
    }

    public static String criptografarComSalt(String textoParaCriptografar) {
        return criptografarParaHashingSHA256(textoParaCriptografar + Constantes.SALT_FINOBAN);
    }

    public static String criptografarComHashingMaisSaltMaisId(String textoParaCriptografar, int idCliente) {
        return criptografarComSalt(textoParaCriptografar + idCliente);
    }
}
