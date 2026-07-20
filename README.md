# 🏦 Sistema Bancário Java

Projeto desenvolvido em **Java + MySQL** com foco em aplicar conceitos de Programação Orientada a Objetos, JDBC, arquitetura em camadas e boas práticas de desenvolvimento, simulando o funcionamento de um sistema bancário real.

---

# 🚀 Tecnologias

- Java
- JDBC
- MySQL
- IntelliJ IDEA
- Git
- GitHub

---

# 📂 Arquitetura

O projeto foi organizado utilizando separação de responsabilidades.

```
src
│
├── database
├── exception
├── model
├── repository
├── service
└── Main.java
```

Cada camada possui uma responsabilidade específica:

- **model** → Entidades do sistema
- **repository** → Comunicação com o banco de dados
- **service** → Regras de negócio
- **exception** → Exceções personalizadas
- **database** → Conexão JDBC

---

# ✅ Funcionalidades

- Cadastro de clientes
- Abertura de contas
- Login por CPF e senha
- Depósito
- Saque
- Transferência entre contas
- Consulta de saldo
- Extrato bancário
- Registro de movimentações
- Atualização automática de saldo
- Numeração automática de contas
- Escolha da agência
- Escolha do tipo de conta

---

# 📚 Conceitos aplicados

- Programação Orientada a Objetos (POO)
- Encapsulamento
- Herança
- Polimorfismo
- Repository Pattern
- Service Layer
- Separação de responsabilidades
- JDBC
- SQL
- CRUD
- Tratamento de exceções personalizadas
- Arquitetura em camadas
- Persistência de dados
- Relacionamento entre entidades

---

# 🗄️ Banco de Dados

O sistema utiliza MySQL para persistência de dados.

Principais tabelas:

- cliente
- agencia
- conta
- movimentacao
- sequencia_conta

---

# 🎯 Objetivo

Este projeto faz parte da minha evolução como desenvolvedor Back-end Java.

O objetivo é desenvolver um sistema bancário cada vez mais próximo de aplicações reais, aplicando boas práticas de arquitetura, organização do código e persistência de dados.

---

# 🔄 Próximas implementações

- Criptografia de senha (BCrypt)
- Enum para TipoConta
- Enum para TipoMovimentacao
- Validação de CPF
- Encerramento de conta
- Alteração de senha
- Logs da aplicação
- Testes unitários (JUnit)
- Spring Boot
- API REST
- Spring Security
- JWT