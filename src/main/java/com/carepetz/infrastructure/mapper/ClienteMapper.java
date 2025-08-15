package com.carepetz.infrastructure.mapper;

import com.carepetz.domain.model.Cliente;
import com.carepetz.infrastructure.entity.ClienteEntity;

/**
 * Mapper para conversão entre Cliente e ClienteEntity
 * Aplicando princípios do Clean Code para conversões
 */
public class ClienteMapper {

    public static Cliente toDomain(ClienteEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Cliente cliente = new Cliente();
        cliente.setId(entity.getId());
        cliente.setCodigoCliente(entity.getCodigoCliente());
        cliente.setNomeCliente(entity.getNomeCliente());
        cliente.setCelularCliente(entity.getCelularCliente());
        cliente.setEmailCliente(entity.getEmailCliente());
        
        return cliente;
    }

    public static ClienteEntity toEntity(Cliente domain) {
        if (domain == null) {
            return null;
        }
        
        ClienteEntity entity = new ClienteEntity();
        entity.setId(domain.getId());
        entity.setCodigoCliente(domain.getCodigoCliente());
        entity.setNomeCliente(domain.getNomeCliente());
        entity.setCelularCliente(domain.getCelularCliente());
        entity.setEmailCliente(domain.getEmailCliente());
        
        return entity;
    }

    public static void updateEntity(ClienteEntity entity, Cliente domain) {
        if (entity == null || domain == null) {
            return;
        }
        
        entity.setCodigoCliente(domain.getCodigoCliente());
        entity.setNomeCliente(domain.getNomeCliente());
        entity.setCelularCliente(domain.getCelularCliente());
        entity.setEmailCliente(domain.getEmailCliente());
    }
}
