package com.carepetz.domain.service;

import com.carepetz.domain.model.Servico;
import com.carepetz.domain.port.in.ServicoUseCase;
import com.carepetz.domain.port.out.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Implementação dos casos de uso de Serviço
 * Contém a lógica de negócio para operações relacionadas a serviços
 */
@Service
public class ServicoService implements ServicoUseCase {

    private final ServicoRepository servicoRepository;

    @Autowired
    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Override
    public Servico criarServico(Servico servico) {
        validarServico(servico);
        
        if (servicoRepository.existePorCodigo(servico.getCodigoServico())) {
            throw new IllegalArgumentException("Serviço com código já existente: " + servico.getCodigoServico());
        }
        
        return servicoRepository.salvar(servico);
    }

    @Override
    public Optional<Servico> buscarServicoPorId(Long id) {
        validarId(id);
        return servicoRepository.buscarPorId(id);
    }

    @Override
    public Optional<Servico> buscarServicoPorCodigo(String codigoServico) {
        validarCodigoServico(codigoServico);
        return servicoRepository.buscarPorCodigo(codigoServico);
    }

    @Override
    public List<Servico> listarTodosServicos() {
        return servicoRepository.buscarTodos();
    }

    @Override
    public Servico atualizarServico(Long id, Servico servico) {
        validarId(id);
        validarServico(servico);
        
        if (!servicoRepository.existe(id)) {
            throw new IllegalArgumentException("Serviço não encontrado com ID: " + id);
        }
        
        servico.setId(id);
        return servicoRepository.salvar(servico);
    }

    @Override
    public void excluirServico(Long id) {
        validarId(id);
        
        if (!servicoRepository.existe(id)) {
            throw new IllegalArgumentException("Serviço não encontrado com ID: " + id);
        }
        
        servicoRepository.excluir(id);
    }

    @Override
    public boolean existeServico(Long id) {
        validarId(id);
        return servicoRepository.existe(id);
    }

    @Override
    public boolean existeServicoPorCodigo(String codigoServico) {
        validarCodigoServico(codigoServico);
        return servicoRepository.existePorCodigo(codigoServico);
    }

    private void validarServico(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço não pode ser nulo");
        }
        
        if (!servico.isDescricaoValida()) {
            throw new IllegalArgumentException("Descrição do serviço é obrigatória");
        }
        
        if (!servico.isValorValido()) {
            throw new IllegalArgumentException("Valor do serviço deve ser maior que zero");
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID deve ser um número positivo");
        }
    }

    private void validarCodigoServico(String codigoServico) {
        if (codigoServico == null || codigoServico.trim().isEmpty()) {
            throw new IllegalArgumentException("Código do serviço é obrigatório");
        }
    }
}
