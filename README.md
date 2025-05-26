# Como testar a API de Controle de Estoque

A aplicação expõe dois grupos principais de endpoints:

1. **Produtos** (`/api/produtos`)  
2. **Movimentos de Estoque** (`/api/movimentos`)  

Os exemplos abaixo usam **cURL**, mas você pode adaptar para Postman, Insomnia, HTTPie, etc.

---

## 1. Endpoints de **Produto**

| Método | URI                        | Descrição                                |
|:-------|:---------------------------|:-----------------------------------------|
| POST   | `/api/produtos`            | Criar um novo produto                    |
| GET    | `/api/produtos`            | Listar todos os produtos                 |
| GET    | `/api/produtos/{id}`       | Buscar um produto por ID                 |
| DELETE | `/api/produtos/{id}`       | Deletar um produto por ID                |
| GET    | `/api/produtos/tipo/{tipo}`| Filtrar produtos por tipo                |

### 1.1 Criar produto

```bash
curl -X POST http://localhost:8080/api/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "codigo": null,
    "descricao": "Notebook XYZ",
    "tipoProduto": "ELETRONICO",
    "valorFornecedor": 3500.00,
    "quantidadeEstoque": 10
  }'
```

**Resposta (201 Created)**  
```json
{
  "codigo": 1,
  "descricao": "Notebook XYZ",
  "tipoProduto": "ELETRONICO",
  "valorFornecedor": 3500.00,
  "quantidadeEstoque": 10
}
```

### 1.2 Listar todos

```bash
curl http://localhost:8080/api/produtos
```

**Resposta (200 OK)**  
```json
[
  { "codigo": 1, "descricao": "Notebook XYZ", ... },
  { "codigo": 2, "descricao": "Smartphone ABC", ... }
]
```

### 1.3 Buscar por ID

```bash
curl http://localhost:8080/api/produtos/1
```

- **200 OK**:  
  ```json
  { "codigo": 1, "descricao": "Notebook XYZ", ... }
  ```
- **404 Not Found**:  
  ```json
  Produto com ID 999 não encontrado.
  ```

### 1.4 Deletar por ID

```bash
curl -X DELETE http://localhost:8080/api/produtos/1
```

- **204 No Content**  
- **404 Not Found**:  
  ```json
  Produto com ID 999 não encontrado.
  ```

### 1.5 Filtrar por tipo

```bash
curl http://localhost:8080/api/produtos/tipo/ELETRONICO
```

**Resposta (200 OK)**  
```json
[
  { "codigo": 1, "descricao": "Notebook XYZ", "tipoProduto": "ELETRONICO", ... },
  …
]
```

---

## 2. Endpoints de **Movimento de Estoque**

| Método | URI                               | Descrição                                                        |
|:-------|:----------------------------------|:-----------------------------------------------------------------|
| POST   | `/api/movimentos`                 | Registrar entrada ou saída de produto                            |
| GET    | `/api/movimentos/tipo/{tipo}`     | Resumo por tipo de movimentação (SAIDA ou ENTRADA)               |
| GET    | `/api/movimentos/lucro`           | Consulta de lucro por produto                                    |

### 2.1 Registrar movimento

```bash
curl -X POST http://localhost:8080/api/movimentos \
  -H "Content-Type: application/json" \
  -d '{
    "id": null,
    "produtoId": 1,
    "quantidade": 2,
    "tipoMovimentacao": "SAIDA",
    "valorVenda": 4500.00,
    "dataVenda": "2025-05-25T21:00:00"
  }'
```

- **200 OK**:  
  ```json
  {
    "id": 1,
    "produtoId": 1,
    "nomeProduto": "Notebook XYZ",
    "quantidade": 2,
    "tipoMovimentacao": "SAIDA",
    "valorVenda": 4500.00,
    "dataVenda": "2025-05-25T21:00:00"
  }
  ```
- **400 Bad Request (estoque insuficiente)**:  
  ```json
  Saldo insuficiente para saída do produto Notebook XYZ
  ```

### 2.2 Resumo por tipo de movimentação

```bash
curl http://localhost:8080/api/movimentos/tipo/SAIDA
```

**Resposta (200 OK)**  
```json
[
  {
    "produtoId": 1,
    "nomeProduto": "Notebook XYZ",
    "tipoProduto": "ELETRONICO",
    "quantidadeDisponivel": 8,
    "quantidadeSaida": 2
  },
  …
]
```

### 2.3 Consulta de lucro por produto

```bash
curl http://localhost:8080/api/movimentos/lucro
```

**Resposta (200 OK)**  
```json
[
  {
    "produtoId": 1,
    "nomeProduto": "Notebook XYZ",
    "quantidadeTotalSaida": 2,
    "lucroTotal": 2000.00
  },
  …
]
```

---

> **Dica de uso em Postman/Insomnia**  
> - Crie uma collection com estes endpoints.  
> - Defina variável de ambiente `{baseUrl}` = `http://localhost:8080`.  
> - Use os bodies JSON acima.  
> - Você também pode explorar e testar diretamente pelo Swagger UI em `/swagger-ui.html`.  
