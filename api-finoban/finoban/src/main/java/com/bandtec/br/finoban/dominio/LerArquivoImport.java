package com.bandtec.br.finoban.dominio;

import com.bandtec.br.finoban.dominio.entidades.Regiao;
import com.bandtec.br.finoban.dominio.entidades.Usuario;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class LerArquivoImport {

    public List<List> importarDados(byte[] arquivo) {
        BufferedReader entrada = null;
        InputStream is = null;
        List<Regiao> listaRegiao = new ArrayList<>();
        List<Usuario> listaUsuario = new ArrayList<>();
        List<List> listaResposta = new ArrayList<>();
        String registro;
        String tipoRegistro;

        is = new ByteArrayInputStream(arquivo);
        entrada = new BufferedReader(new InputStreamReader(is));

        try {
            registro = entrada.readLine();

            while (registro != null) {
                tipoRegistro = registro.substring(0, 2);

                if (tipoRegistro.equals("02")) {

                    String descricao = registro.substring(2, 19).trim();
                    Integer valor = Integer.parseInt(registro.substring(19, 29));

                    Regiao regiao = new Regiao();

                    regiao.setDescricaoRegiao(descricao);
                    regiao.setValorRegiao(valor);
                    regiao.setDataCraw(LocalDateTime.now());

                    listaRegiao.add(regiao);
                }

                if (tipoRegistro.equals("03")) {

                    String cpf = registro.substring(2, 16).trim();
                    String nome = registro.substring(16, 106).trim();
                    String email = registro.substring(106, 146).trim();
                    String senha = registro.substring(147, 196).trim();
                    String cep = registro.substring(196, 204).trim();
                    Integer numero = Integer.parseInt(registro.substring(204, 209));
                    String dataNasc = registro.substring(209, 221).trim();
                    String bairro = registro.substring(221, 238).trim();

                    Usuario usuario = new Usuario();
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDate dataFormatada = LocalDate.parse(dataNasc, formatter);

                    usuario.setCpf(cpf);
                    usuario.setNome(nome);
                    usuario.setDataCriacao(LocalDateTime.now());
                    usuario.setEmail(email);
                    usuario.setSenha(senha);
                    usuario.setCep(cep);
                    usuario.setNumero(numero);
                    usuario.setDataNasc(dataFormatada);
                    usuario.setBairro(bairro);

                    listaUsuario.add(usuario);

                }

                registro = entrada.readLine();
            }
            entrada.close();
        } catch (IOException e) {
            System.err.printf("Erro ao ler arquivo: %s.\n", e.getMessage());
        }

        listaResposta.add(listaRegiao);
        listaResposta.add(listaUsuario);

        return listaResposta;

    }

}
