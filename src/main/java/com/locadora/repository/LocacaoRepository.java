package com.locadora.repository;

import com.locadora.model.Locacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacaoRepository extends JpaRepository <Locacao, Long> {

    // Aqui vai ter o retorno de todas as locações associadas a um determiando cliente e veículo
    List<Locacao> findByClienteId(Long clienteId);

    List<Locacao> findByVeiculoId(Long veiculoId);
}
