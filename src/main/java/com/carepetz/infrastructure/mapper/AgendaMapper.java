package com.carepetz.infrastructure.mapper;

import com.carepetz.domain.model.Agenda;
import com.carepetz.infrastructure.entity.AgendaEntity;

/**
 * Mapper para conversão entre Agenda e AgendaEntity
 * Aplicando princípios do Clean Code para conversões
 */
public class AgendaMapper {

    public static Agenda toDomain(AgendaEntity entity) {
        if (entity == null) {
            return null;
        }
        
        Agenda agenda = new Agenda();
        agenda.setId(entity.getId());
        agenda.setCodigoAgenda(entity.getCodigoAgenda());
        agenda.setDataAgenda(entity.getDataAgenda());
        agenda.setHoraAgenda(entity.getHoraAgenda());
        agenda.setValorServico(entity.getValorServico());
        
        if (entity.getCliente() != null) {
            agenda.setCliente(ClienteMapper.toDomain(entity.getCliente()));
        }
        
        if (entity.getServico() != null) {
            agenda.setServico(ServicoMapper.toDomain(entity.getServico()));
        }
        
        return agenda;
    }

    public static AgendaEntity toEntity(Agenda domain) {
        if (domain == null) {
            return null;
        }
        
        AgendaEntity entity = new AgendaEntity();
        entity.setId(domain.getId());
        entity.setCodigoAgenda(domain.getCodigoAgenda());
        entity.setDataAgenda(domain.getDataAgenda());
        entity.setHoraAgenda(domain.getHoraAgenda());
        entity.setValorServico(domain.getValorServico());
        
        if (domain.getCliente() != null) {
            entity.setCliente(ClienteMapper.toEntity(domain.getCliente()));
        }
        
        if (domain.getServico() != null) {
            entity.setServico(ServicoMapper.toEntity(domain.getServico()));
        }
        
        return entity;
    }

    public static void updateEntity(AgendaEntity entity, Agenda domain) {
        if (entity == null || domain == null) {
            return;
        }
        
        entity.setCodigoAgenda(domain.getCodigoAgenda());
        entity.setDataAgenda(domain.getDataAgenda());
        entity.setHoraAgenda(domain.getHoraAgenda());
        entity.setValorServico(domain.getValorServico());
        
        if (domain.getCliente() != null) {
            entity.setCliente(ClienteMapper.toEntity(domain.getCliente()));
        }
        
        if (domain.getServico() != null) {
            entity.setServico(ServicoMapper.toEntity(domain.getServico()));
        }
    }
}
