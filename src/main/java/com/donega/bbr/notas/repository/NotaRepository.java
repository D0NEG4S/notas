package com.donega.bbr.notas.repository;

import com.donega.bbr.notas.model.Nota;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotaRepository extends CrudRepository<Nota, Long> {
    List<Nota> findAll();
}
