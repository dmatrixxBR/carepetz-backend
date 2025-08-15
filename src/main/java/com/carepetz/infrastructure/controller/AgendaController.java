package com.carepetz.infrastructure.controller;

import com.carepetz.domain.model.Agenda;
import com.carepetz.domain.model.Cliente;
import com.carepetz.domain.model.Servico;
import com.carepetz.domain.port.in.AgendaUseCase;
import com.carepetz.domain.port.in.ClienteUseCase;
import com.carepetz.domain.port.in.ServicoUseCase;
import com.carepetz.infrastructure.dto.AgendaDTO;
import com.carepetz.infrastructure.mapper.AgendaDTOMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controlador REST para Agenda
 * Adaptador de entrada da arquitetura hexagonal
 */
@RestController
@RequestMapping("/api/agendas")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Agendas", description = "API para gerenciamento de agendamentos")
public class AgendaController {

    private final AgendaUseCase agendaUseCase;
    private final ClienteUseCase clienteUseCase;
    private final ServicoUseCase servicoUseCase;

    @Autowired
    public AgendaController(AgendaUseCase agendaUseCase, 
                           ClienteUseCase clienteUseCase,
                           ServicoUseCase servicoUseCase) {
        this.agendaUseCase = agendaUseCase;
        this.clienteUseCase = clienteUseCase;
        this.servicoUseCase = servicoUseCase;
    }

    @Operation(summary = "Criar um novo agendamento", description = "Cria um novo agendamento no sistema")
    @PostMapping
    public ResponseEntity<AgendaDTO> criarAgenda(@Valid @RequestBody AgendaDTO agendaDTO) {
        try {
            Agenda agenda = AgendaDTOMapper.toDomain(agendaDTO);
            
            // Buscar e definir o cliente
            if (agendaDTO.getClienteId() != null) {
                Optional<Cliente> cliente = clienteUseCase.buscarClientePorId(agendaDTO.getClienteId());
                if (cliente.isPresent()) {
                    agenda.setCliente(cliente.get());
                } else {
                    return ResponseEntity.badRequest().build(); // Cliente não encontrado
                }
            }
            
            // Buscar e definir o serviço
            if (agendaDTO.getServicoId() != null) {
                Optional<Servico> servico = servicoUseCase.buscarServicoPorId(agendaDTO.getServicoId());
                if (servico.isPresent()) {
                    agenda.setServico(servico.get());
                } else {
                    return ResponseEntity.badRequest().build(); // Serviço não encontrado
                }
            }
            
            Agenda agendaCriada = agendaUseCase.criarAgenda(agenda);
            
            // Buscar nomes para exibição
            AgendaDTO responseDTO = criarAgendaDTOComNomes(agendaCriada);
            
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Buscar agendamento por ID", description = "Busca um agendamento específico pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<AgendaDTO> buscarAgendaPorId(
            @Parameter(description = "ID do agendamento") @PathVariable Long id) {
        try {
            Optional<Agenda> agenda = agendaUseCase.buscarAgendaPorId(id);
            return agenda.map(a -> {
                AgendaDTO dto = criarAgendaDTOComNomes(a);
                return ResponseEntity.ok(dto);
            }).orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Listar todos os agendamentos", description = "Retorna uma lista com todos os agendamentos")
    @GetMapping
    public ResponseEntity<List<AgendaDTO>> listarTodosAgendamentos() {
        List<Agenda> agendas = agendaUseCase.listarTodasAgendas();
        List<AgendaDTO> agendasDTO = agendas.stream()
                .map(this::criarAgendaDTOComNomes)
                .collect(Collectors.toList());
        return ResponseEntity.ok(agendasDTO);
    }

    @Operation(summary = "Atualizar agendamento", description = "Atualiza os dados de um agendamento existente")
    @PutMapping("/{id}")
    public ResponseEntity<AgendaDTO> atualizarAgenda(
            @Parameter(description = "ID do agendamento") @PathVariable Long id,
            @Valid @RequestBody AgendaDTO agendaDTO) {
        try {
            Agenda agenda = AgendaDTOMapper.toDomain(agendaDTO);
            
            // Buscar e definir o cliente
            if (agendaDTO.getClienteId() != null) {
                Optional<Cliente> cliente = clienteUseCase.buscarClientePorId(agendaDTO.getClienteId());
                if (cliente.isPresent()) {
                    agenda.setCliente(cliente.get());
                } else {
                    return ResponseEntity.badRequest().build(); // Cliente não encontrado
                }
            }
            
            // Buscar e definir o serviço
            if (agendaDTO.getServicoId() != null) {
                Optional<Servico> servico = servicoUseCase.buscarServicoPorId(agendaDTO.getServicoId());
                if (servico.isPresent()) {
                    agenda.setServico(servico.get());
                } else {
                    return ResponseEntity.badRequest().build(); // Serviço não encontrado
                }
            }
            
            Agenda agendaAtualizada = agendaUseCase.atualizarAgenda(id, agenda);
            AgendaDTO responseDTO = criarAgendaDTOComNomes(agendaAtualizada);
            return ResponseEntity.ok(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir agendamento", description = "Remove um agendamento do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAgenda(
            @Parameter(description = "ID do agendamento") @PathVariable Long id) {
        try {
            agendaUseCase.excluirAgenda(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Verificar se agendamento existe", description = "Verifica se um agendamento existe pelo ID")
    @GetMapping("/{id}/existe")
    public ResponseEntity<Boolean> existeAgenda(
            @Parameter(description = "ID do agendamento") @PathVariable Long id) {
        try {
            boolean existe = agendaUseCase.existeAgenda(id);
            return ResponseEntity.ok(existe);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(false);
        }
    }

    /**
     * Método auxiliar para criar AgendaDTO com nomes de cliente e serviço
     */
    private AgendaDTO criarAgendaDTOComNomes(Agenda agenda) {
        String nomeCliente = "";
        String descricaoServico = "";
        
        // Buscar nome do cliente
        if (agenda.getCliente() != null) {
            nomeCliente = agenda.getCliente().getNomeCliente();
        } else {
            // Fallback: buscar pelo ID se o cliente não estiver carregado
            try {
                Long clienteId = agenda.getCliente() != null ? agenda.getCliente().getId() : null;
                if (clienteId != null) {
                    Optional<Cliente> cliente = clienteUseCase.buscarClientePorId(clienteId);
                    if (cliente.isPresent()) {
                        nomeCliente = cliente.get().getNomeCliente();
                    }
                }
            } catch (Exception e) {
                // Log do erro, mas continue com nome vazio
            }
        }
        
        // Buscar descrição do serviço
        if (agenda.getServico() != null) {
            descricaoServico = agenda.getServico().getDescricaoServico();
        } else {
            // Fallback: buscar pelo ID se o serviço não estiver carregado
            try {
                Long servicoId = agenda.getServico() != null ? agenda.getServico().getId() : null;
                if (servicoId != null) {
                    Optional<Servico> servico = servicoUseCase.buscarServicoPorId(servicoId);
                    if (servico.isPresent()) {
                        descricaoServico = servico.get().getDescricaoServico();
                    }
                }
            } catch (Exception e) {
                // Log do erro, mas continue com descrição vazia
            }
        }
        
        return AgendaDTOMapper.toDTOWithNames(agenda, nomeCliente, descricaoServico);
    }
}
