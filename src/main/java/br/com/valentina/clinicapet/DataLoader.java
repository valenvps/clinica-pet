package br.com.valentina.clinicapet;

import br.com.valentina.clinicapet.model.Usuario;
import br.com.valentina.clinicapet.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByEmail("professor@pet.com").isEmpty()) {
            Usuario usuario = new Usuario();
            usuario.setEmail("professor@pet.com");
            usuario.setSenha("123456");
            usuarioRepository.save(usuario);
        }
    }
}
