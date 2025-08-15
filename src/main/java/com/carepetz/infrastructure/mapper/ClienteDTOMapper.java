package com.carepetz.infrastructure.mapper;

import com.carepetz.domain.model.Cliente;
import com.carepetz.infrastructure.dto.ClienteDTO;

/**
 * Mapper para conversão entre Cliente e ClienteDTO
 * Aplicando princípios do Clean Code para conversões entre camadas
 */
public class ClienteDTOMapper {

    public static Cliente toDomain(ClienteDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setCodigoCliente(dto.getCodigoCliente());
        cliente.setNomeCliente(dto.getNomeCliente());
        cliente.setCelularCliente(dto.getCelularCliente());
        cliente.setEmailCliente(dto.getEmailCliente());
        
        return cliente;
    }

    public static ClienteDTO toDTO(Cliente domain) {
        if (domain == null) {
            return null;
        }
        
        ClienteDTO dto = new ClienteDTO();
        dto.setId(domain.getId());
        dto.setCodigoCliente(domain.getCodigoCliente());
        dto.setNomeCliente(domain.getNomeCliente());
        dto.setCelularCliente(domain.getCelularCliente());
        dto.setEmailCliente(domain.getEmailCliente());
        
        return dto;
    }
}
