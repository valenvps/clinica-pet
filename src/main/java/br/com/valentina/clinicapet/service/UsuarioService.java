package br.com.valentina.clinicapet.service;

import br.com.valentina.clinicapet.model.Usuario;
import br.com.valentina.clinicapet.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Usuario cadastrar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public String login(Usuario usuario) {
        return usuarioRepository.findByEmail(usuario.getEmail())
                .map(u -> {
                    if (u.getSenha().equals(usuario.getSenha())) {
                        return "Login realizado com sucesso!";
                    } else {
                        return "Senha incorreta!";
                    }
                })
                .orElse("Usuário não encontrado!");
    }
}