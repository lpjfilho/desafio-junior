# Desafio Técnico (Nível Júnior) - Portal Parceiros

## Descrição do Desafio

A empresa ajuda parceiros a cobrar dívidas de consumidores e cobra uma **comissão**
sobre cada dívida paga. Essa comissão depende de quantos dias a dívida ficou em
atraso: quanto mais atrasada, maior o percentual cobrado.

Você vai implementar a função que calcula essa comissão.

## O que o sistema precisa fazer

1. Receber o **ID de um parceiro**.
2. Olhar todas as dívidas pagas (`PaidDebt`) daquele parceiro.
3. Para cada dívida, calcular quantos **dias de atraso** ela teve
   (diferença entre a data de pagamento e a data de vencimento).
4. Usar a **tabela de preços** do parceiro para descobrir qual o percentual de
   comissão para aquela quantidade de dias de atraso.
5. Calcular o valor da comissão (`valor da dívida * percentual / 100`).
6. Retornar o total de dívidas pagas e o total de comissão.

## Estrutura já pronta

Os modelos (`Partner`, `PriceTable`/`PriceRange`, `PaidDebt`) e os dados de
exemplo já estão prontos nos arquivos iniciais. **O que falta implementar é a
função de cálculo da comissão**, marcada com `TODO` no código:

- Go: [`service/commission.go`](go/service/commission.go)
- Java: [`CommissionService.java`](java/src/main/java/com/desafio/service/CommissionService.java)

## Exemplo

Parceiro "Parceiro 1" tem a tabela:

| Dias de atraso | Percentual |
|----------------|------------|
| 1 a 30          | 5%         |
| 31 ou mais      | 10%        |

Uma dívida de R$ 100,00 paga com 35 dias de atraso gera uma comissão de
R$ 10,00 (10% de 100).

## O que será avaliado

- O cálculo está correto?
- O código está organizado e legível?
- Os testes unitários passam (e fazem sentido)?

## Bônus (opcional)

Se sobrar tempo, exponha o cálculo em um endpoint HTTP simples
(`GET /comissao?partnerId=1`) que devolva o resultado em JSON. Não é
necessário usar nenhum framework — a biblioteca padrão da linguagem já é
suficiente (`net/http` em Go, `com.sun.net.httpserver` em Java).

## Como rodar

### Go
```bash
cd go
go run .
go test ./...
```

### Java
Requer Java 11 ou superior.
```bash
cd java
mvn test
mvn compile exec:java -Dexec.mainClass="com.desafio.Main"
```

Boa sorte! 🚀
