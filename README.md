# 🏦 Sistema Bancário Java

Projeto desenvolvido em **Java + MySQL** com foco em aplicar conceitos de Programação Orientada a Objetos, JDBC, arquitetura em camadas e boas práticas de desenvolvimento, simulando o funcionamento de um sistema bancário real.

---

# 🚀 Tecnologias

- Java
- JDBC
- MySQL
- BCrypt
- IntelliJ IDEA
- Git
- GitHub

---

# 📂 Arquitetura

O projeto foi organizado utilizando separação de responsabilidades.

```text
src
│
├── app
│   └── Main.java
│
├── database
│   └── Conexao.java
│
├── exception
│   ├── ClienteNaoEncontradoException.java
│   ├── ContaNaoEncontradaException.java
│   ├── SaldoInsuficienteException.java
│   ├── SenhaInvalidaException.java
│   ├── TransferenciaInvalidaException.java
│   └── ValorInvalidoException.java
│
├── model
│   ├── Agencia.java
│   ├── Cliente.java
│   ├── Conta.java
│   ├── Movimentacao.java
│   │
│   └── enums
│       ├── TipoConta.java
│       └── TipoMovimentacao.java
│
├── repository
│   ├── AgenciaRepository.java
│   ├── ClienteRepository.java
│   ├── ContaRepository.java
│   └── MovimentacaoRepository.java
│
├── service
│   ├── AgenciaService.java
│   ├── ContaService.java
│   ├── DepositoService.java
│   ├── ExtratoService.java
│   ├── LoginService.java
│   ├── MenuContaService.java
│   ├── MovimentacaoService.java
│   ├── SaqueService.java
│   └── TransferenciaService.java
│
└── util
    ├── PasswordEncoder.java
    └── ValidacaoUtil.java
```

Cada camada possui uma responsabilidade específica:

- **model** → Entidades do sistema
- **repository** → Comunicação com o banco de dados
- **service** → Regras de negócio
- **exception** → Exceções personalizadas
- **database** → Conexão JDBC
- **util** → Utilitários e validações compartilhadas

---

# 🏛️ Diagrama da Arquitetura

```text
                         Usuário
                            │
                            ▼
                     Classe Main (app)
                            │
                            ▼
                 ┌──────────────────────┐
                 │       Services       │
                 └──────────────────────┘
          │               │               │
          ▼               ▼               ▼
   Regras de negócio  Validações    Criptografia
                         (Util)         BCrypt
          │
          ▼
      Repositories
          │
          ▼
     JDBC / MySQL
          │
          ▼
     Banco de Dados
```

---

# ✅ Funcionalidades

- Cadastro de clientes
- Abertura de contas
- Login por CPF e senha
- Criptografia de senha com **BCrypt**
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
- Validações centralizadas
- Tratamento de exceções personalizadas

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
- Enum
- BCrypt (Hash de senhas)
- Refatoração
- Clean Code

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

- Validação de CPF
- Encerramento de conta
- Alteração de senha
- Bloqueio de conta após tentativas inválidas
- Logs da aplicação
- Testes unitários (JUnit)
- Spring Boot
- API REST
- Spring Security
- JWT