package com.carepetz.infrastructure.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * Entidade JPA para Servico
 * Representa a tabela de servicos no banco de dados
 */
@Entity
@Table(name = "servicos")
public class ServicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_servico", unique = true, nullable = false)
    private String codigoServico;

    @Column(name = "descricao_servico", nullable = false)
    private String descricaoServico;

    @Column(name = "valor_servico", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorServico;

    public ServicoEntity() {
        this.codigoServico = UUID.randomUUID().toString();
    }

    public ServicoEntity(String descricaoServico, BigDecimal valorServico) {
        this();
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
}
