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

    public Usuario login(Usuario usuario) {
        Usuario encontrado = usuarioRepository.findByEmail(usuario.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (encontrado.getSenha().equals(usuario.getSenha())) {
            return encontrado;
        }

        throw new RuntimeException("Senha incorreta");
    }
}