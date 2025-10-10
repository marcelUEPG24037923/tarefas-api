# API REST - Gerenciador de Tarefas

## Objetivo
Este projeto é uma API RESTful desenvolvida em **Java 17** com **Spring Boot 3**, que permite gerenciar uma lista de tarefas (To-Do List).  
A aplicação suporta operações de **CRUD completo** (criar, consultar, atualizar e deletar) além de filtros e atualização de status.  

---

## Tecnologias Utilizadas
- Java 17  
- Spring Boot 3  
- Spring Web  
- Spring Data JPA  
- PostgreSQL  
- Maven  

---

## Estrutura da Entidade `Tarefa`
- **id (long)** → Identificador único da tarefa (PK, auto gerado)  
- **descricao (String)** → Texto descritivo da tarefa (máx. 100 caracteres, obrigatório)  
- **concluida (boolean)** → Status da tarefa (padrão = false)  
- **prioridade (int)** → Prioridade da tarefa (1 = alta, 5 = baixa, obrigatório)  

---

