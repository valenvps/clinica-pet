package br.com.valentina.clinicapet.service;

import br.com.valentina.clinicapet.model.Pet;
import br.com.valentina.clinicapet.repository.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    // LISTAR
    public List<Pet> listar() {
        return petRepository.findAll();
    }

    // BUSCAR POR ID
    public Pet buscarPorId(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        return pet.orElse(null);
    }

    // SALVAR (cadastrar)
    public Pet salvar(Pet pet) {
        return petRepository.save(pet);
    }

    // DELETAR
    public void deletar(Long id) {
        petRepository.deleteById(id);
    }
}