# 🚀 Desafio Técnico - Desenvolvedor(a) Full Stack

Bem-vindo(a)! Este repositório contém o desafio técnico para a vaga de **Desenvolvedor(a) Full Stack**. O objetivo é avaliar suas habilidades em desenvolvimento web, tanto no front-end quanto no back-end, além da capacidade de estruturar um projeto funcional e bem organizado.

---

## 🎯 Objetivo do Projeto

Crie uma aplicação web completa para **gerenciamento de tarefas (to-do list)**, contendo:

- Cadastro, edição e exclusão de tarefas
- Marcar tarefas como concluídas
- Listagem de tarefas pendentes e concluídas
- Interface responsiva

---

## 🧰 Tecnologias sugeridas

Você pode utilizar as tecnologias que preferir, mas sugerimos:

- **Front-end**: React, Vue ou similar
- **Back-end**: Node.js com Express (ou outra linguagem/framework de sua preferência)
- **Banco de dados**: Mysql
- **Extras** (opcional):
  - Autenticação de usuários
  - Testes automatizados
  - Deploy em ambiente gratuito (Vercel, Netlify, Render, etc.)

---

## ✅ Requisitos

- Fazer um fork deste repositório antes de iniciar o desenvolvimento
- Utilizar Git para versionamento (incluindo histórico de commits)
- Criar um `README.md` explicando como rodar o projeto
- Boa organização de pastas e código
- Seguir boas práticas de desenvolvimento
- (Opcional) Link do projeto publicado online

---

## 📦 Entrega

- Envie o link do repositório público no GitHub para o e-mail: [henrique@tarefy.com] e [douglas@tarefy.com]
- prazo de entrega é de até 5 dias corridos após o recebimento deste desafio
- layout é livre: sinta-se à vontade para usar sua criatividade ou alguma biblioteca de UI que preferir.

---

## 📘 Como rodar o projeto

Siga os passos abaixo para configurar e executar o back-end:

1.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/SEU_USUARIO/spring-todolist-backend.git](https://github.com/SEU_USUARIO/spring-todolist-backend.git)
    ```
    (Substitua `SEU_USUARIO` pelo seu nome de usuário do GitHub)

2.  **Navegue até a Pasta do Projeto:**
    ```bash
    cd spring-todolist-backend
    ```

3.  **Configuração do Banco de Dados (MySQL):**
    Abra o arquivo `src/main/resources/application.properties` (ou `application.yml`) e configure as credenciais do seu banco de dados MySQL:

    ```properties
    # src/main/resources/application.properties

    spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_seu_banco
    spring.datasource.username=seu_usuario_mysql
    spring.datasource.password=sua_senha_mysql
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    # Configuração JPA/Hibernate
    spring.jpa.hibernate.ddl-auto=update # Use 'update' para criar/atualizar tabelas automaticamente
    spring.jpa.show-sql=true # Para ver as queries SQL no console (útil para debug)
    spring.jpa.properties.hibernate.format_sql=true # Formata o SQL para melhor leitura
    ```
    * **`nome_do_seu_banco`**: Substitua pelo nome do banco de dados que você criou para o projeto no MySQL.
    * **`seu_usuario_mysql`**: Substitua pelo nome de usuário do seu MySQL (geralmente `root` em instalações locais).
    * **`sua_senha_mysql`**: Substitua pela senha do seu usuário MySQL.
    * **`spring.jpa.hibernate.ddl-auto`**: `update` é bom para desenvolvimento, pois tenta atualizar o schema. Para produção, `none` ou `validate` são mais seguros.

    **Importante:** Certifique-se de que você tem a dependência do MySQL Connector/J no seu `pom.xml` (se for Maven) ou `build.gradle` (se for Gradle).

    * **Para Maven (`pom.xml`):**
        ```xml
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.33</version> </dependency>
        ```
        (adicione dentro da seção `<dependencies>`)

    * **Para Gradle (`build.gradle`):**
        ```gradle
        dependencies {
            implementation 'mysql:mysql-connector-java:8.0.33' // Use a versão compatível
        }
        ```
        (adicione dentro da seção `dependencies { ... }`)


4.  **Construa o Projeto:**
    * **Com Maven:**
        ```bash
        mvn clean install
        ```
    * **Com Gradle:**
        ```bash
        gradle build
        ```

5.  **Execute a Aplicação:**
    * **Com Maven:**
        ```bash
        mvn spring-boot:run
        ```
    * **Com Gradle:**
        ```bash
        gradle bootRun
        ```
    A aplicação será iniciada e estará disponível em `http://localhost:8080`.

## Endpoints da API

A API RESTful oferece os seguintes endpoints para gerenciamento de tarefas:

* **`GET /api/tasks`**: Retorna todas as tarefas.
* **`GET /api/tasks/pending`**: Retorna apenas as tarefas pendentes.
* **`GET /api/tasks/completed`**: Retorna apenas as tarefas concluídas.
* **`GET /api/tasks/{id}`**: Retorna uma tarefa específica por ID
