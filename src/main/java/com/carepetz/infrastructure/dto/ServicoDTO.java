package com.carepetz.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * DTO para Servico - Entrada e Saída de dados da API
 * Seguindo boas práticas do Clean Code
 */
public class ServicoDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("codigoServico")
    private String codigoServico;

    @JsonProperty("descricaoServico")
    @NotBlank(message = "Descrição do serviço é obrigatória")
    @Size(min = 5, max = 200, message = "Descrição deve ter entre 5 e 200 caracteres")
    private String descricaoServico;

    @JsonProperty("valorServico")
    @NotNull(message = "Valor do serviço é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal valorServico;

    // Construtor padrão
    public ServicoDTO() {}

    // Construtor completo
    public ServicoDTO(Long id, String codigoServico, String descricaoServico, BigDecimal valorServico) {
        this.id = id;
        this.codigoServico = codigoServico;
        this.descricaoServico = descricaoServico;
        this.valorServico = valorServico;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoServico() {
        return codigoServico;
    }

    public void setCodigoServico(String codigoServico) {
        this.codigoServico = codigoServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    @Override
    public String toString() {
        return "ServicoDTO{" +
                "id=" + id +
                ", codigoServico='" + codigoServico + '\'' +
                ", descricaoServico='" + descricaoServico + '\'' +
                ", valorServico=" + valorServico +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        ServicoDTO that = (ServicoDTO) obj;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
