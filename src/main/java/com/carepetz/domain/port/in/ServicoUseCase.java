package com.carepetz.domain.port.in;

import com.carepetz.domain.model.Servico;
import java.util.List;
import java.util.Optional;

/**
 * Porta de entrada para casos de uso de Serviço
 * Define os contratos para operações relacionadas a serviços
 */
public interface ServicoUseCase {

    Servico criarServico(Servico servico);
    
    Optional<Servico> buscarServicoPorId(Long id);
    
    Optional<Servico> buscarServicoPorCodigo(String codigoServico);
    
    List<Servico> listarTodosServicos();
    
    Servico atualizarServico(Long id, Servico servico);
    
    void excluirServico(Long id);
    
    boolean existeServico(Long id);
    
    boolean existeServicoPorCodigo(String codigoServico);
}
