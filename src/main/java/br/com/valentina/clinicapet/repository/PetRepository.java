package br.com.valentina.clinicapet.repository;

import br.com.valentina.clinicapet.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
}