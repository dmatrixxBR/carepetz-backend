package com.carepetz.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * Entidade Agenda do domínio
 * Representa um agendamento de serviço para um cliente
 */
public class Agenda {

    private Long id;
    private String codigoAgenda;
    private LocalDate dataAgenda;
    private LocalTime horaAgenda;
    private Cliente cliente;
    private Servico servico;
    private BigDecimal valorServico;

    public Agenda() {
        this.codigoAgenda = UUID.randomUUID().toString();
        this.dataAgenda = LocalDate.now();
        this.horaAgenda = LocalTime.now();
    }

    public Agenda(Cliente cliente, Servico servico, LocalDate dataAgenda, LocalTime horaAgenda) {
        this();
        this.cliente = cliente;
        this.servico = servico;
        this.dataAgenda = dataAgenda;
        this.horaAgenda = horaAgenda;
        this.valorServico = servico != null ? servico.getValorServico() : BigDecimal.ZERO;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getCodigoAgenda() {
        return codigoAgenda;
    }

    public LocalDate getDataAgenda() {
        return dataAgenda;
    }

    public LocalTime getHoraAgenda() {
        return horaAgenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Servico getServico() {
        return servico;
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigoAgenda(String codigoAgenda) {
        this.codigoAgenda = codigoAgenda;
    }

    public void setDataAgenda(LocalDate dataAgenda) {
        this.dataAgenda = dataAgenda;
    }

    public void setHoraAgenda(LocalTime horaAgenda) {
        this.horaAgenda = horaAgenda;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
        if (servico != null) {
            this.valorServico = servico.getValorServico();
        }
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }

    public boolean isDataFutura() {
        return dataAgenda != null && dataAgenda.isAfter(LocalDate.now());
    }

    public boolean isAgendamentoValido() {
        return cliente != null && 
               servico != null && 
               dataAgenda != null && 
               horaAgenda != null &&
               valorServico != null &&
               valorServico.compareTo(BigDecimal.ZERO) > 0;
    }

    public String getDataFormatada() {
        return dataAgenda != null ? 
               dataAgenda.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) : null;
    }

    public String getHoraFormatada() {
        return horaAgenda != null ? 
               horaAgenda.format(DateTimeFormatter.ofPattern("HH:mm")) : null;
    }

    @Override
    public String toString() {
        return "Agenda{" +
                "id=" + id +
                ", codigoAgenda='" + codigoAgenda + '\'' +
                ", dataAgenda=" + dataAgenda +
                ", horaAgenda=" + horaAgenda +
                ", cliente=" + cliente +
                ", servico=" + servico +
                ", valorServico=" + valorServico +
                '}';
    }
}
