package com.carepetz.infrastructure.controller;

import com.carepetz.domain.model.Servico;
import com.carepetz.domain.port.in.ServicoUseCase;
import com.carepetz.infrastructure.dto.ServicoDTO;
import com.carepetz.infrastructure.mapper.ServicoDTOMapper;
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
 * Controlador REST para Servico
 * Adaptador de entrada da arquitetura hexagonal
 */
@RestController
@RequestMapping("/api/servicos")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Serviços", description = "API para gerenciamento de serviços")
public class ServicoController {

    private final ServicoUseCase servicoUseCase;

    @Autowired
    public ServicoController(ServicoUseCase servicoUseCase) {
        this.servicoUseCase = servicoUseCase;
    }

    @Operation(summary = "Criar um novo serviço", description = "Cria um novo serviço no sistema")
    @PostMapping
    public ResponseEntity<ServicoDTO> criarServico(@Valid @RequestBody ServicoDTO servicoDTO) {
        try {
            Servico servico = ServicoDTOMapper.toDomain(servicoDTO);
            Servico servicoCriado = servicoUseCase.criarServico(servico);
            ServicoDTO responseDTO = ServicoDTOMapper.toDTO(servicoCriado);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Buscar serviço por ID", description = "Busca um serviço específico pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> buscarServicoPorId(
            @Parameter(description = "ID do serviço") @PathVariable Long id) {
        try {
            Optional<Servico> servico = servicoUseCase.buscarServicoPorId(id);
            return servico.map(s -> ResponseEntity.ok(ServicoDTOMapper.toDTO(s)))
                          .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Listar todos os serviços", description = "Retorna uma lista com todos os serviços")
    @GetMapping
    public ResponseEntity<List<ServicoDTO>> listarTodosServicos() {
        List<Servico> servicos = servicoUseCase.listarTodosServicos();
        List<ServicoDTO> servicosDTO = servicos.stream()
                .map(ServicoDTOMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(servicosDTO);
    }

    @Operation(summary = "Atualizar serviço", description = "Atualiza os dados de um serviço existente")
    @PutMapping("/{id}")
    public ResponseEntity<ServicoDTO> atualizarServico(
            @Parameter(description = "ID do serviço") @PathVariable Long id,
            @Valid @RequestBody ServicoDTO servicoDTO) {
        try {
            Servico servico = ServicoDTOMapper.toDomain(servicoDTO);
            Servico servicoAtualizado = servicoUseCase.atualizarServico(id, servico);
            ServicoDTO responseDTO = ServicoDTOMapper.toDTO(servicoAtualizado);
            return ResponseEntity.ok(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir serviço", description = "Remove um serviço do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirServico(
            @Parameter(description = "ID do serviço") @PathVariable Long id) {
        try {
            servicoUseCase.excluirServico(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Verificar se serviço existe", description = "Verifica se um serviço existe pelo ID")
    @GetMapping("/{id}/existe")
    public ResponseEntity<Boolean> existeServico(
            @Parameter(description = "ID do serviço") @PathVariable Long id) {
        try {
            boolean existe = servicoUseCase.existeServico(id);
            return ResponseEntity.ok(existe);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(false);
        }
    }
}
