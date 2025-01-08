package com.locadora.controller;

import com.locadora.model.Locacao;
import com.locadora.service.LocacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locacoes")
public class LocacaoController {

    @Autowired
    private LocacaoService locacaoService;

    @GetMapping
    public List<Locacao> listarTodas() {
        return locacaoService.listarTodas();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<Locacao>> listarPorCliente(@PathVariable Long clienteId) {
        List<Locacao> locacoes = locacaoService.buscarLocacoesPorCliente(clienteId);
        return ResponseEntity.ok(locacoes);
    }

    @GetMapping("/veiculo/{veiculoId}")
    public ResponseEntity<List<Locacao>> listarPorVeiculo(@PathVariable Long veiculoId) {
        List<Locacao> locacoes = locacaoService.buscarLocacoesPorVeiculo(veiculoId);
        return ResponseEntity.ok(locacoes);
    }

    @PostMapping
    public Locacao salvar(@RequestBody Locacao locacao) {
        return locacaoService.salvar(locacao);
    }
}
