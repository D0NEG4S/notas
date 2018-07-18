package com.donega.bbr.notas.service;

import com.donega.bbr.notas.model.Nota;
import com.donega.bbr.notas.repository.NotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaService implements INotaService {

    @Autowired
    NotaRepository repository;

    @Override
    public void save(Nota nota) {
        if (!repository.existsById(nota.getId())) {
            repository.save(nota);
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean updateNota(Long id, Nota nota) {
        Nota notaUpdate = repository.findById(id).orElse(null);

        if (notaUpdate != null) {
            notaUpdate.setValor(nota.getValor());
            notaUpdate.setDescription(nota.getDescription());
            repository.save(notaUpdate);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteNota(Long id) {
        Nota notaDelete = repository.findById(id).orElse(null);

        if (notaDelete != null) {
            repository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Nota getOne(Long id) {
        return repository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public List<Nota> getAll() {
        return repository.findAll();
    }
}
