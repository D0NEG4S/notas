package com.donega.bbr.notas.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
public class Nota {

    @Id
    @NotNull
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    private double valor;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Nota{" +
                "id=" + id +
                ", data=" + data +
                ", valor=" + valor +
                ", description='" + description + '\'' +
                '}';
    }
}
