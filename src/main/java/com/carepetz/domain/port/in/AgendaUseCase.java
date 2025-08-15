package com.carepetz.domain.port.in;

import com.carepetz.domain.model.Agenda;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Porta de entrada para casos de uso de Agenda
 * Define os contratos para operações relacionadas a agendamentos
 */
public interface AgendaUseCase {

    Agenda criarAgenda(Agenda agenda);
    
    Optional<Agenda> buscarAgendaPorId(Long id);
    
    Optional<Agenda> buscarAgendaPorCodigo(String codigoAgenda);
    
    List<Agenda> listarTodasAgendas();
    
    List<Agenda> listarAgendasPorCliente(Long clienteId);
    
    List<Agenda> listarAgendasPorData(LocalDate data);
    
    List<Agenda> listarAgendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim);
    
    Agenda atualizarAgenda(Long id, Agenda agenda);
    
    void excluirAgenda(Long id);
    
    boolean existeAgenda(Long id);
    
    boolean existeAgendaPorCodigo(String codigoAgenda);
    
    boolean existeConflitoHorario(LocalDate data, String hora, Long agendaIdExcluir);
}
