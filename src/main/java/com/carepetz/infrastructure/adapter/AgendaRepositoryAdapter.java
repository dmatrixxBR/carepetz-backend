package com.carepetz.infrastructure.adapter;

import com.carepetz.domain.model.Agenda;
import com.carepetz.domain.port.out.AgendaRepository;
import com.carepetz.infrastructure.entity.AgendaEntity;
import com.carepetz.infrastructure.mapper.AgendaMapper;
import com.carepetz.infrastructure.repository.AgendaJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Adaptador de repositório para Agenda
 * Implementa a porta de saída usando JPA
 */
@Repository
public class AgendaRepositoryAdapter implements AgendaRepository {

    private final AgendaJpaRepository agendaJpaRepository;

    @Autowired
    public AgendaRepositoryAdapter(AgendaJpaRepository agendaJpaRepository) {
        this.agendaJpaRepository = agendaJpaRepository;
    }

    @Override
    public Agenda salvar(Agenda agenda) {
        AgendaEntity entity = AgendaMapper.toEntity(agenda);
        AgendaEntity savedEntity = agendaJpaRepository.save(entity);
        return AgendaMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Agenda> buscarPorId(Long id) {
        return agendaJpaRepository.findById(id)
                .map(AgendaMapper::toDomain);
    }

    @Override
    public Optional<Agenda> buscarPorCodigo(String codigoAgenda) {
        return agendaJpaRepository.findByCodigoAgenda(codigoAgenda)
                .map(AgendaMapper::toDomain);
    }

    @Override
    public List<Agenda> buscarTodas() {
        return agendaJpaRepository.findAll()
                .stream()
                .map(AgendaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Agenda> buscarPorClienteId(Long clienteId) {
        return agendaJpaRepository.findByClienteId(clienteId)
                .stream()
                .map(AgendaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Agenda> buscarPorData(LocalDate data) {
        return agendaJpaRepository.findByDataAgenda(data)
                .stream()
                .map(AgendaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Agenda> buscarPorPeriodo(LocalDate dataInicio, LocalDate dataFim) {
        return agendaJpaRepository.findByDataAgendaBetween(dataInicio, dataFim)
                .stream()
                .map(AgendaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void excluir(Long id) {
        agendaJpaRepository.deleteById(id);
    }

    @Override
    public boolean existe(Long id) {
        return agendaJpaRepository.existsById(id);
    }

    @Override
    public boolean existePorCodigo(String codigoAgenda) {
        return agendaJpaRepository.existsByCodigoAgenda(codigoAgenda);
    }

    @Override
    public boolean existeConflitoHorario(LocalDate data, String hora, Long agendaIdExcluir) {
        try {
            LocalTime horaConvertida = LocalTime.parse(hora, DateTimeFormatter.ofPattern("HH:mm"));
            return agendaJpaRepository.existeConflitoHorario(data, horaConvertida, agendaIdExcluir);
        } catch (Exception e) {
            return false;
        }
    }
}
