package com.carepetz.domain.port.out;

import com.carepetz.domain.model.Cliente;
import java.util.List;
import java.util.Optional;

/**
 * Porta de saída para persistência de Cliente
 * Define os contratos para operações de persistência
 */
public interface ClienteRepository {

    Cliente salvar(Cliente cliente);
    
    Optional<Cliente> buscarPorId(Long id);
    
    Optional<Cliente> buscarPorCodigo(String codigoCliente);
    
    List<Cliente> buscarTodos();
    
    void excluir(Long id);
    
    boolean existe(Long id);
    
    boolean existePorCodigo(String codigoCliente);
}
