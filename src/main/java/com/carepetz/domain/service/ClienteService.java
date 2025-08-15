package com.carepetz.domain.service;

import com.carepetz.domain.model.Cliente;
import com.carepetz.domain.port.in.ClienteUseCase;
import com.carepetz.domain.port.out.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementação dos casos de uso de Cliente
 * Contém a lógica de negócio para operações relacionadas a clientes
 */
@Service
public class ClienteService implements ClienteUseCase {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente criarCliente(Cliente cliente) {
        validarCliente(cliente);
        
        if (clienteRepository.existePorCodigo(cliente.getCodigoCliente())) {
            throw new IllegalArgumentException("Cliente com código já existente: " + cliente.getCodigoCliente());
        }
        
        return clienteRepository.salvar(cliente);
    }

    @Override
    public Optional<Cliente> buscarClientePorId(Long id) {
        validarId(id);
        return clienteRepository.buscarPorId(id);
    }

    @Override
    public Optional<Cliente> buscarClientePorCodigo(String codigoCliente) {
        validarCodigoCliente(codigoCliente);
        return clienteRepository.buscarPorCodigo(codigoCliente);
    }

    @Override
    public List<Cliente> listarTodosClientes() {
        return clienteRepository.buscarTodos();
    }

    @Override
    public Cliente atualizarCliente(Long id, Cliente cliente) {
        validarId(id);
        validarCliente(cliente);
        
        if (!clienteRepository.existe(id)) {
            throw new IllegalArgumentException("Cliente não encontrado com ID: " + id);
        }
        
        cliente.setId(id);
        return clienteRepository.salvar(cliente);
    }

    @Override
    public void excluirCliente(Long id) {
        validarId(id);
        
        if (!clienteRepository.existe(id)) {
            throw new IllegalArgumentException("Cliente não encontrado com ID: " + id);
        }
        
        clienteRepository.excluir(id);
    }

    @Override
    public boolean existeCliente(Long id) {
        validarId(id);
        return clienteRepository.existe(id);
    }

    @Override
    public boolean existeClientePorCodigo(String codigoCliente) {
        validarCodigoCliente(codigoCliente);
        return clienteRepository.existePorCodigo(codigoCliente);
    }

    private void validarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }
        
        if (cliente.getNomeCliente() == null || cliente.getNomeCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente é obrigatório");
        }
        
        if (cliente.getCelularCliente() == null || cliente.getCelularCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("Celular do cliente é obrigatório");
        }
        
        if (!cliente.isValidCelular()) {
            throw new IllegalArgumentException("Celular do cliente inválido");
        }
        
        if (cliente.getEmailCliente() == null || cliente.getEmailCliente().trim().isEmpty()) {
            throw new IllegalArgumentException("Email do cliente é obrigatório");
        }
        
        if (!cliente.isValidEmail()) {
            throw new IllegalArgumentException("Email do cliente inválido");
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID deve ser um número positivo");
        }
    }

    private void validarCodigoCliente(String codigoCliente) {
        if (codigoCliente == null || codigoCliente.trim().isEmpty()) {
            throw new IllegalArgumentException("Código do cliente é obrigatório");
        }
    }
}
