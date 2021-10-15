package com.bandtec.br.finoban.service.admin;

import com.bandtec.br.finoban.dominio.Login;
import com.bandtec.br.finoban.dominio.entidades.Admin;
import com.bandtec.br.finoban.dominio.exceptions.*;
import com.bandtec.br.finoban.dominio.resposta.respostasLogin.RespostaLogin;
import com.bandtec.br.finoban.infraestrutura.criptografia.Criptografia;
import com.bandtec.br.finoban.repository.AdminInterface;
import com.bandtec.br.finoban.repository.database.AdminRepository;
import com.bandtec.br.finoban.repository.database.PermissoesRepository;
import com.bandtec.br.finoban.repository.database.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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

        var permissoes = permissoesRepository.findById(admin.getPermissoes().getIdPermissao());
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
        var admin = adminRepository.findAllByEmail(login.getEmail());
        if (admin == null)
            throw new AdminNaoEncontradoException();

        var senhaCriptografada = Criptografia.criptografarComHashingMaisSaltMaisId(login.getSenha(),
                admin.getIdAmin());

        if (!admin.getSenha().equals(senhaCriptografada))
            throw new SenhaIncorretaException();

        return loginAdminService.logarUsuario(admin);
    }

    @Override
    public void realizarLogoff(Login logoff) {
        var usuario = adminRepository.findAllByEmail(logoff.getEmail());
        if (usuario == null)
            throw new AdminNaoEncontradoException();

        loginAdminService.realizarLogoffUsuario(usuario);
    }

    @Override
    public Admin atualizarStatusAdmin(Admin admin) {
        var adminVerificar = adminRepository.findAllByEmail(admin.getEmail());
        if (adminVerificar == null)
            throw new AdminNaoEncontradoException();

        adminRepository.atualizarStatus(admin.getPermissoes().isStatus(), adminVerificar.getIdAmin());
        return adminRepository.findById(adminVerificar.getIdAmin()).get();
    }

    @Override
    public void removerAdminPorId(int idAdmin) {
        var admin = adminRepository.findById(idAdmin);
        if (!admin.isPresent())
            throw new AdminNaoEncontradoException();

//        var permissao = permissoesRepository
//                .findById(admin.get().getPermissoes().getIdPermissao());

//        if (!permissao.isPresent())
//            throw new PermissaoNaoEncontradaException();

        adminRepository.deleteById(admin.get().getIdAmin());
    }

    @Override
    public Admin resgatarAdminPeloId(int id) {
        var admin = adminRepository.findById(id);
        if (!admin.isPresent())
            throw new AdminNaoEncontradoException();

        return admin.get();
    }

    @Override
    public Admin atualizarDados(Admin admin) {
        var verificarAdmin = adminRepository.findAllByEmail(admin.getEmail());
        if (verificarAdmin == null)
            throw new EmailNaoEncontradoException();

        var senhaCriptografada = Criptografia.criptografarComHashingMaisSaltMaisId(verificarAdmin.getSenha(), verificarAdmin.getIdAmin());
        verificarAdmin.setSenha(senhaCriptografada);
        adminRepository.redefinirSenhaUsuario(senhaCriptografada, admin.getIdAmin());
        return verificarAdmin;
    }
}
