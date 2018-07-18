package com.donega.bbr.notas.controllers;

import com.donega.bbr.notas.model.Nota;
import com.donega.bbr.notas.service.NotaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/notas")
public class NotaController {

    private static final Logger LOG = LoggerFactory.getLogger(NotaController.class);

    @Autowired
    NotaService service;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> save(@RequestBody Nota nota) {
        LOG.info("Inserindo nota = {}", nota.toString());

        try {
            service.save(nota);
            return new ResponseEntity<String>("Sua nota foi cadastrada com sucesso", HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return new ResponseEntity<String>("Essa nota já foi cadastrada anteriormente", HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<String>("Ops, falha ao cadastrar sua nota", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value="/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Nota nota) {
        LOG.info("Atualizando nota com id = {}", id);

        if(service.updateNota(id, nota)){
            return new ResponseEntity<String>(HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<String> update(@PathVariable Long id) {
        LOG.info("Deletando nota com id = {}", id);

        if(service.deleteNota(id)){
            return new ResponseEntity<String>("Sua nota foi excluída com sucesso " + id, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Nota>> getOne() {
        LOG.info("Listando todas as notas");

        List<Nota> notas = service.getAll();

        if(!notas.isEmpty()){
            return new ResponseEntity<List<Nota>>(notas, HttpStatus.OK);
        } else {
            return new ResponseEntity<List<Nota>>(HttpStatus.NO_CONTENT);
        }
    }
}
