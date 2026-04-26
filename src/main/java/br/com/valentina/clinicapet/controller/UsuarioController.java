package br.com.valentina.clinicapet.controller;

import br.com.valentina.clinicapet.model.Usuario;
import br.com.valentina.clinicapet.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public Usuario cadastrar(@RequestBody Usuario usuario) {
        return usuarioService.cadastrar(usuario);
    }

    @PostMapping("/login")
    public Usuario login(@RequestBody Usuario usuario) {
        return usuarioService.login(usuario);
    }
}
