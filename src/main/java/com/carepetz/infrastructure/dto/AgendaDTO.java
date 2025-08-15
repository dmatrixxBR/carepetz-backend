package com.carepetz.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * DTO para Agenda - Entrada e Saída de dados da API
 * Seguindo boas práticas do Clean Code
 */
public class AgendaDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("codigoAgenda")
    private String codigoAgenda;

    @JsonProperty("clienteId")
    @NotNull(message = "ID do cliente é obrigatório")
    private Long clienteId;

    @JsonProperty("servicoId")
    @NotNull(message = "ID do serviço é obrigatório")
    private Long servicoId;

    @JsonProperty("dataAgenda")
    @NotNull(message = "Data do agendamento é obrigatória")
    private LocalDate dataAgenda;

    @JsonProperty("horaAgenda")
    @NotNull(message = "Hora do agendamento é obrigatória")
    private LocalTime horaAgenda;

    @JsonProperty("valorServico")
    @NotNull(message = "Valor do serviço é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor deve ser maior que zero")
    private BigDecimal valorServico;

    // Propriedades para exibição (apenas leitura)
    @JsonProperty("nomeCliente")
    private String nomeCliente;

    @JsonProperty("descricaoServico")
    private String descricaoServico;

    // Construtor padrão
    public AgendaDTO() {}

    // Construtor completo
    public AgendaDTO(Long id, String codigoAgenda, Long clienteId, Long servicoId,
                     LocalDate dataAgenda, LocalTime horaAgenda, BigDecimal valorServico) {
        this.id = id;
        this.codigoAgenda = codigoAgenda;
        this.clienteId = clienteId;
        this.servicoId = servicoId;
        this.dataAgenda = dataAgenda;
        this.horaAgenda = horaAgenda;
        this.valorServico = valorServico;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoAgenda() {
        return codigoAgenda;
    }

    public void setCodigoAgenda(String codigoAgenda) {
        this.codigoAgenda = codigoAgenda;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getServicoId() {
        return servicoId;
    }

    public void setServicoId(Long servicoId) {
        this.servicoId = servicoId;
    }

    public LocalDate getDataAgenda() {
        return dataAgenda;
    }

    public void setDataAgenda(LocalDate dataAgenda) {
        this.dataAgenda = dataAgenda;
    }

    public LocalTime getHoraAgenda() {
        return horaAgenda;
    }

    public void setHoraAgenda(LocalTime horaAgenda) {
        this.horaAgenda = horaAgenda;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }

    @Override
    public String toString() {
        return "AgendaDTO{" +
                "id=" + id +
                ", codigoAgenda='" + codigoAgenda + '\'' +
                ", clienteId=" + clienteId +
                ", servicoId=" + servicoId +
                ", dataAgenda=" + dataAgenda +
                ", horaAgenda=" + horaAgenda +
                ", valorServico=" + valorServico +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", descricaoServico='" + descricaoServico + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        
        AgendaDTO that = (AgendaDTO) obj;
        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
