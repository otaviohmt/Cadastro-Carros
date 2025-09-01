package com.unincor.cadastroCarros.cadastroCarros.model.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Carros {
    private Integer id;
    private String modelo;
    private String marca;
    private Integer ano;
    private Double potencia;
    private Integer cavalos;
    private boolean hibrido;
}
