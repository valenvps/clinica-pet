package br.com.valentina.clinicapet.controller;

import br.com.valentina.clinicapet.model.Pet;
import br.com.valentina.clinicapet.service.PetService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets")
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    // LISTAR
    @GetMapping
    public List<Pet> listar() {
        return petService.listar();
    }

    // BUSCAR POR ID
    @GetMapping("/{id}")
    public Pet buscar(@PathVariable Long id) {
        return petService.buscarPorId(id);
    }

    // CADASTRAR
    @PostMapping
    public Pet cadastrar(@RequestBody Pet pet) {
        return petService.salvar(pet);
    }

    // DELETAR
    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        petService.deletar(id);
    }
}