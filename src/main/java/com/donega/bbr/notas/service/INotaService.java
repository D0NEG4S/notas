package com.donega.bbr.notas.service;

import com.donega.bbr.notas.model.Nota;

import java.util.List;

public interface INotaService {

    void save(Nota nota);

    boolean updateNota(Long id, Nota nota);

    boolean deleteNota(Long id);

    Nota getOne(Long id);

    List<Nota> getAll();
}
