package com.carepetz.infrastructure.repository;

import com.carepetz.infrastructure.entity.ServicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório JPA para ServicoEntity
 */
@Repository
public interface ServicoJpaRepository extends JpaRepository<ServicoEntity, Long> {
    
    Optional<ServicoEntity> findByCodigoServico(String codigoServico);
    
    boolean existsByCodigoServico(String codigoServico);
}
