package com.carepetz.domain.port.out;

import com.carepetz.domain.model.Servico;
import java.util.List;
import java.util.Optional;

/**
 * Porta de saída para persistência de Serviço
 * Define os contratos para operações de persistência
 */
public interface ServicoRepository {

    Servico salvar(Servico servico);
    
    Optional<Servico> buscarPorId(Long id);
    
    Optional<Servico> buscarPorCodigo(String codigoServico);
    
    List<Servico> buscarTodos();
    
    void excluir(Long id);
    
    boolean existe(Long id);
    
    boolean existePorCodigo(String codigoServico);
}
