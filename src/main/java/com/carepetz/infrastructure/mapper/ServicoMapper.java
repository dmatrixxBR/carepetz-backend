package com.carepetz.infrastructure.mapper;

import com.carepetz.domain.model.Servico;
import com.carepetz.infrastructure.entity.ServicoEntity;

/**
 * Mapper para conversão entre Servico e ServicoEntity
 * Aplicando princípios do Clean Code para conversões
 */
public class ServicoMapper {

    public static Servico toDomain(ServicoEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Servico servico = new Servico();
        servico.setId(entity.getId());
        servico.setCodigoServico(entity.getCodigoServico());
        servico.setDescricaoServico(entity.getDescricaoServico());
        servico.setValorServico(entity.getValorServico());
        
        return servico;
    }

    public static ServicoEntity toEntity(Servico domain) {
        if (domain == null) {
            return null;
        }
        
        ServicoEntity entity = new ServicoEntity();
        entity.setId(domain.getId());
        entity.setCodigoServico(domain.getCodigoServico());
        entity.setDescricaoServico(domain.getDescricaoServico());
        entity.setValorServico(domain.getValorServico());
        
        return entity;
    }

    public static void updateEntity(ServicoEntity entity, Servico domain) {
        if (entity == null || domain == null) {
            return;
        }
        
        entity.setCodigoServico(domain.getCodigoServico());
        entity.setDescricaoServico(domain.getDescricaoServico());
        entity.setValorServico(domain.getValorServico());
    }
}
