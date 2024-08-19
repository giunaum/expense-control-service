# Expense Control Service

## Objetivo
O objetivo é fornecer APIs para controle de gastos.

## Functionalities
* Lançar novos gastos
* Listar todos os gastos
* Consultar gastps
* Atualizar gastps
* Excusão de gastos
* Relatório

## Pré-requisites
```
*   Java 21
*   Kotlin
*   PostgreSQL
*   Maven
```

## Base URL
* /api/expense-control

## Actuactor
```
/api/expense-control/actuator/health
```

## Desenvolvedor
* Jeronimo L. Faria Junior (jeronimo.fariajunior@gmail.com)

##### Como executar a compilação local?
```console
$ mvn clean package
```

##### Como criar um banco de dados postgresql localmente?
```console
$ docker run --name postgresql -h postgresql -p 5432:5432 -e POSTGRES_PASSWORD=postgres -d postgres
```

##### Como fazer rodar do projeto localmente?
```console
$ mvn spring-boot:run -Dspring.profiles.active=local,default
```

##### Como executar os testes?
```console
$ mvn clean test
```