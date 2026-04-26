package br.com.valentina.clinicapet.controller;

import br.com.valentina.clinicapet.model.Exame;
import br.com.valentina.clinicapet.service.ExameService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/exames")
public class ExameController {

    private final ExameService exameService;

    public ExameController(ExameService exameService) {
        this.exameService = exameService;
    }

    @GetMapping
    public List<Exame> listar() {
        return exameService.listar();
    }

    @GetMapping("/{id}")
    public Exame buscar(@PathVariable Long id) {
        return exameService.buscar(id);
    }

    @PostMapping
    public Exame cadastrar(@RequestBody Exame exame) {
        return exameService.salvar(exame);
    }
}