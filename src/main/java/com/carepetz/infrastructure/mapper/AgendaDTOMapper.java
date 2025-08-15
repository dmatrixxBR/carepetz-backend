package com.carepetz.infrastructure.mapper;

import com.carepetz.domain.model.Agenda;
import com.carepetz.infrastructure.dto.AgendaDTO;

/**
 * Mapper para conversão entre Agenda e AgendaDTO
 * Aplicando princípios do Clean Code para conversões entre camadas
 */
public class AgendaDTOMapper {

    public static Agenda toDomain(AgendaDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Agenda agenda = new Agenda();
        agenda.setId(dto.getId());
        agenda.setCodigoAgenda(dto.getCodigoAgenda());
        agenda.setDataAgenda(dto.getDataAgenda());
        agenda.setHoraAgenda(dto.getHoraAgenda());
        agenda.setValorServico(dto.getValorServico());
        
        // Note: Cliente e Servico devem ser definidos posteriormente pelos serviços
        // pois o DTO contém apenas os IDs, não os objetos completos
        
        return agenda;
    }

    public static AgendaDTO toDTO(Agenda domain) {
        if (domain == null) {
            return null;
        }
        
        AgendaDTO dto = new AgendaDTO();
        dto.setId(domain.getId());
        dto.setCodigoAgenda(domain.getCodigoAgenda());
        dto.setClienteId(domain.getCliente() != null ? domain.getCliente().getId() : null);
        dto.setServicoId(domain.getServico() != null ? domain.getServico().getId() : null);
        dto.setDataAgenda(domain.getDataAgenda());
        dto.setHoraAgenda(domain.getHoraAgenda());
        dto.setValorServico(domain.getValorServico());
        
        return dto;
    }

    public static AgendaDTO toDTOWithNames(Agenda domain, String nomeCliente, String descricaoServico) {
        AgendaDTO dto = toDTO(domain);
        if (dto != null) {
            dto.setNomeCliente(nomeCliente);
            dto.setDescricaoServico(descricaoServico);
        }
        return dto;
    }
}
