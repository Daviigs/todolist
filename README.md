# Todo List - Gerenciador de Tarefas

## Descrição
O Todo List é uma aplicação web para gerenciamento de tarefas, desenvolvida utilizando Java 21 e Spring Boot. Ela permite aos usuários adicionar, editar e listar tarefas. A aplicação segue a arquitetura RESTful e utiliza um banco de dados em memória H2 para testes.

## Funcionalidades
- Criar usuários: Permite o cadastro de novos usuários.
- Criar tarefas: Adicione novas tarefas associadas a um usuário.
- Listar tarefas: Visualize todas as tarefas criadas pelo usuário.
- Editar tarefas: Modifique o título, descrição, prioridade e datas de uma tarefa.
- Restrição de edição: Apenas o usuário que criou a tarefa pode editá-la.

## Tecnologias Utilizadas
- **Java 21**
- **Spring Boot**: Framework para desenvolvimento de aplicações Java baseadas em microserviços.
- **H2 Database**: Banco de dados em memória para facilitar o teste da aplicação.
- **Spring Data JPA**: Para persistência de dados.
- **Spring Web**: Para construção de APIs RESTful.
- **Postman**: Para testar as APIs.

## Requisitos
- Java 21 ou superior.
- Maven para gerenciamento de dependências.

## Como Rodar o Projeto
1. Clone este repositório:
   ```bash
   git clone https://github.com/Daviigs/todolist.git
   ```
2. Acesse o diretório do projeto:
   ```bash
   cd todolist
   ```
3. Compile e rode a aplicação:
   ```bash
   mvn spring-boot:run
   ```
4. A aplicação estará rodando em `http://localhost:8080`.

## Endpoints da API

### Criar um Usuário
**POST /users/**

**Requisição:**
```json
{
  "name": "Davi Miqueias",
  "username": "davimi",
  "password": "12345"
}
```

### Criar uma Tarefa
**POST /tasks/**

> Quando uma tarefa é criada, ela automaticamente recebe o ID do usuário criador.

**Requisição:**
```json
{
  "description": "Tarefa de estudar",
  "priority": "BAIXA",
  "title": "Titulo",
  "dateStart": "2025-02-10T12:30:00",
  "dateEnd": "2025-02-10T15:30:00",
}
```

### Listar Tarefas do Usuário
**GET /tasks?userId={id}**

### Editar uma Tarefa
**PUT /tasks/{id}**

> Somente o usuário que criou a tarefa pode editá-la.

**Requisição:**
```json
{
  "description": "Nova descrição da tarefa",
  "priority": "ALTA",
  "title": "Novo Título",
  "dateStart": "2025-02-11T09:00:00",
  "dateEnd": "2025-02-11T12:00:00"
}
```

## Contribuição
Sinta-se à vontade para contribuir para este projeto. Você pode fazer isso de várias maneiras, como corrigindo bugs, melhorando a documentação ou implementando novas funcionalidades.

1. Faça um fork deste repositório.
2. Crie uma branch para sua feature:
   ```bash
   git checkout -b feature/nome-da-feature
   ```
3. Faça suas modificações e commit com uma mensagem clara:
   ```bash
   git commit -m "Adiciona nova funcionalidade"
   ```
4. Push para a branch:
   ```bash
   git push origin feature/nome-da-feature
   ```
5. Abra um pull request.

## Licença
Este projeto está licenciado sob a MIT License.

