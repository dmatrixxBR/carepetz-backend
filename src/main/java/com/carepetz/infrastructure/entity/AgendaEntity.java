package com.carepetz.infrastructure.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * Entidade JPA para Agenda
 * Representa a tabela de agendas no banco de dados
 */
@Entity
@Table(name = "agendas")
public class AgendaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_agenda", unique = true, nullable = false)
    private String codigoAgenda;

    @Column(name = "data_agenda", nullable = false)
    private LocalDate dataAgenda;

    @Column(name = "hora_agenda", nullable = false)
    private LocalTime horaAgenda;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    private ClienteEntity cliente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "servico_id", nullable = false)
    private ServicoEntity servico;

    @Column(name = "valor_servico", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorServico;

    public AgendaEntity() {
        this.codigoAgenda = UUID.randomUUID().toString();
        this.dataAgenda = LocalDate.now();
        this.horaAgenda = LocalTime.now();
    }

    public AgendaEntity(ClienteEntity cliente, ServicoEntity servico, 
                       LocalDate dataAgenda, LocalTime horaAgenda) {
        this();
        this.cliente = cliente;
        this.servico = servico;
        this.dataAgenda = dataAgenda;
        this.horaAgenda = horaAgenda;
        this.valorServico = servico != null ? servico.getValorServico() : BigDecimal.ZERO;
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

    public ClienteEntity getCliente() {
        return cliente;
    }

    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    public ServicoEntity getServico() {
        return servico;
    }

    public void setServico(ServicoEntity servico) {
        this.servico = servico;
        if (servico != null) {
            this.valorServico = servico.getValorServico();
        }
    }

    public BigDecimal getValorServico() {
        return valorServico;
    }

    public void setValorServico(BigDecimal valorServico) {
        this.valorServico = valorServico;
    }
}
