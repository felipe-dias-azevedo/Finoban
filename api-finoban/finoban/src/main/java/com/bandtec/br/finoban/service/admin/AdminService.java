package com.bandtec.br.finoban.service.admin;

import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.entidades.Permissoes;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.exceptions.AdminNaoEncontradoException;
import com.bandtec.br.finoban.dominio.exceptions.ClienteNaoEncontradoException;
import com.bandtec.br.finoban.dominio.exceptions.PermissaoNaoEncontradaException;
import com.bandtec.br.finoban.repository.AdminInterface;
import com.bandtec.br.finoban.repository.AdminRepository;
import com.bandtec.br.finoban.repository.PermissoesRepository;
import com.bandtec.br.finoban.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AdminService implements AdminInterface {

    private AdminRepository adminRepository;
    private UsuarioRepository usuarioRepository;
    private PermissoesRepository permissoesRepository;

    @Override
    public void cadastrarAdmin(Admin admin) {
        Optional<Usuario> usuario = usuarioRepository.findById(admin.getUsuario().getIdUsuario());
        if (!usuario.isPresent())
            throw new ClienteNaoEncontradoException();

        Optional<Permissoes> permissoes = permissoesRepository.findById(admin.getPermissoes().getIdPermissao());
        if (!permissoes.isPresent())
            throw new PermissaoNaoEncontradaException();

        adminRepository.save(admin);
    }

    @Override
    public List<Admin> resgatarTodosAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public Admin realizarLogin(Admin admin) {
        return null;
    }

    @Override
    public void atualizarStatusAdmin(Admin admin) {

    }

    @Override
    public void removerAdmin(Admin admin) {

    }

    @Override
    public Admin resgatarAdminPeloId(int id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (!admin.isPresent())
            throw new AdminNaoEncontradoException();

        return admin.get();
    }
}
