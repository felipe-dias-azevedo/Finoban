package com.bandtec.br.finoban.criptografia;

import com.bandtec.br.finoban.constantes.Constantes;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jcajce.provider.digest.SHA224;

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
