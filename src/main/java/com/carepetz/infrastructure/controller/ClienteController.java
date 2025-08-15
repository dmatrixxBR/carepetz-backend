package com.carepetz.infrastructure.controller;

import com.carepetz.domain.model.Cliente;
import com.carepetz.domain.port.in.ClienteUseCase;
import com.carepetz.infrastructure.dto.ClienteDTO;
import com.carepetz.infrastructure.mapper.ClienteDTOMapper;
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
 * Controlador REST para Cliente
 * Adaptador de entrada da arquitetura hexagonal
 */
@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:4200")
@Tag(name = "Clientes", description = "API para gerenciamento de clientes")
public class ClienteController {

    private final ClienteUseCase clienteUseCase;

    @Autowired
    public ClienteController(ClienteUseCase clienteUseCase) {
        this.clienteUseCase = clienteUseCase;
    }

    @Operation(summary = "Criar um novo cliente", description = "Cria um novo cliente no sistema")
    @PostMapping
    public ResponseEntity<ClienteDTO> criarCliente(@Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente cliente = ClienteDTOMapper.toDomain(clienteDTO);
            Cliente clienteCriado = clienteUseCase.criarCliente(cliente);
            ClienteDTO responseDTO = ClienteDTOMapper.toDTO(clienteCriado);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Buscar cliente por ID", description = "Busca um cliente específico pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(
            @Parameter(description = "ID do cliente") @PathVariable Long id) {
        try {
            Optional<Cliente> cliente = clienteUseCase.buscarClientePorId(id);
            return cliente.map(c -> ResponseEntity.ok(ClienteDTOMapper.toDTO(c)))
                          .orElse(ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Listar todos os clientes", description = "Retorna uma lista com todos os clientes")
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodosClientes() {
        List<Cliente> clientes = clienteUseCase.listarTodosClientes();
        List<ClienteDTO> clientesDTO = clientes.stream()
                .map(ClienteDTOMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(clientesDTO);
    }

    @Operation(summary = "Atualizar cliente", description = "Atualiza os dados de um cliente existente")
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> atualizarCliente(
            @Parameter(description = "ID do cliente") @PathVariable Long id,
            @Valid @RequestBody ClienteDTO clienteDTO) {
        try {
            Cliente cliente = ClienteDTOMapper.toDomain(clienteDTO);
            Cliente clienteAtualizado = clienteUseCase.atualizarCliente(id, cliente);
            ClienteDTO responseDTO = ClienteDTOMapper.toDTO(clienteAtualizado);
            return ResponseEntity.ok(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Excluir cliente", description = "Remove um cliente do sistema")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(
            @Parameter(description = "ID do cliente") @PathVariable Long id) {
        try {
            clienteUseCase.excluirCliente(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @Operation(summary = "Verificar se cliente existe", description = "Verifica se um cliente existe pelo ID")
    @GetMapping("/{id}/existe")
    public ResponseEntity<Boolean> existeCliente(
            @Parameter(description = "ID do cliente") @PathVariable Long id) {
        try {
            boolean existe = clienteUseCase.existeCliente(id);
            return ResponseEntity.ok(existe);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(false);
        }
    }
}
