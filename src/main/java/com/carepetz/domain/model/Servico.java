package com.carepetz.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Entidade Servico do domínio
 * Representa um serviço oferecido pelo pet shop
 */
public class Servico {

    private Long id;
    private String codigoServico;
    private String descricaoServico;
    private BigDecimal valorServico;

    public Servico() {
        this.codigoServico = UUID.randomUUID().toString();
    }

    public Servico(String descricaoServico, BigDecimal valorServico) {
        this();
        this.descricaoServico = descricaoServico;
        this.valorServico = valorServico;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getCodigoServico() {
        return codigoServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public boolean isValorValido() {
        return valorServico != null && valorServico.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean isDescricaoValida() {
        return descricaoServico != null && !descricaoServico.trim().isEmpty();
    }

    @Override
    public String toString() {
        return "Servico{" +
                "id=" + id +
                ", codigoServico='" + codigoServico + '\'' +
                ", descricaoServico='" + descricaoServico + '\'' +
                ", valorServico=" + valorServico +
                '}';
    }
}
