package com.bandtec.br.finoban.controller;


import com.bandtec.br.finoban.entidades.Regiao;
import com.bandtec.br.finoban.entidades.Usuario;
import com.bandtec.br.finoban.models.LerArquivoImport;
import com.bandtec.br.finoban.repository.UsuarioRepository;
import com.bandtec.br.finoban.repository.RegiaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api-finoban/exportar")
public class ExportaArquivo {

    @Autowired
    private RegiaoRepository regiaoRepository;

    @Autowired
    private UsuarioRepository cadastroRepository;

    LerArquivoImport ler = new LerArquivoImport();

    @PostMapping()
    public ResponseEntity imporatarDados(@RequestParam MultipartFile arquivo) throws IOException {

        if (arquivo.getContentType().equals("text/plain")) {
            byte[] conteudo = arquivo.getBytes();

            List<Regiao> regiaoList = ler.importarDados(conteudo).get(0);
            List<Usuario> usuarioList = ler.importarDados(conteudo).get(1);

            if (regiaoList.size() > 0 || usuarioList.size() > 0) {

                for (Regiao regiao:regiaoList) {
                    regiaoRepository.save(regiao);
                }

                for (Usuario usuario:usuarioList) {
                    cadastroRepository.save(usuario);
                }

                return ResponseEntity.status(201).build();

            }

            return ResponseEntity.status(400).build();
        }

        return ResponseEntity.status(400).build();

    }

}
