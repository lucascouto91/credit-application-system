


<h1 align="center" style="font-weight: bold;">Credit Application System 💳</h1>


<p align="center">
    <img src="https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white" alt="Kotlin">
    <img src="https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot" alt="Spring Boot">
    <img src="https://img.shields.io/badge/Swagger-85EA2D.svg?style=for-the-badge&logo=Swagger&logoColor=black" alt="Swagger">
    <img src="https://img.shields.io/badge/H2_Database-002cd3?style=for-the-badge&logo=H2_database&logoColor=white" alt="H2 Database">
    <img src="https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens" alt="Jwt">
    <img src="https://img.shields.io/badge/Docker-2496ED.svg?style=for-the-badge&logo=Docker&logoColor=white" alt="Docker">
</p>

<p align="center">
 <a href="#funcionalidades">Funcionalidades</a> • 
 <a href="#documentacao">Documentação API</a> •
 <a href="#contribuicao">Contribuição</a>
</p>

<p align="center">
  <b>Este é um projeto simples desenvolvido durante o Bootcamp da Digital Innovation One (DIO). O projeto consiste em um backend para um sistema de aplicação de crédito, permitindo o cadastro de usuários, atualização de informações pessoais, exclusão de conta, pesquisa de seus próprios créditos e concessão de crédito por administradores.
.</b>
</p>

<h2 id="tecnologias">Tecnologias Utilizadas</h2>

- Kotlin
- Spring Boot
- Swagger
- Banco de dados H2 (em memória)
- JWT Token
- Docker

<h2 id="funcionalidades">Funcionalidades</h2>

- Cadastro de Usuário: Permite aos usuários se cadastrarem no sistema.
- Atualização de Informações Pessoais: Usuários podem atualizar suas informações pessoais, como nome, renda, etc.
- Exclusão de Conta: Usuários podem excluir suas contas do sistema.
- Pesquisa de Créditos: Usuários podem verificar suas próprias informações de crédito.
- Concessão de Crédito: Funcionalidade exclusiva para administradores, que podem conceder crédito aos usuários.

<h2 id="executar">Como Executar o Projeto</h2>

- <h3 id="docker">Executando com Docker</h3>
1. Baixe a imagem do projeto.
```bash
docker pull lucascouto91/credit-application-system:1.0.0
```
2. Execute a imagem.
```bash
docker run -p 8080:8080 d7290ab212ba
```
3. O servidor será iniciado e você poderá acessar as API através `http://localhost:8080/`.
4. O H2 database está disponível em `http://localhost:8080/h2-console`.
    - Conferir os seguintes campos:
      - Driver Class: `org.h2.Driver`
      - JDBC URL: `jdbc:h2:mem:credit_application_system_db`
      - User Name: `cami`
      - Não tem senha.

- <h3 id="IDE">Executando com IDE</h3>

1. Certifique-se de ter o JDK 11 ou superior instalado em seu sistema.
2. Clone este repositório em seu ambiente local.
```bash
git clone https://github.com/lucascouto91/credit-application-system
```
3. Abra o projeto em sua IDE preferida.
4. Execute a classe principal `CreditApplicationSystemApplication.kt`.
5. O servidor será iniciado e você poderá acessar as API através `http://localhost:8080/`.
6. O H2 database está disponível em `http://localhost:8080/h2-console`.
    - Conferir os seguintes campos:
        - Driver Class: `org.h2.Driver`
        - JDBC URL: `jdbc:h2:mem:credit_application_system_db`
        - User Name: `cami`
        - Não tem senha.

<h2 id="documentacao">Documentação da API</h2>

Para detalhes sobre os endpoints disponíveis e como usá-los, consulte a documentação da API, que estará disponível em `http://localhost:8080/swagger-ui/html` após iniciar o servidor.

<h2 id="contribuicao">📫 Contribuição</h2>

Contribuições são bem-vindas! Se você encontrar bugs, tiver sugestões de melhorias ou quiser adicionar novos recursos, fique à vontade para abrir uma issue ou enviar um pull request.

