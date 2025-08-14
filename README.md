# MagicFridgeAI 🪄🥗

**MagicFridgeAI** é uma aplicação backend desenvolvida em **Java + Spring Boot** que ajuda a gerenciar ingredientes disponíveis e gerar sugestões de receitas com base neles, integrando Inteligência Artificial.

## 🚀 Funcionalidades

- 📦 **Gerenciamento de ingredientes** — Cadastrar, listar, atualizar e remover itens do estoque.
- 🍳 **Sugestão de receitas** — Geração de receitas personalizadas com base nos ingredientes disponíveis.
- 🤖 **Integração com IA** — Utiliza API de IA para sugerir receitas criativas e práticas.
- 🌐 **API REST** — Disponível para consumo em front-ends, aplicativos móveis ou integrações.

## 🛠️ Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Web**
- **Spring Data JPA**
- **Hibernate**
- **Docker & Docker Compose**
- **Maven**
- Integração com API de Inteligência Artificial [Gemini e ChatGPT]

## 📂 Estrutura do Projeto

```
MagicFridgeAI/
├── src/main/java/dev/siqueira/magicfridgeai/ # Código fonte principal
│ ├── config/ # Configurações (ex: WebClient)
│ ├── controller/ # Endpoints REST
│ ├── model/ # Modelos de dados
│ ├── repository/ # Interfaces JPA
│ └── service/ # Regras de negócio
├── pom.xml # Dependências Maven
├── docker-compose.yml
└── .env # Variáveis de ambiente
```


## ⚙️ Configuração e Execução

### 1️⃣ Clonar o repositório
```bash
git clone https://github.com/PedroHSiqueira/MagicFridgeAI.git
cd MagicFridgeAI
```

### 2️⃣ Configurar Variáveis de Ambiente
```bash
DATABASE_URL=jdbc:postgresql://localhost:5432/food_item
DATABASE_USERNAME=sa
DATABASE_PASSWORD=magic

OPENAI_API_KEY= sua Chave da OpenAi
GOOGLE_API_KEY= sua Chave da Google Gemini
```

### 3️⃣ Rodar o Doscker-Compose
```bash
docker-compose up --build
```

## 📚 Endpoints Principais
| Método |  Endpoint  |                Descrição                |
|:------:|:----------:|:---------------------------------------:|
| GET    | /food      | Lista todos os ingredientes             |
| POST   | /food      | Cadastra um novo ingrediente            |
| GET    | /recipes   | Gera receitas com base nos ingredientes |
| DELETE | /food/{id} | Remove um ingrediente                   |

## 📚 Gerar Receita
| Método |     Endpoint     |                  Descrição                 |
|:------:|:----------------:|:------------------------------------------:|
| POST   | /generate/gemini | Cria uma Receita com IA a partir do Gemini |
| POST   | /generate/gpt    | Cria uma Receita com IA a partir do GPT    |

## 📜 Licença

Este projeto está sob a licença MIT — sinta-se livre para usar e modificar.
