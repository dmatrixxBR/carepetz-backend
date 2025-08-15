package com.carepetz.domain.port.in;

import com.carepetz.domain.model.Cliente;
import java.util.List;
import java.util.Optional;

/**
 * Porta de entrada para casos de uso de Cliente
 * Define os contratos para operações relacionadas a clientes
 */
public interface ClienteUseCase {

    Cliente criarCliente(Cliente cliente);
    
    Optional<Cliente> buscarClientePorId(Long id);
    
    Optional<Cliente> buscarClientePorCodigo(String codigoCliente);
    
    List<Cliente> listarTodosClientes();
    
    Cliente atualizarCliente(Long id, Cliente cliente);
    
    void excluirCliente(Long id);
    
    boolean existeCliente(Long id);
    
    boolean existeClientePorCodigo(String codigoCliente);
}
