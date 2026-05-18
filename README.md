# ShopPlus AI

Backend de e-commerce inteligente com integração com IA
para análise de dados, avaliações de produtos e insights automatizados.

<details>

## Sobre o projeto

O ShopPlusAI Backend é uma API REST desenvolvida com foco em um sistema de e-commerce moderno, que além das funcionalidades tradicionais (produtos, usuários, avaliações), utiliza Inteligência Artificial para análise de sentimentos e geração de insights a partir de avaliações de produtos.

O objetivo do projeto é simular um ambiente real de plataforma de vendas com capacidade de análise inteligente de dados.

Ele não possuí as funcionalidades básicas de um e-commerce, apenas de cadastrar informações e focado em análise com IA.

</details>

<details>

## Funcionalidades

* Usuarios - CRUD básico;
* Produtos - CRUD, organização por categoria;
* Avaliações - CRUD básico;
* Módulo de IA
  * Análise de Sentimento das avaliações;
  * Chatbot conversacional;
  * Geração de insights sobre os produtos;
  * Base para dashboards inteligentes;

</details>


<details>

## IA no Projeto

O sistema possuí módulo de IA completo responsável por:
* Interpretar avaliações de usuários;
* Classificar sentimento (positivo, neutro, negativo);
* Gerar respostas estruturadas para consumo no backend;
* Apoiar decisões de negócio com base nos dados coletados;

</details>

<details>

## Tecnologias Utilizadas

* Java 17
* Spring Boot 3.5.14
* Spring Data JPA
* Jackson (ObjectMapper)
* Bean Validation
* Lombok
* Gemini Client

</details>

<details>

## Como executar o projeto

#### Pré-requisitos
* Java 17+
* Maven
* Banco de dados configurado

#### Passos

````powershell
# Clonar o repositório
git clone https://github.com/cyronp/ShopPlusAi-Backend.git
````

````properties
# Configure seu arquivo application.properties

spring.application.name=shopplusai

spring.datasource.url=[CAMINHO DO BANCO DE DADOS]
# Caso for utilizar outro banco será necessário alterar o drive abaixo e as migrations
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver 
spring.datasource.username=[USUARIO DO BANCO]
spring.datasource.password=[SENHA]

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Configure a API KEY abaixo nas variaveis de ambiente
spring.ai.google.genai.api-key=${GEMINI-API-KEY}
spring.ai.google.genai.chat.options.model=gemini-3-flash-preview
````

````powershell
# Entrar no projeto
cd ShopPlusAi-Backend

# Rodar a aplicação
mvn spring-boot:run
````

## Documentação da API

Este projeto utiliza Springdoc OpenAPI (Swagger) para documentação interativa da API.

A documentação permite:

* Visualizar todos os endpoints disponíveis
* Testar requisições diretamente pelo navegador
* Entender os contratos da API (DTOs, parâmetros e respostas)

### Acesso a documentação

Após rodar o projeto acesse:
````http-explyt
http://localhost:8080/swagger-ui.html
````
ou
````http-explyt
http://localhost:8080/swagger-ui/index.html
````

## Testes da API (Insominia)

Este projeto possui uma coleção de testes da API utilizando Insomnia, contendo todos os endpoints principais do sistema.

#### Arquivo de testes

o arquivo está disponivel no repositório:
````http-explyt
/docs/insominia/shopplus-insominia.yaml
````

</details>
