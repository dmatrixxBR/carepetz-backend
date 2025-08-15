package com.carepetz.infrastructure.adapter;

import com.carepetz.domain.model.Cliente;
import com.carepetz.domain.port.out.ClienteRepository;
import com.carepetz.infrastructure.entity.ClienteEntity;
import com.carepetz.infrastructure.mapper.ClienteMapper;
import com.carepetz.infrastructure.repository.ClienteJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de repositório para Cliente
 * Implementa a porta de saída usando JPA
 */
@Repository
public class ClienteRepositoryAdapter implements ClienteRepository {

    private final ClienteJpaRepository clienteJpaRepository;

    @Autowired
    public ClienteRepositoryAdapter(ClienteJpaRepository clienteJpaRepository) {
        this.clienteJpaRepository = clienteJpaRepository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity entity = ClienteMapper.toEntity(cliente);
        ClienteEntity savedEntity = clienteJpaRepository.save(entity);
        return ClienteMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteJpaRepository.findById(id)
                .map(ClienteMapper::toDomain);
    }

    @Override
    public Optional<Cliente> buscarPorCodigo(String codigoCliente) {
        return clienteJpaRepository.findByCodigoCliente(codigoCliente)
                .map(ClienteMapper::toDomain);
    }

    @Override
    public List<Cliente> buscarTodos() {
        return clienteJpaRepository.findAll()
                .stream()
                .map(ClienteMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void excluir(Long id) {
        clienteJpaRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return clienteJpaRepository.existsById(id);
    }

    @Override
    public boolean existePorCodigo(String codigoCliente) {
        return clienteJpaRepository.existsByCodigoCliente(codigoCliente);
    }
}
