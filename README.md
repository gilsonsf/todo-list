# Todo List API
Uma RESTFUL API simples que armazena e atualiza tarefas (TODO LIST API).

Esta aplicação usa NGINX como Load Balancer na frente de APIs REST criado com Spring Boot (Tomcat embedded), PostgreSQL como Banco de Dados e Spring Boot Admin e Actuator para gerenciar e monitorar os serviços.
Todos os componentes são executados dentro de containers Docker.
Para exemplos de uso foi gerado cURL e Postman Collection.

## Architetura Overview

![](img/docker-architecture.png)

## How-to

### Requirements

Install <b>Docker</b>: https://docs.docker.com/engine/installation/

Install <b>docker-compose</b>: https://docs.docker.com/compose/install/

### Installation

Run the following commands:
  
```
$ git clone https://github.com/gilsonsf/todo-list.git
$ cd todo-list
$ sudo docker-compose up
```

### Acesso Spring Boot Admin

http://localhost:9000/ user:admin password:admin 

### Pode usar cURL ou Postman para acessar a aplicação

#### Postman

![postman](client/TODO-LIST API.postman_collection.json)

#### cURL

##### Add Task
curl -X POST \
  http://localhost/todo \
  -H 'Content-Type: application/json' \
  -d '{
	"description": "Task To Do",
	"status": "PENDING"
}'

##### Find Task
curl -X GET http://localhost/todo/1
  
##### Find All Tasks
curl -X GET http://localhost/todo
  
##### Delete Task
curl -X DELETE  http://localhost/todo/1
  
##### Alter Task
curl -X PUT \
  http://localhost/todo/1 \
   -H 'Content-Type: application/json' \
  -d '{
	"description": "Task Done",
	"status": "COMPLETED"
}'


### Screenshots
