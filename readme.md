
# Login JWT

Api que valida um usuário a partir de um token JWT.


## Tabela de Conteúdos
## Status do Projeto

![](https://img.shields.io/badge/Status-Finalizado-green?style=for-the-badge&logo=appveyor)
## Funcionalidades

- Autenticação e autorização de usuários usando tokens JWT;
- Endpoint de validação.



## Documentação da API


### Realizar o login

#### POST `/login`

##### Responses

| Code | Description |
| ---- | ----------- |
| 200  | Token JWT válido com expiração em 2 horas |

### 

#### GET `/login/sucesso`
##### Responses

| Code | Description |
| ---- | ----------- |
| 200  | Retorna uma mensagem informando que o token é valido |
| 403  | Token inválido ou expirado |



## Variáveis de Ambiente

Para rodar esse projeto, você vai precisar adicionar a seguinte variáveis de ambiente:

`JWT_SECRET`: Chave do algoritmo de assinatura do token.


## Rodando localmente

Clone o projeto

```bash
  git clone git@github.com:gasfgrv/spring-jwt-login.git
```

Entre no diretório do projeto

```bash
  cd login
```

Instale as dependências

```bash
  mvn clean package -DskipTests
```

Subir banco de dados

```bash
  docker-compose up -d
```

Insira os dados do usuário nas tabelas:

```bash
  docker exec -it login_db bash
  mysql -u root -p
```

**obs:** A senha padrão do banco é *root*

```sql
  INSERT INTO perfil (nome) VALUES('ROLE_USER');
  INSERT INTO usuario (login, senha) VALUES('user', 'senha-criptografada-com-bcrypt');
  INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES(1, 1);
```

Inicie o servidor, pode ser pela IDE (Intellij ou Eclipse), ou rodando o seguinte comando:


```bash
  java -Dapi.security.token.secret=<JWT_TOKEN> -jar target/login-0.0.1-SNAPSHOT.jar
```


## Stack utilizada

**Linguagem:** Java 17

**Framework:** Spring Boot 3

**Build:** Maven 3.8

**Banco de dados:** Maria DB (via *Docker Compose*)

**Dependências:**
- spring-boot-starter-data-jpa
- spring-boot-starter-security
- spring-boot-starter-validation
- spring-boot-starter-web
- java-jwt (auth0)
- springdoc-openapi-starter-webmvc-ui
- mariadb-java-client
- lombok


## Apêndice

### Contraro da API

```yml
openapi: 3.0.1
info:
  title: login api
  description: Api que valida um usuário a partir de um token JWT
  contact:
    name: gasfgrv
    url: https://github.com/gasfgrv
    email: gustavo_almeida11@hotmail.com
  version: V1
servers:
  - url: http://localhost:8080
    description: Generated server url
paths:
  /login:
    post:
      tags:
        - autenticacao-controller
      operationId: efetuarLogin
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/DadosAutenticacao'
        required: true
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                $ref: '#/components/schemas/DadosTokenJWT'
  /login/sucesso:
    get:
      tags:
        - autenticacao-controller
      operationId: loginComSucesso
      responses:
        '200':
          description: OK
          content:
            '*/*':
              schema:
                type: string
      security:
        - bearerAuth: []
components:
  schemas:
    DadosAutenticacao:
      type: object
      properties:
        login:
          type: string
        senha:
          type: string
    DadosTokenJWT:
      type: object
      properties:
        token:
          type: string
  securitySchemes:
    bearerAuth:
      type: http
      scheme: bearer
      bearerFormat: JWT

```

## Autores

- [@gasfgrv](https://www.github.com/gasfgrv)

