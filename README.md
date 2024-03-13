# Yami Demo Application

Yami Demo é uma aplicação de demonstração construída em Kotlin usando o framework Spring Boot.

## Visão Geral

Esta aplicação oferece endpoints RESTful para operações CRUD (Create, Read, Update, Delete) em entidades de um usuário.

## Tecnologias Utilizadas

- **Spring Boot** - Framework para desenvolvimento de aplicativos Java/Kotlin.
- **Spring Data JPA** - Facilita o acesso e a persistência de dados em aplicativos usando o padrão de acesso a dados.
- **Spring Web** - Fornece suporte para criar aplicativos da web, incluindo API RESTful.
- **Jackson** - Biblioteca para trabalhar com JSON em Java/Kotlin.
- **Kotlin** - Linguagem de programação moderna que roda na máquina virtual Java (JVM).
- **JUnit** - Framework de teste para Java/Kotlin.
- **Testcontainers** - Biblioteca Java/Kotlin para integração de contêineres de teste.
- **Docker** -  Plataforma para implantação e execução de aplicativos em contêineres.
- **Oracle** - Plataforma de persistência de dados.

## Pré-requisitos

- Java Development Kit (JDK) 17 ou superior.
- Maven ou Gradle instalado.
- Docker

## Configuração do Projeto

1. Clone este repositório.
2. Abra o projeto em sua IDE preferida.
3. Configure as dependências do projeto usando Maven ou Gradle.

## Executando a Aplicação
Para subir o container ocm banco de dados execute os comenados:
```json
docker build -t oracle-express:21.3.0 .
docker run -d -p 1521:1521 --name oracle-container oracle-express:21.3.0
```

Para executar a aplicação, execute a classe principal `YamiDemoApplication.kt`.

## Endpoints

- **POST /users/user** - Cria um novo usuário.
- **GET /users/** - Retorna todos os usuários com paginação.
- **GET /users/{id}** - Retorna um usuário específico pelo ID.
- **PUT /users/{id}** - Atualiza um usuário existente.
- **DELETE /users/{id}** - Remove um usuário pelo ID.

## Formato dos Dados

Para criar ou atualizar um usuário, envie uma solicitação JSON no seguinte formato:

```json
{
  "nick_name": "string",
  "name": "string",
  "birth_date": "yyyy-MM-dd'T'HH:mm:ss",
  "stack": ["string"]
}
