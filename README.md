# 📚 Library API com Spring Boot, PostgreSQL e AWS

Uma API RESTful para gerenciamento de acervo de livros e autores, desenvolvida em Java com Spring Boot e integrada a PostgreSQL, Docker e AWS. Projetada para ser segura, escalável e observável.

---

## 🚀 Principais Funcionalidades

- **CRUD de Livros e Autores**  
  Operações de criação, leitura, atualização e exclusão usando Spring Data JPA.

- **Autenticação e Autorização**  
  - 🔐 Spring Security para proteção de endpoints  
  - 🔓 Login Social com OAuth2 (Google)  
  - 🛡️ Authorization Server com JWT (JSON Web Tokens)

- **Observabilidade e Logs**  
  - 📊 Spring Boot Actuator para monitoramento e métricas  
  - 📝 SLF4J + Logback para logs estruturados

- **Deployment Contêinerizado**  
  - 🐳 Docker para empacotamento da aplicação  
  - ☁️ AWS EC2 + AWS RDS para ambiente de produção

---

## 🛠 Tecnologias e Dependências

| Camada / Ferramenta              | Tecnologia                                         |
|----------------------------------|----------------------------------------------------|
| Linguagem                        | Java 11+                                           |
| Framework                        | Spring Boot                                        |
| Persistência                     | Spring Data JPA, Hibernate                         |
| Banco de Dados                   | PostgreSQL                                         |
| Segurança                        | Spring Security, OAuth2 Client, Spring Authorization Server, JWT |
| Observabilidade                  | Spring Boot Actuator, Micrometer                   |
| Logs                             | SLF4J, Logback                                     |
| Build                            | Maven                                              |
| Contêiner                        | Docker                                             |
| Nuvem                            | AWS EC2, AWS RDS (PostgreSQL)                      |

---

## 📁 Estrutura do Projeto

```
libraryapi-postgresql/
├── src/
│ └── main/
│ ├── java/com/beletatti/libraryapi/
│ │ ├── config/ # Configurações de segurança, OAuth2, JWT e Actuator
│ │ ├── controllers/ # Endpoints REST
│ │ ├── models/ # Entidades JPA (Livro, Autor, Usuário, Papel)
│ │ ├── repositories/ # Repositórios Spring Data JPA
│ │ ├── services/ # Lógica de negócio e serviços de autenticação
│ │ └── Application.java # Classe principal Spring Boot
│ └── resources/
│ ├── application.properties
│ ├── logback.xml
│ └── public/ # Templates estáticos
├── comandos-docker.txt
├── comandos-sql.txt
├── Dockerfile
├── pom.xml
└── README.md
```

---

## ⚙️ Configuração e Execução

### 1. Clone o repositório

```bash
git clone https://github.com/Beletatti/libraryapi-postgresql.git
cd libraryapi-postgresql
```
### 2. Ajuste das Variáveis de Ambiente
No arquivo application.properties, configure as variáveis de ambiente:

```
# Banco de dados PostgreSQL
spring.datasource.url=jdbc:postgresql://<HOST_RDS>:5432/librarydb
spring.datasource.username=<USUARIO>
spring.datasource.password=<SENHA>

# OAuth2 Client (Google)
spring.security.oauth2.client.registration.google.client-id=<GOOGLE_CLIENT_ID>
spring.security.oauth2.client.registration.google.client-secret=<GOOGLE_CLIENT_SECRET>
spring.security.oauth2.client.registration.google.scope=openid,profile,email

# JWT Authorization Server
jwt.secret=<SEGREDO_JWT_CUSTOMIZADO>
jwt.expiration-ms=3600000

# Actuator
management.endpoints.web.exposure.include=health,info,metrics,loggers
management.endpoint.health.show-details=always
```
### 3. Banco de Dados
Use o arquivo comandos-sql.txt para criar o schema e as tabelas no banco PostgreSQL (local ou AWS RDS).

---

### 4. Build e Run

```
./mvnw clean package
java -jar target/libraryapi-postgresql-0.0.1-SNAPSHOT.jar
```

A aplicação estará disponível em:
📍 http://localhost:8080

---

🐳 Docker

### 1. Build da imagem

```
docker build -t libraryapi-postgres .
```

### 2. Rodar o container

```
docker run -d \
  -e SPRING_DATASOURCE_URL=jdbc:postgresql://<HOST_RDS>:5432/librarydb \
  -e SPRING_DATASOURCE_USERNAME=<USUARIO> \
  -e SPRING_DATASOURCE_PASSWORD=<SENHA> \
  -e SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_ID=<CLIENT_ID> \
  -e SPRING_SECURITY_OAUTH2_CLIENT_REGISTRATION_GOOGLE_CLIENT_SECRET=<CLIENT_SECRET> \
  -p 8080:8080 \
  libraryapi-postgres
```
### ☁️ Deploy na AWS

Recursos Utilizados:

***EC2: Instância Ubuntu configurada com Docker.***

***RDS: PostgreSQL provisionado com acesso externo ou via VPC.***

Passos:
- Suba o container Docker na EC2.
- Configure os Security Groups da EC2 para permitir:
- Porta 22 (SSH)
- Porta 8080 (API)
- Conecte-se ao RDS via JDBC usando as credenciais configuradas.
- (Opcional) Gerencie segredos com o AWS Secrets Manager ou SSM Parameter Store.

### 📝 Comandos SQL
O arquivo comandos-sql.txt inclui:

- Criação do banco de dados e schema
- Scripts para inserir autores, livros, usuários e funções
- Permissões básicas para funcionamento da segurança e autenticação

### ℹ️ Dicas de Uso

- Acesse /actuator/health para verificar a saúde da aplicação.
- Use /oauth2/authorization/google para iniciar o fluxo de login social com o Google.
- Após login, utilize o token JWT retornado no header:
```
Authorization: Bearer <token>
```
Para acessar rotas protegidas da API.








