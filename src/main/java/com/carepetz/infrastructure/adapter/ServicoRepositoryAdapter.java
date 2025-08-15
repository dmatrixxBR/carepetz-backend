package com.carepetz.infrastructure.adapter;

import com.carepetz.domain.model.Servico;
import com.carepetz.domain.port.out.ServicoRepository;
import com.carepetz.infrastructure.entity.ServicoEntity;
import com.carepetz.infrastructure.mapper.ServicoMapper;
import com.carepetz.infrastructure.repository.ServicoJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de repositório para Servico
 * Implementa a porta de saída usando JPA
 */
@Repository
public class ServicoRepositoryAdapter implements ServicoRepository {

    private final ServicoJpaRepository servicoJpaRepository;

    @Autowired
    public ServicoRepositoryAdapter(ServicoJpaRepository servicoJpaRepository) {
        this.servicoJpaRepository = servicoJpaRepository;
    }

    @Override
    public Servico salvar(Servico servico) {
        ServicoEntity entity = ServicoMapper.toEntity(servico);
        ServicoEntity savedEntity = servicoJpaRepository.save(entity);
        return ServicoMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Servico> buscarPorId(Long id) {
        return servicoJpaRepository.findById(id)
                .map(ServicoMapper::toDomain);
    }

    @Override
    public Optional<Servico> buscarPorCodigo(String codigoServico) {
        return servicoJpaRepository.findByCodigoServico(codigoServico)
                .map(ServicoMapper::toDomain);
    }

    @Override
    public List<Servico> buscarTodos() {
        return servicoJpaRepository.findAll()
                .stream()
                .map(ServicoMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void excluir(Long id) {
        servicoJpaRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return servicoJpaRepository.existsById(id);
    }

    @Override
    public boolean existePorCodigo(String codigoServico) {
        return servicoJpaRepository.existsByCodigoServico(codigoServico);
    }
}
