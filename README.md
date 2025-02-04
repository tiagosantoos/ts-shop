# 📌 Projeto: Sistema de Gestão de Pedidos (DESF5 - Atividade do Desafio Final)

## 📋 Descrição
Este projeto é um **Sistema de Gestão de Pedidos** desenvolvido com **Spring Boot** e banco de dados **H2**. Ele permite gerenciar clientes, produtos, pedidos e itens de pedidos, garantindo controle sobre o estoque e os pedidos realizados pelos clientes.

## 🚀 Tecnologias Utilizadas
- **Java 21** (ou versão mais recente)
- **Spring Boot 3.x**
- **Spring Data JPA**
- **Banco de Dados H2** (para ambiente de desenvolvimento)
- **Lombok** (para reduzir código boilerplate)
- **Jakarta Validation** (para validação de dados)
- **Spring Web** (para API REST)

## 📂 Estrutura do Projeto
```
/src/main/java/com/example/pedidos
│── controller/      # Controladores da API
│── entity/          # Entidades JPA
│── repository/      # Interfaces JPA Repository
│── service/         # Regras de negócio
│── exception/       # Tratamento de erros globais
│── dto/             # DTOs para transporte de dados
│── config/          # Configurações adicionais
```

## 🛠️ Configuração e Execução
### 🔧 Pré-requisitos
- **Java 21+** instalado
- **Maven** instalado
- Postman ou outra ferramenta para testar a API

### 🚀 Passos para rodar o projeto
1. Clone o repositório:
   ```sh
   git clone https://github.com/tiagosantoos/ts-shop.git
   cd ts-shop
   ```
2. Compile e rode o projeto com **Maven**:
   ```sh
   mvn spring-boot:run
   ```
3. Acesse a API pelo navegador ou Postman:
   ```
   http://localhost:8080
   ```
4. O **H2 Console** pode ser acessado em:
   ```
   http://localhost:8080/h2-console
   ```
   - **JDBC URL:** `jdbc:h2:mem:testdb`
   - **Usuário:** `sa`
   - **Senha:** *(vazia)*

## 📌 Endpoints da API
### 🛠️ Cliente
- **`GET /clientes/nome/{nome}`** → Lista todos os clientes por nome
- **`GET /clientes/count`** → Obtém a quantidade de registro
- **`GET /clientes`** → Lista todos os clientes
- **`GET /clientes/{id}`** → Obtém um cliente por ID
- **`POST /clientes`** → Cria um novo cliente
- **`PUT /clientes/{id}`** → Atualiza um cliente existente
- **`DELETE /clientes/{id}`** → Remove um cliente

### 📦 Produto
- **`GET /produtos/nome/{nome}`** → Lista todos os produtos por nome
- **`GET /produtos/count`** → Obtém a quantidade de registro
- **`GET /produtos`** → Lista todos os produtos
- **`GET /produtos/{id}`** → Obtém um produto por ID
- **`POST /produtos`** → Cadastra um novo produto
- **`PUT /produtos/{id}`** → Atualiza um produto existente
- **`DELETE /produtos/{id}`** → Remove um produto

### 🛒 Pedido
- **`GET /pedidos/nome/{nome}`** → Lista todos os pedidos por nome do cliente
- **`GET /pedidos/count`** → Obtém a quantidade de registro
- **`GET /pedidos`** → Lista todos os pedidos
- **`GET /pedidos/{id}`** → Obtém um pedido por ID
- **`POST /pedidos`** → Cria um novo pedido
- **`PUT /pedidos/{id}`** → Atualiza um pedido existente
- **`DELETE /pedidos/{id}`** → Remove um pedido

### 📄 ItemPedido
- **`GET /itens-pedido/count`** → Obtém a quantidade de registro
- **`GET /itens-pedido`** → Lista todos os itens de pedidos
- **`GET /itens-pedido/{id}`** → Obtém um item por ID
- **`POST /itens-pedido`** → Adiciona um item a um pedido
- **`PUT /itens-pedido/{id}`** → Atualiza um item
- **`DELETE /itens-pedido/{id}`** → Remove um item

## 📌 Tratamento de Erros
O sistema usa `@RestControllerAdvice` para capturar e retornar mensagens amigáveis:
- **Erros de validação (400 BAD REQUEST)**:
  ```json
  {
    "nome": "O nome não pode estar vazio",
    "email": "E-mail inválido"
  }
  ```
- **Recurso não encontrado (404 NOT FOUND)**:
  ```json
  {
    "erro": "Cliente não encontrado"
  }
  ```

## 📦 Importando Collection do Postman
1. Adicione a collection no postman ou em outro http client.
2. Atualize a variável `{{base_url}}` para `http://localhost:8080`.


