package com.carepetz.infrastructure.repository;

import com.carepetz.infrastructure.entity.AgendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

/**
 * Repositório JPA para AgendaEntity
 */
@Repository
public interface AgendaJpaRepository extends JpaRepository<AgendaEntity, Long> {
    
    Optional<AgendaEntity> findByCodigoAgenda(String codigoAgenda);
    
    boolean existsByCodigoAgenda(String codigoAgenda);
    
    List<AgendaEntity> findByClienteId(Long clienteId);
    
    List<AgendaEntity> findByDataAgenda(LocalDate dataAgenda);
    
    List<AgendaEntity> findByDataAgendaBetween(LocalDate dataInicio, LocalDate dataFim);
    
    @Query("SELECT COUNT(a) > 0 FROM AgendaEntity a " +
           "WHERE a.dataAgenda = :data " +
           "AND a.horaAgenda = :hora " +
           "AND (:agendaId IS NULL OR a.id <> :agendaId)")
    boolean existeConflitoHorario(@Param("data") LocalDate data, 
                                 @Param("hora") LocalTime hora, 
                                 @Param("agendaId") Long agendaId);
}
