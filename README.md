# 🛒 Quick Shop E-commerce

![Java](https://img.shields.io/badge/Java-17+-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0+-6DB33F?logo=springboot&logoColor=white)
![MongoDB](https://img.shields.io/badge/MongoDB-4.4+-47A248?logo=mongodb&logoColor=white)
![Redis](https://img.shields.io/badge/Redis-7.0+-DC382D?logo=redis&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.0+-6DB33F?logo=springsecurity&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-Auth-000000?logo=jsonwebtokens&logoColor=white)
![License](https://img.shields.io/github/license/lucasaita1/Quick-Shop-Ecommerce)

Este projeto é um serviço de backend desenvolvido em Spring Boot para gerenciar cestas de compras (baskets) em um sistema de e-commerce. Ele lida com a criação, atualização, visualização e pagamento de cestas, além de integrar-se com um serviço externo para consulta de produtos e gerenciar a autenticação de clientes.



##  🚀 Features

- **Gerenciamento de Cestas de Compras**: Operações CRUD completas para cestas de compras, incluindo adição e remoção de produtos.
- **Autenticação e Autorização**: Sistema de autenticação baseado em JWT para proteger as rotas da API, permitindo o registro e login de clientes.
- **Integração com API Externa**: Consumo de uma API externa (Platzi Fake Store API) para obter informações detalhadas sobre produtos.
- **Processamento de Pagamentos**: Funcionalidade para simular o pagamento de uma cesta de compras, atualizando seu status.
- **Tratamento de Exceções Global**: Gerenciamento centralizado de exceções para fornecer respostas de erro consistentes e informativas.
- **Cache com Redis**: Utilização do Redis para cache de dados, melhorando a performance de consultas frequentes.
- **Persistência de Dados com MongoDB**: Armazenamento de dados de cestas e clientes em um banco de dados

## 🏗️ Arquitetura

```
┌───────────────┐     ┌─────────────────┐     ┌─────────────────┐
│     Client      │     │                 │     │                 │
│  (User/App)   │────▶│   BasketService   │────▶│    External API   │
└───────────────┘     │  (Spring Boot)  │     │ (Platzi Products) │
                      │                 │     └─────────────────┘
                      │                 │
                      │                 │     ┌─────────────────┐
                      │                 │     │                 │
                      │                 │────▶│ MongoDB + Redis │
                      └─────────────────┘     │    (Database)   │
                                              └─────────────────┘
```

###  Stack Tecnológica

| Componente | Tecnologia | Versão | Função |
|------------|------------|--------|--------|
| **Runtime** | Java | 17+ | Linguagem de programação principal. |
| **Framework** | Spring Boot | 3.4.9 | Framework para construção de aplicações Java robustas e escaláveis. |
| **Security** | Spring Security | (Gerenciada pelo Spring Boot) | Para autenticação e autorização baseada em JWT. |
| **Authentication** | JWT | 4.4.0 | Para autenticação segura de usuários. |
| **Database** | MongoDB | 4.4+ ou 5.0+ | Banco de dados NoSQL principal. |
| **Cache** | Redis | 6.0+ ou 7.0+ | Banco de dados em memória, utilizado para cache. |
| **Data Access** | Spring Data MongoDB | (Gerenciada pelo Spring Boot) | Para integração com o banco de dados MongoDB. |
| **Data Access** | Spring Data Redis | (Gerenciada pelo Spring Boot) | Para implementação de cache e gerenciamento de sessões. |
| **Client** | Spring Cloud OpenFeign | 2024.0.2 | Cliente HTTP declarativo para facilitar a comunicação com APIs RESTful externas. |
| **Utilities** | Lombok | (Gerenciada pelo Spring Boot) | Biblioteca para reduzir código boilerplate. |
| **API Docs** | Swagger/OpenAPI | 2.2.0 | Para documentação e teste interativo da API. |
| **Build** | Maven | 3.8+ | Ferramenta de automação de build e gerenciamento de dependências. |
| **Orchestration** | Docker Compose | (Versão do Docker Compose) | Para orquestração de containers Docker (MongoDB e Redis). |


## 📋 Funcionalidades Detalhadas

### Operações de Carrinho

#### 🔐 Autenticação
- Autenticação via JWT (JSON Web Token)
- Proteção de rotas com Spring Security

#### 🆕 Criação de Carrinho
- Validação de cliente único
- Inicialização com valores zerados

#### 🔍 Consulta de Carrinho
- Busca por ID do cliente
- Retorno de itens com detalhes completos
- Cálculo de totais em tempo real

#### ✏️ Atualização de Carrinho
- Adição/remoção de produtos
- Alteração de quantidades
- Recálculo automático de valores

#### 🗑️ Finalização/Remoção
- Fechamento de carrinho após compra
- Liberação para novo carrinho

## 📌 Como Começar

Para configurar e executar o projeto BasketService localmente, siga as instruções abaixo:

### Pré-requisitos

Certifique-se de ter os seguintes softwares instalados em sua máquina:

- Java Development Kit (JDK) 17 ou superior
- Maven 3.x
- Docker e Docker Compose (para executar MongoDB e Redis)

### Configuração do Ambiente

1.  **Clone o repositório:**

    ```bash
    git clone <URL_DO_SEU_REPOSITORIO>
    cd BasketService
    ```

2.  **Variáveis de Ambiente:**

    Crie um arquivo `.env` na raiz do projeto com as seguintes variáveis (se aplicável, para chaves JWT, etc.):

    ```
    # Exemplo: Chave secreta para JWT
    JWT_SECRET=sua_chave_secreta_aqui
    ```

3.  **Configuração do Banco de Dados e Cache (Docker Compose):**

    O projeto utiliza MongoDB e Redis. Você pode iniciá-los facilmente usando Docker Compose:

    ```bash
    docker-compose up -d
    ```

    Isso iniciará os contêineres do MongoDB e Redis em segundo plano.

4.  **Configuração do `application.properties`:**

    Verifique o arquivo `src/main/resources/application.properties` para garantir que as configurações de conexão com MongoDB e Redis estejam corretas. As configurações padrão são:

    ```properties
    spring.data.mongodb.host=localhost
    spring.data.mongodb.port=27017
    spring.data.mongodb.database=basket-service

    spring.data.redis.host=localhost
    spring.data.redis.port=6379
    spring.data.redis.password=sa

    spring.cache.redis.time-to-live=60000

    basket.client.platzi=https://api.escuelajs.co/api/v1
    ```

### Executando a Aplicação

1.  **Compile o projeto usando Maven:**

    ```bash
    mvn clean install
    ```

2.  **Execute a aplicação Spring Boot:**

    ```bash
    mvn spring-boot:run
    ```

    A aplicação estará disponível em `http://localhost:8080` (ou na porta configurada).




## 🎯 Endpoints da API

A API do BasketService expõe os seguintes endpoints:

### Autenticação (`/client/auth`)

| Método | Endpoint      | Descrição           | Requisição                                 | Resposta                                  |
|--------|---------------|---------------------|--------------------------------------------|-------------------------------------------|
| `POST` | `/register`   | Registra um novo cliente | `ClientRequest` (name, email, CPF, password) | `ClientResponse` (id, email)              |
| `POST` | `/login`      | Autentica um cliente | `LoginRequest` (email, password)           | `String` (JWT Token)                      |

### Cesta de Compras (`/basket`)

| Método | Endpoint      | Descrição                     | Requisição                                 | Resposta                                  |
|--------|---------------|-------------------------------|--------------------------------------------|-------------------------------------------|
| `GET`  | `/{id}`       | Obtém uma cesta por ID        | -                                          | `Basket`                                  |
| `GET`  | `/{id}/client`| Obtém cestas de um cliente    | -                                          | `List<Basket>`                            |
| `POST` | `/`           | Cria uma nova cesta           | `BasketRequest` (clientId, products)       | `Basket`                                  |
| `PUT`  | `/{id}`       | Atualiza uma cesta existente  | `BasketRequest` (clientId, products)       | `Basket`                                  |
| `PUT`  | `/{id}/payment`| Processa o pagamento de uma cesta | `PaymentRequest` (paymentMethod, amount)   | `Basket`                                  |

### Produtos (`/products`)

| Método | Endpoint      | Descrição                     | Requisição                                 | Resposta                                  |
|--------|---------------|-------------------------------|--------------------------------------------|-------------------------------------------|
| `GET`  | `/`           | Obtém todos os produtos       | -                                          | `List<PlatzProductResponse>`              |
| `GET`  | `/{id}`       | Obtém um produto por ID       | -                                          | `PlatzProductResponse`                    |




## 📚 Documentação Swagger

A documentação interativa da API está disponível via Swagger UI. Após iniciar a aplicação, acesse:

`http://localhost:8080/swagger-ui.html`

Você pode usar esta interface para explorar os endpoints, testar requisições e entender a estrutura das respostas

## 💬 Exemplos de input e respostas das rotas

### Autenticação

#### Login
```http
POST client/auth/login
Content-Type: application/json

{
  "email": "cliente@email.com",
  "password": "senha123"
}

Response:
{
  "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  }

```

### Endpoints do Carrinho
> **Nota**: Todos os endpoints requerem autenticação via Header `Authorization: Bearer {token}`

#### Buscar Carrinho Atual
```http
GET /basket/{id}
Authorization: Bearer {jwt_token}
```

#### Criar/Obter Carrinho
```http
POST /basket
Authorization: Bearer {jwt_token}
Content-Type: application/json

{
    "clientId":"68b3847a9d2e063128e3477c",
    "products":[
        {
            "id":1,
            "quantity":3
        },
        {
            "id":9,
            "quantity":2
        }
       
    ]
    
}
```

#### Atualizar Quantidade de Item
```http
PUT /basket/{Id}
Authorization: Bearer {jwt_token}
Content-Type: application/json

{
  "quantity": 3
}
```

#### Atualizar Status
```http
PUT /basket/{Id}/paymethod
Authorization: Bearer {jwt_token}
Content-Type: application/json

{
    "paymentMethod":"CREDIT"
  
}
```


### Exemplo de Response do Carrinho

```json
{
    "id": "68ad4bd931d97e78ecb596a4",
    "client": "2",
    "totalPrice": 1180,
    "products": [
        {
            "id": 2,
            "title": "buku cerita rakha",
            "price": 1000,
            "quantity": 1
        },
        {
            "id": 4,
            "title": "Classic Grey Hooded Sweatshirt",
            "price": 90,
            "quantity": 2
        }
    ],
    "status": "OPEN"
}
  #OU/OR

{
    "id": "68b002167ca33a05113b9f20",
    "client": "1",
    "totalPrice": 15020,
    "products": [
        {
            "id": 8,
            "title": "Socks",
            "price": 20,
            "quantity": 1
        },
        {
            "id": 5,
            "title": "Classic Black espain Sweatshirt",
            "price": 5000,
            "quantity": 3
        }
    ],
    "status": "CLOSE",
    "paymentMethod": "CREDIT"
}
```

### Códigos de Status HTTP

| Status | Descrição |
|--------|-----------|
| `200` | Operação realizada com sucesso |
| `201` | Carrinho/item criado com sucesso |
| `400` | Dados inválidos na requisição |
| `401` | Token JWT inválido ou expirado |
| `403` | Acesso negado ao recurso |
| `404` | Carrinho ou item não encontrado |
| `500` | Erro interno do servidor |


## 🤝 Contribuindo

Contribuições são bem-vindas! Se você deseja contribuir com este projeto, siga estas etapas:

1.  Faça um fork do repositório.
2.  Crie uma nova branch para sua feature (`git checkout -b feature/sua-feature`).
3.  Faça suas alterações e adicione testes, se aplicável.
4.  Commit suas alterações (`git commit -m 'feat: Adiciona nova feature'`).
5.  Envie para a branch (`git push origin feature/sua-feature`).
6.  Abra um Pull Request.

Por favor, certifique-se de que seu código siga os padrões de qualidade do projeto e que todos os testes passem.

### Padrões de Código

- Seguir convenções Java e Spring Boot
- Documentar APIs com OpenAPI/Swagger
- Usar commits convencionais

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Lucas Aita**
- GitHub: [@lucasaita1](https://github.com/lucasaita1)
- LinkedIn: [Lucas Aita](https://linkedin.com/in/lucas.aita)

---

<div align="center">

**Quick Shop E-commerce** - Microserviço de Carrinho de Compras 🛒

Desenvolvido com ❤️ por Lucas Aita

</div>
