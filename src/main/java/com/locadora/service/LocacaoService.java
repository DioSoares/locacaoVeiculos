package com.locadora.service;


import com.locadora.model.Cliente;
import com.locadora.model.Locacao;
import com.locadora.model.Veiculo;

import com.locadora.repository.ClienteRepository;
import com.locadora.repository.LocacaoRepository;
import com.locadora.repository.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;


@Service
public class LocacaoService {

    @Autowired
    private LocacaoRepository locacaoRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Locacao> listarTodas() {
        return locacaoRepository.findAll();
    }

    public List<Locacao> buscarLocacoesPorCliente(Long clienteId) {
        return locacaoRepository.findByClienteId(clienteId);
    }

    public List<Locacao> buscarLocacoesPorVeiculo(Long veiculoId) {
        return locacaoRepository.findByVeiculoId(veiculoId);
    }

    public Locacao salvar(Locacao locacao) {
        Optional<Cliente> cliente = clienteRepository.findById(locacao.getCliente().getId());
        Optional<Veiculo> veiculo = veiculoRepository.findById(locacao.getVeiculo().getId());

        if (cliente.isPresent() && veiculo.isPresent()) {
            Veiculo veiculoEncontrado = veiculo.get();

            // Verifica se o valorDiaria do veículo está preenchido
            if (veiculoEncontrado.getValorDiaria() == null) {
                throw new RuntimeException("O veículo não possui valor de diária definido.");
            }

            Cliente clienteEncontrado = cliente.get();

            // Calcula a quantidade de dias
            long dias = ChronoUnit.DAYS.between(locacao.getDataInicio(), locacao.getDataFim());

            // Se a data de início e fim forem iguais, considera 1 dia
            if (dias == 0) {
                dias = 1;
            }

            // Calcula o valor total da locação
            double valorTotal = dias * veiculoEncontrado.getValorDiaria();
            locacao.setValorTotal(valorTotal);

            locacao.setCliente(clienteEncontrado);
            locacao.setVeiculo(veiculoEncontrado);

            return locacaoRepository.save(locacao);
        } else {
            throw new RuntimeException("Cliente ou Veículo não encontrado!");
        }
    }
}