package com.carepetz.infrastructure.entity;

import jakarta.persistence.*;
import java.util.UUID;

/**
 * Entidade JPA para Cliente
 * Representa a tabela de clientes no banco de dados
 */
@Entity
@Table(name = "clientes")
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_cliente", unique = true, nullable = false)
    private String codigoCliente;

    @Column(name = "nome_cliente", nullable = false)
    private String nomeCliente;

    @Column(name = "celular_cliente", nullable = false)
    private String celularCliente;

    @Column(name = "email_cliente", nullable = false)
    private String emailCliente;

    public ClienteEntity() {
        this.codigoCliente = UUID.randomUUID().toString();
    }

    public ClienteEntity(String nomeCliente, String celularCliente, String emailCliente) {
        this();
        this.nomeCliente = nomeCliente;
        this.celularCliente = celularCliente;
        this.emailCliente = emailCliente;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }
}
