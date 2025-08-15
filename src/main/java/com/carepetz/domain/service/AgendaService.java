package com.carepetz.domain.service;

import com.carepetz.domain.model.Agenda;
import com.carepetz.domain.port.in.AgendaUseCase;
import com.carepetz.domain.port.out.AgendaRepository;
import com.carepetz.domain.port.out.ClienteRepository;
import com.carepetz.domain.port.out.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Implementação dos casos de uso de Agenda
 * Contém a lógica de negócio para operações relacionadas a agendamentos
 */
@Service
public class AgendaService implements AgendaUseCase {

    private final AgendaRepository agendaRepository;
    private final ClienteRepository clienteRepository;
    private final ServicoRepository servicoRepository;

    @Autowired
    public AgendaService(AgendaRepository agendaRepository, 
                        ClienteRepository clienteRepository,
                        ServicoRepository servicoRepository) {
        this.agendaRepository = agendaRepository;
        this.clienteRepository = clienteRepository;
        this.servicoRepository = servicoRepository;
    }

    @Override
    public Agenda criarAgenda(Agenda agenda) {
        validarAgenda(agenda);
        validarDependencias(agenda);
        
        if (agendaRepository.existePorCodigo(agenda.getCodigoAgenda())) {
            throw new IllegalArgumentException("Agenda com código já existente: " + agenda.getCodigoAgenda());
        }
        
        if (existeConflitoHorario(agenda.getDataAgenda(), agenda.getHoraFormatada(), null)) {
            throw new IllegalArgumentException("Já existe um agendamento para este horário");
        }
        
        return agendaRepository.salvar(agenda);
    }

    @Override
    public Optional<Agenda> buscarAgendaPorId(Long id) {
        validarId(id);
        return agendaRepository.buscarPorId(id);
    }

    @Override
    public Optional<Agenda> buscarAgendaPorCodigo(String codigoAgenda) {
        validarCodigoAgenda(codigoAgenda);
        return agendaRepository.buscarPorCodigo(codigoAgenda);
    }

    @Override
    public List<Agenda> listarTodasAgendas() {
        return agendaRepository.buscarTodas();
    }

    @Override
    public List<Agenda> listarAgendasPorCliente(Long clienteId) {
        validarId(clienteId);
        return agendaRepository.buscarPorClienteId(clienteId);
    }

    @Override
    public List<Agenda> listarAgendasPorData(LocalDate data) {
        if (data == null) {
            throw new IllegalArgumentException("Data não pode ser nula");
        }
        return agendaRepository.buscarPorData(data);
    }

    @Override
    public List<Agenda> listarAgendasPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        if (dataInicio == null || dataFim == null) {
            throw new IllegalArgumentException("Datas de início e fim são obrigatórias");
        }
        if (dataInicio.isAfter(dataFim)) {
            throw new IllegalArgumentException("Data de início deve ser anterior à data de fim");
        }
        return agendaRepository.buscarPorPeriodo(dataInicio, dataFim);
    }

    @Override
    public Agenda atualizarAgenda(Long id, Agenda agenda) {
        validarId(id);
        validarAgenda(agenda);
        validarDependencias(agenda);
        
        if (!agendaRepository.existe(id)) {
            throw new IllegalArgumentException("Agenda não encontrada com ID: " + id);
        }
        
        if (existeConflitoHorario(agenda.getDataAgenda(), agenda.getHoraFormatada(), id)) {
            throw new IllegalArgumentException("Já existe um agendamento para este horário");
        }
        
        agenda.setId(id);
        return agendaRepository.salvar(agenda);
    }

    @Override
    public void excluirAgenda(Long id) {
        validarId(id);
        
        if (!agendaRepository.existe(id)) {
            throw new IllegalArgumentException("Agenda não encontrada com ID: " + id);
        }
        
        agendaRepository.excluir(id);
    }

    @Override
    public boolean existeAgenda(Long id) {
        validarId(id);
        return agendaRepository.existe(id);
    }

    @Override
    public boolean existeAgendaPorCodigo(String codigoAgenda) {
        validarCodigoAgenda(codigoAgenda);
        return agendaRepository.existePorCodigo(codigoAgenda);
    }

    @Override
    public boolean existeConflitoHorario(LocalDate data, String hora, Long agendaIdExcluir) {
        if (data == null || hora == null) {
            return false;
        }
        return agendaRepository.existeConflitoHorario(data, hora, agendaIdExcluir);
    }

    private void validarAgenda(Agenda agenda) {
        if (agenda == null) {
            throw new IllegalArgumentException("Agenda não pode ser nula");
        }
        
        if (!agenda.isAgendamentoValido()) {
            throw new IllegalArgumentException("Dados da agenda são inválidos");
        }
        
        if (agenda.getDataAgenda().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Não é possível agendar para datas passadas");
        }
    }

    private void validarDependencias(Agenda agenda) {
        if (agenda.getCliente() == null || agenda.getCliente().getId() == null) {
            throw new IllegalArgumentException("Cliente é obrigatório");
        }
        
        if (!clienteRepository.existe(agenda.getCliente().getId())) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        
        if (agenda.getServico() == null || agenda.getServico().getId() == null) {
            throw new IllegalArgumentException("Serviço é obrigatório");
        }
        
        if (!servicoRepository.existe(agenda.getServico().getId())) {
            throw new IllegalArgumentException("Serviço não encontrado");
        }
    }

    private void validarId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID deve ser um número positivo");
        }
    }

    private void validarCodigoAgenda(String codigoAgenda) {
        if (codigoAgenda == null || codigoAgenda.trim().isEmpty()) {
            throw new IllegalArgumentException("Código da agenda é obrigatório");
        }
    }
}
