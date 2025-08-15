package com.carepetz.infrastructure.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * DTO para Cliente - Entrada e Saída de dados da API
 * Seguindo boas práticas do Clean Code
 */
public class ClienteDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("codigoCliente")
    private String codigoCliente;

    @JsonProperty("nomeCliente")
    @NotBlank(message = "Nome do cliente é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nomeCliente;

    @JsonProperty("celularCliente")
    @NotBlank(message = "Celular do cliente é obrigatório")
    @Size(min = 10, max = 15, message = "Celular deve ter entre 10 e 15 caracteres")
    private String celularCliente;

    @JsonProperty("emailCliente")
    @NotBlank(message = "Email do cliente é obrigatório")
    @Email(message = "Email deve ter um formato válido")
    private String emailCliente;

    // Construtor padrão
    public ClienteDTO() {}

    // Construtor completo
    public ClienteDTO(Long id, String codigoCliente, String nomeCliente, 
                     String celularCliente, String emailCliente) {
        this.id = id;
        this.codigoCliente = codigoCliente;
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
