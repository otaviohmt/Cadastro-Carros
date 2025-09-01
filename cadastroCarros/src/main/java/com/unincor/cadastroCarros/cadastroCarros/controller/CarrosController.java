package com.unincor.cadastroCarros.cadastroCarros.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unincor.cadastroCarros.cadastroCarros.model.domain.Carros;

@RestController
@RequestMapping("/carros")
public class CarrosController {

    private List<Carros> Carros = new ArrayList<>();

    @GetMapping
    public String inicio() {
        return "Cadastro de Carros";
    }

    @PostMapping
    public ResponseEntity<Carros> cadastrarCarro(@RequestBody Carros carro) {
        carro.setId(Carros.size() + 1);
        Carros.add(carro);
        return new ResponseEntity<>(carro, HttpStatus.CREATED);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Carros>> listarCarros() {
        return ResponseEntity.ok(Carros);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarCarro(@PathVariable Integer id) {
        boolean removed = Carros.removeIf(c -> c.getId().equals(id));

        if (removed) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<Carros> buscarCarroPorId(@PathVariable Integer id) {
        Optional<Carros> carro = Carros.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst();
        return carro.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/ano/{ano}")
    public List<Carros> buscarCarroPorAno(@PathVariable Integer ano) {
        return Carros.stream()
                .filter(c -> c.getAno().equals(ano))
                .toList();
    }

    @GetMapping("/novos")
    public List<Carros> listarCarrosNovos() {
        int anoAtual = java.time.Year.now().getValue();
        return Carros.stream()
                .filter(c -> (anoAtual - c.getAno()) <= 5)
                .toList();
    }
}