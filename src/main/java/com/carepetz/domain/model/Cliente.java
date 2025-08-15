package com.carepetz.domain.model;

import java.util.UUID;

/**
 * Entidade Cliente do domínio
 * Representa um cliente do sistema de agendamento
 */
public class Cliente {

    private Long id;
    private String codigoCliente;
    private String nomeCliente;
    private String celularCliente;
    private String emailCliente;

    public Cliente() {
        this.codigoCliente = UUID.randomUUID().toString();
    }

    public Cliente(String nomeCliente, String celularCliente, String emailCliente) {
        this();
        this.nomeCliente = nomeCliente;
        this.celularCliente = celularCliente;
        this.emailCliente = emailCliente;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public String getCelularCliente() {
        return celularCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public void setCelularCliente(String celularCliente) {
        this.celularCliente = celularCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public boolean isValidEmail() {
        return emailCliente != null && 
               emailCliente.contains("@") && 
               emailCliente.contains(".");
    }

    public boolean isValidCelular() {
        return celularCliente != null && 
               celularCliente.replaceAll("[^0-9]", "").length() >= 10;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", codigoCliente='" + codigoCliente + '\'' +
                ", nomeCliente='" + nomeCliente + '\'' +
                ", celularCliente='" + celularCliente + '\'' +
                ", emailCliente='" + emailCliente + '\'' +
                '}';
    }
}
