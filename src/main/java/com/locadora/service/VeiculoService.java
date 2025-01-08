package com.locadora.service;

import com.locadora.model.Veiculo;
import com.locadora.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Veiculo salvar (Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public void deletar(Long id) {
        veiculoRepository.deleteById(id);
    }
}