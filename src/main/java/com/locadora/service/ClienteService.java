package com.locadora.service;

import com.locadora.model.Cliente;
import com.locadora.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos () {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente salvar (Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deletar (Long id) {
        clienteRepository.deleteById(id);
    }
}