package com.carepetz.infrastructure.mapper;

import com.carepetz.domain.model.Servico;
import com.carepetz.infrastructure.dto.ServicoDTO;

/**
 * Mapper para conversão entre Servico e ServicoDTO
 * Aplicando princípios do Clean Code para conversões entre camadas
 */
public class ServicoDTOMapper {

    public static Servico toDomain(ServicoDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Servico servico = new Servico();
        servico.setId(dto.getId());
        servico.setCodigoServico(dto.getCodigoServico());
        servico.setDescricaoServico(dto.getDescricaoServico());
        servico.setValorServico(dto.getValorServico());
        
        return servico;
    }

    public static ServicoDTO toDTO(Servico domain) {
        if (domain == null) {
            return null;
        }
        
        ServicoDTO dto = new ServicoDTO();
        dto.setId(domain.getId());
        dto.setCodigoServico(domain.getCodigoServico());
        dto.setDescricaoServico(domain.getDescricaoServico());
        dto.setValorServico(domain.getValorServico());
        
        return dto;
    }
}
