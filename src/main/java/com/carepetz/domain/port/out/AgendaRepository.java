package com.carepetz.domain.port.out;

import com.carepetz.domain.model.Agenda;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Porta de saída para persistência de Agenda
 * Define os contratos para operações de persistência
 */
public interface AgendaRepository {

    Agenda salvar(Agenda agenda);
    
    Optional<Agenda> buscarPorId(Long id);
    
    Optional<Agenda> buscarPorCodigo(String codigoAgenda);
    
    List<Agenda> buscarTodas();
    
    List<Agenda> buscarPorClienteId(Long clienteId);
    
    List<Agenda> buscarPorData(LocalDate data);
    
    List<Agenda> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
    
    void excluir(Long id);
    
    boolean existe(Long id);
    
    boolean existePorCodigo(String codigoAgenda);
    
    boolean existeConflitoHorario(LocalDate data, String hora, Long agendaIdExcluir);
}
