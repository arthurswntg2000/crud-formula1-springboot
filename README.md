# 🏎️ F1 CRUD API - Spring Boot & PostgreSQL

API RESTful desenvolvida em Java utilizando Spring Boot para gestão de escuderias e carros de Fórmula 1.

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3**
- **Spring Data JPA**
- **PostgreSQL**
- **Lombok**
- **Maven**

## 📌 Endpoints da API

### Escuderias (`/api/escuderias`)
- `GET /api/escuderias` - Lista todas as escuderias e respetivos carros
- `GET /api/escuderias/{id}` - Procura uma escuderia por ID
- `POST /api/escuderias` - Cadastra uma nova escuderia
- `PUT /api/escuderias/{id}` - Atualiza os dados de uma escuderia
- `DELETE /api/escuderias/{id}` - Remove uma escuderia

### Carros (`/api/carros`)
- `GET /api/carros` - Lista todos os carros
- `GET /api/carros/{id}` - Procura um carro por ID
- `POST /api/carros/escuderia/{escuderiaId}` - Cadastra um carro vinculado a uma escuderia
- `PUT /api/carros/{id}` - Atualiza os dados de um carro
- `DELETE /api/carros/{id}` - Remove um carro

## ⚙️ Como Executar o Projeto Localmente

1. Clonar o repositório:
   ```bash
   git clone [https://github.com/arthurswntg2000/crud-formula1-springboot.git](https://github.com/arthurswntg2000/crud-formula1-springboot.git)
