package com.carepetz.domain.service;

import com.carepetz.domain.model.Cliente;
import com.carepetz.domain.port.out.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para ClienteService
 * Seguindo boas práticas de Clean Code para testes
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("Testes do ClienteService")
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private Cliente clienteValido;

    @BeforeEach
    void setUp() {
        clienteValido = new Cliente();
        clienteValido.setId(1L);
        clienteValido.setCodigoCliente("123e4567-e89b-12d3-a456-426614174000");
        clienteValido.setNomeCliente("João Silva");
        clienteValido.setCelularCliente("(11) 99999-9999");
        clienteValido.setEmailCliente("joao@email.com");
    }

    @Test
    @DisplayName("Deve criar cliente com sucesso quando dados são válidos")
    void deveCriarClienteComSucesso() {
        // Arrange
        when(clienteRepository.existePorCodigo(anyString())).thenReturn(false);
        when(clienteRepository.salvar(any(Cliente.class))).thenReturn(clienteValido);

        // Act
        Cliente clienteCriado = clienteService.criarCliente(clienteValido);

        // Assert
        assertNotNull(clienteCriado);
        assertEquals(clienteValido.getNomeCliente(), clienteCriado.getNomeCliente());
        verify(clienteRepository, times(1)).salvar(clienteValido);
    }

    @Test
    @DisplayName("Deve lançar exceção quando cliente é nulo")
    void deveLancarExcecaoQuandoClienteNulo() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> clienteService.criarCliente(null)
        );
        
        assertEquals("Cliente não pode ser nulo", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando nome do cliente está vazio")
    void deveLancarExcecaoQuandoNomeVazio() {
        // Arrange
        clienteValido.setNomeCliente("");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> clienteService.criarCliente(clienteValido)
        );
        
        assertEquals("Nome do cliente é obrigatório", exception.getMessage());
    }

    @Test
    @DisplayName("Deve lançar exceção quando email é inválido")
    void deveLancarExcecaoQuandoEmailInvalido() {
        // Arrange
        clienteValido.setEmailCliente("email-invalido");

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> clienteService.criarCliente(clienteValido)
        );
        
        assertEquals("Email do cliente inválido", exception.getMessage());
    }

    @Test
    @DisplayName("Deve buscar cliente por ID com sucesso")
    void deveBuscarClientePorIdComSucesso() {
        // Arrange
        Long id = 1L;
        when(clienteRepository.buscarPorId(id)).thenReturn(Optional.of(clienteValido));

        // Act
        Optional<Cliente> clienteEncontrado = clienteService.buscarClientePorId(id);

        // Assert
        assertTrue(clienteEncontrado.isPresent());
        assertEquals(clienteValido.getNomeCliente(), clienteEncontrado.get().getNomeCliente());
    }

    @Test
    @DisplayName("Deve retornar vazio quando cliente não existe")
    void deveRetornarVazioQuandoClienteNaoExiste() {
        // Arrange
        Long id = 999L;
        when(clienteRepository.buscarPorId(id)).thenReturn(Optional.empty());

        // Act
        Optional<Cliente> clienteEncontrado = clienteService.buscarClientePorId(id);

        // Assert
        assertFalse(clienteEncontrado.isPresent());
    }

    @Test
    @DisplayName("Deve listar todos os clientes")
    void deveListarTodosClientes() {
        // Arrange
        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNomeCliente("Maria Silva");
        
        List<Cliente> clientes = Arrays.asList(clienteValido, cliente2);
        when(clienteRepository.buscarTodos()).thenReturn(clientes);

        // Act
        List<Cliente> clientesEncontrados = clienteService.listarTodosClientes();

        // Assert
        assertEquals(2, clientesEncontrados.size());
        assertEquals(clienteValido.getNomeCliente(), clientesEncontrados.get(0).getNomeCliente());
        assertEquals(cliente2.getNomeCliente(), clientesEncontrados.get(1).getNomeCliente());
    }

    @Test
    @DisplayName("Deve atualizar cliente com sucesso")
    void deveAtualizarClienteComSucesso() {
        // Arrange
        Long id = 1L;
        when(clienteRepository.existe(id)).thenReturn(true);
        when(clienteRepository.salvar(any(Cliente.class))).thenReturn(clienteValido);

        // Act
        Cliente clienteAtualizado = clienteService.atualizarCliente(id, clienteValido);

        // Assert
        assertNotNull(clienteAtualizado);
        assertEquals(id, clienteAtualizado.getId());
    }

    @Test
    @DisplayName("Deve lançar exceção ao atualizar cliente inexistente")
    void deveLancarExcecaoAoAtualizarClienteInexistente() {
        // Arrange
        Long id = 999L;
        when(clienteRepository.existe(id)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> clienteService.atualizarCliente(id, clienteValido)
        );
        
        assertEquals("Cliente não encontrado com ID: " + id, exception.getMessage());
    }

    @Test
    @DisplayName("Deve excluir cliente com sucesso")
    void deveExcluirClienteComSucesso() {
        // Arrange
        Long id = 1L;
        when(clienteRepository.existe(id)).thenReturn(true);
        doNothing().when(clienteRepository).excluir(id);

        // Act
        assertDoesNotThrow(() -> clienteService.excluirCliente(id));

        // Assert
        verify(clienteRepository, times(1)).excluir(id);
    }

    @Test
    @DisplayName("Deve lançar exceção ao excluir cliente inexistente")
    void deveLancarExcecaoAoExcluirClienteInexistente() {
        // Arrange
        Long id = 999L;
        when(clienteRepository.existe(id)).thenReturn(false);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class,
            () -> clienteService.excluirCliente(id)
        );
        
        assertEquals("Cliente não encontrado com ID: " + id, exception.getMessage());
    }

    @Test
    @DisplayName("Deve verificar se cliente existe")
    void deveVerificarSeClienteExiste() {
        // Arrange
        Long id = 1L;
        when(clienteRepository.existe(id)).thenReturn(true);

        // Act
        boolean existe = clienteService.existeCliente(id);

        // Assert
        assertTrue(existe);
    }
}
