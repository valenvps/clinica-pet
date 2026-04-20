package br.com.valentina.clinicapet.service;

import br.com.valentina.clinicapet.model.Exame;
import br.com.valentina.clinicapet.repository.ExameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExameService {

    private final ExameRepository exameRepository;

    public ExameService(ExameRepository exameRepository) {
        this.exameRepository = exameRepository;
    }

    public List<Exame> listar() {
        return exameRepository.findAll();
    }

    public Exame buscar(Long id) {
        return exameRepository.findById(id).orElse(null);
    }

    public Exame salvar(Exame exame) {
        return exameRepository.save(exame);
    }
}