package com.bandtec.br.finoban.service.admin;

import com.bandtec.br.finoban.dominio.Login;
import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.entidades.Permissoes;
import com.bandtec.br.finoban.dominio.entidades.Usuario;
import com.bandtec.br.finoban.dominio.exceptions.*;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLogin;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLoginAdmin;
import com.bandtec.br.finoban.infraestrutura.criptografia.Criptografia;
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
    private LoginAdminService loginAdminService;

    @Override
    public void cadastrarAdmin(Admin admin) {

        if (adminRepository.existsByEmail(admin.getEmail()))
                throw new AdminJaCadastradoException();

        Optional<Permissoes> permissoes = permissoesRepository.findById(admin.getPermissoes().getIdPermissao());
        if (!permissoes.isPresent())
            throw new PermissaoNaoEncontradaException();

        adminRepository.save(admin);
        atualizarDados(admin);
    }

    @Override
    public List<Admin> resgatarTodosAdmins() {
        return adminRepository.findAll();
    }

    @Override
    public RespostaLogin realizarLogin(Login login) {
        Admin admin = adminRepository.findAllByEmail(login.getEmail());
        if (admin == null)
            throw new AdminNaoEncontradoException();

        String senhaCriptografada = Criptografia.criptografarComHashingMaisSaltMaisId(login.getSenha(),
                admin.getIdAmin());

        if (!admin.getSenha().equals(senhaCriptografada))
            throw new SenhaIncorretaException();

        return loginAdminService.logarUsuario(admin);
    }

    @Override
    public void realizarLogoff(Login logoff) {
        Admin usuario = adminRepository.findAllByEmail(logoff.getEmail());
        if (usuario == null)
            throw new EmailNaoEncontradoException();

        loginAdminService.realizarLogoffUsuario(usuario);
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

    @Override
    public Admin atualizarDados(Admin admin) {
        Admin verificarAdmin = adminRepository.findAllByEmail(admin.getEmail());
        if (verificarAdmin == null)
            throw new EmailNaoEncontradoException();

        String senhaCriptografada = Criptografia.criptografarComHashingMaisSaltMaisId(verificarAdmin.getSenha(), verificarAdmin.getIdAmin());
        verificarAdmin.setSenha(senhaCriptografada);
        adminRepository.redefinirSenhaUsuario(senhaCriptografada, admin.getIdAmin());
        return verificarAdmin;
    }
}
