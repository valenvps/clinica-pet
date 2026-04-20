package br.com.valentina.clinicapet.repository;

import br.com.valentina.clinicapet.model.Exame;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExameRepository extends JpaRepository<Exame, Long> {
}