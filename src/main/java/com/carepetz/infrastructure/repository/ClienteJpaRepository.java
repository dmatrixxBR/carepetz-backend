package com.carepetz.infrastructure.repository;

import com.carepetz.infrastructure.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositório JPA para ClienteEntity
 */
@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long> {
    
    Optional<ClienteEntity> findByCodigoCliente(String codigoCliente);
    
    boolean existsByCodigoCliente(String codigoCliente);
}
