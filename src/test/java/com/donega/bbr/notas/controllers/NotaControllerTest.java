package com.donega.bbr.notas.controllers;

import com.donega.bbr.notas.model.Nota;
import com.donega.bbr.notas.service.NotaService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotaControllerTest {

    private static final Logger LOG = LoggerFactory.getLogger(NotaControllerTest.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    NotaController controller;

    @Autowired
    NotaService service;

    @Before
    public void init(){

    }

    @Test
    public void save() {
        LOG.info("Testing save");
        ResponseEntity<String> response = this.restTemplate.postForEntity("/notas", getNota(), String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        service.deleteNota(getNota().getId());
    }


    @Test(expected = IllegalArgumentException.class)
    public void delete() {
        LOG.info("Testing delete");
        service.save(getNota());
        this.restTemplate.delete("/notas/" + getNota().getId());
        service.getOne(getNota().getId());
    }

    @Test
    public void getOne() {
        service.save(getNota());
        ResponseEntity<Nota> response = this.restTemplate.getForEntity("/notas/"+ getNota().getId(), Nota.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getId()).isEqualTo(getNota().getId());
        service.deleteNota(getNota().getId());
    }

    @Test
    public void getAll() {
        ResponseEntity<Object[]> response = this.restTemplate.getForEntity("/notas", Object[].class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    public Nota getNota(){
        Nota nota = new Nota();
        nota.setId(10000L);
        nota.setData(LocalDate.now());
        nota.setValor(456.7);
        nota.setDescription("test");

        return nota;
    }
}