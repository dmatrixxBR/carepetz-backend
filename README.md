# CarePetz Backend

## 🐾 Sobre o Projeto

O **CarePetz Backend** é uma API REST desenvolvida em **Spring Boot** com **Java 17** para servir o sistema de agendamento de serviços para pets. Este projeto substitui o JSON Server usado no frontend Angular, fornecendo uma solução robusta e escalável.

## 🏗️ Arquitetura

O projeto segue os princípios da **Arquitetura Hexagonal** (Ports and Adapters), garantindo:

- **Separação clara de responsabilidades**
- **Facilidade para testes unitários**
- **Baixo acoplamento entre camadas**
- **Alta coesão dos módulos**

### Estrutura de Pastas

```
src/main/java/com/carepetz/
├── domain/                 # Núcleo da aplicação
│   ├── model/             # Entidades de domínio
│   ├── port/              # Interfaces (contratos)
│   │   ├── in/           # Casos de uso (entrada)
│   │   └── out/          # Repositórios (saída)
│   └── service/          # Implementação dos casos de uso
├── infrastructure/        # Camada de infraestrutura
│   ├── adapter/          # Adaptadores de repositório
│   ├── controller/       # Controladores REST
│   ├── dto/             # Objetos de transferência de dados
│   ├── entity/          # Entidades JPA
│   ├── mapper/          # Mapeadores entre camadas
│   └── repository/      # Interfaces JPA Repository
├── configuration/        # Configurações da aplicação
└── CarePetzBackendApplication.java
```

## 🛠️ Tecnologias Utilizadas

### **Backend Framework:**
- **Spring Boot 3.2.2** - Framework principal
- **Java 17** - Linguagem de programação
- **Maven** - Gerenciamento de dependências

### **Banco de Dados:**
- **H2 Database** - Banco em memória para desenvolvimento
- **Spring Data JPA** - Abstração de acesso a dados

### **Documentação:**
- **SpringDoc OpenAPI 3** - Geração automática da documentação Swagger

### **Testes:**
- **JUnit 5** - Framework de testes
- **Mockito** - Mock objects para testes unitários
- **TestContainers** - Testes de integração

### **Qualidade de Código:**
- **MapStruct** - Mapeamento de objetos
- **Bean Validation** - Validação de dados

## 🚀 Como Executar

### **Pré-requisitos:**
- Java 17 ou superior
- Maven 3.6+ (ou usar o Maven Wrapper incluído)

### **Comandos de Execução:**

```bash
# 1. Clonar o repositório
cd /Users/danielbarbosamartins/projetos/carepetz-backend

# 2. Executar a aplicação
./mvnw spring-boot:run

# Ou se tiver Maven instalado:
mvn spring-boot:run
```

A aplicação estará disponível em: **http://localhost:3000**

### **Endpoints Principais:**

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET    | `/clientes` | Listar todos os clientes |
| POST   | `/clientes` | Criar novo cliente |
| GET    | `/clientes/{id}` | Buscar cliente por ID |
| PUT    | `/clientes/{id}` | Atualizar cliente |
| DELETE | `/clientes/{id}` | Excluir cliente |
| GET    | `/servicos` | Listar todos os serviços |
| POST   | `/servicos` | Criar novo serviço |
| GET    | `/servicos/{id}` | Buscar serviço por ID |
| PUT    | `/servicos/{id}` | Atualizar serviço |
| DELETE | `/servicos/{id}` | Excluir serviço |
| GET    | `/agendas` | Listar todos os agendamentos |
| POST   | `/agendas` | Criar novo agendamento |
| GET    | `/agendas/{id}` | Buscar agendamento por ID |
| PUT    | `/agendas/{id}` | Atualizar agendamento |
| DELETE | `/agendas/{id}` | Excluir agendamento |

## 📚 Documentação da API

### **Swagger UI:**
Acesse: **http://localhost:3000/swagger-ui.html**

### **OpenAPI JSON:**
Acesse: **http://localhost:3000/api-docs**

## 🧪 Testes

```bash
# Executar todos os testes
./mvnw test

# Executar testes com relatório de cobertura
./mvnw test jacoco:report

# Executar apenas testes unitários
./mvnw test -Dtest="*Test"
```

## 🎯 Boas Práticas Implementadas

### **Clean Code:**
- Nomes descritivos e expressivos
- Funções pequenas e focadas
- Comentários apenas quando necessário
- Tratamento adequado de exceções

### **SOLID Principles:**
- **Single Responsibility:** Cada classe tem uma única responsabilidade
- **Open/Closed:** Extensível para novos recursos sem modificar código existente
- **Liskov Substitution:** Implementações podem ser substituídas
- **Interface Segregation:** Interfaces específicas e coesas
- **Dependency Inversion:** Dependência de abstrações, não de implementações

### **Arquitetura Hexagonal:**
- **Domínio isolado** da infraestrutura
- **Ports and Adapters** para flexibilidade
- **Inversão de dependências** bem definida

## 💾 Banco de Dados

### **H2 Console (Desenvolvimento):**
- URL: **http://localhost:3000/h2-console**
- JDBC URL: `jdbc:h2:mem:carepetzdb`
- Username: `sa`
- Password: _(vazio)_

### **Dados de Exemplo:**
O sistema é inicializado com dados de exemplo compatíveis com o frontend Angular:

- **4 Serviços:** PETZ CARE, PETZ VETZ, PETZ DELIVERY, PETZ WALKER
- **3 Clientes:** João Silva, Maria Santos, Pedro Oliveira
- **3 Agendamentos:** Para demonstração do funcionamento

## 🔧 Configurações

### **CORS:**
Configurado para aceitar requisições do frontend Angular (`http://localhost:4200`)

### **Profiles:**
- **default:** Desenvolvimento com H2
- **test:** Testes com H2 in-memory
- **prod:** Preparado para PostgreSQL (configuração adicional necessária)

## 🚀 Deploy

### **Docker (Futuro):**
```dockerfile
# Dockerfile será criado para containerização
FROM openjdk:17-jdk-slim
COPY target/carepetz-backend-1.0.0.jar app.jar
EXPOSE 3000
ENTRYPOINT ["java", "-jar", "/app.jar"]
```

## 🤝 Compatibilidade com Frontend

Este backend é **100% compatível** com o frontend Angular CarePetz existente:

- **Mesma porta (3000)** do JSON Server
- **Endpoints idênticos** aos esperados pelo frontend
- **Estrutura de dados consistente** com os modelos Angular
- **CORS configurado** para comunicação direta

## 📈 Próximos Passos

- [ ] Implementar autenticação JWT
- [ ] Adicionar cache com Redis
- [ ] Configurar banco PostgreSQL para produção
- [ ] Implementar logs estruturados
- [ ] Adicionar monitoramento com Actuator
- [ ] Deploy automatizado com Docker

## 📝 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👥 Contribuição

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/AmazingFeature`)
3. Commit suas mudanças (`git commit -m 'Add some AmazingFeature'`)
4. Push para a branch (`git push origin feature/AmazingFeature`)
5. Abra um Pull Request

---

**Desenvolvido com ❤️ para a comunidade CarePetz**
