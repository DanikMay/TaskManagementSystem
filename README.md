# Запускаем PostgreSQL в Docker
1) Надо подготовить образ Postgres https://hub.docker.com/_/postgres необходимо скачать версию 14.15-alpine3.21

2) И запустить контейнер с помощью команды docker run --name danik-pg -p 5432:5432 -e POSTGRES_USER=danik -e POSTGRES_PASSWORD=danikmay -e POSTGRES_DB=danik_db -d postgres:14.15-alpine3.21

# Запускаем проект в IntelliJ IDEA
1) В каталоге danik/may/app находится SpringBootApplication
2) Для запуска приложения понадобится java17

# Примеры запросов с данными на вход и выход
1) http://localhost:8080/auth/sign-up - запрос на регистрацию пользоваетеля и получение JWT токена
* Пример входных данных:
```json
{
    "username": "Danik",
    "email": "danik@example.com",
    "password": "danikdanik"
}
 ```
* Пример выходных данных:
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9BRE1JTiIsImlkIjo1LCJlbWFpbCI6ImRhbmlrQGV4YW1wbGUuY29tIiwic3ViIjoiZGFuaWsiLCJpYXQiOjE3MzQzODc5NzcsImV4cCI6MTczNDUzMTk3N30.kuJSWbTeTRckz3TTk5UMO3bm4si4xiomQ6vsBqAjCnY"
}
```
2) http://localhost:8080/auth/sign-in - запрос на авторизацию пользователя и получение JWT токена
* Пример входных данных:
```json
{
    "username": "Danik",
    "password": "danikdanik"
}
```
* Пример выходных данных:
```json
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiUk9MRV9BRE1JTiIsImlkIjo1LCJlbWFpbCI6ImRhbmlrQGV4YW1wbGUuY29tIiwic3ViIjoiZGFuaWsiLCJpYXQiOjE3MzQzODgxNjMsImV4cCI6MTczNDUzMjE2M30._olDEbh52I6xrJaqEIBJCJINTDa0IE-OpSNp-r37zSY"
}
```
3) http://localhost:8080/check - запрос на проверку успешной авторизации
* Выходные данные:
> Hello, world!
4) http://localhost:8080/check/get-admin - запрос на получения роли админа
5) http://localhost:8080/check/admin - запрос на проверку получения роли админа
* Выходные данные:
> Hello, admin!
6) http://localhost:8080/task/create - запрос на добавление задачи 
* Пример входных данных:
```json
{
    "header": "Задача1",
    "description": "Описание",
    "status": "в ожидании",
    "priority": "средний",
    "author": "Danik",
    "executor": "Danik"
}
```
7) http://localhost:8080/task/get - запрос на получение задачи по id
* Пример входных данных:
```json
{
    "id": 1
}
```
* Пример выходных данных:
```json
{
    "id": 1,
    "header": "Задача1",
    "description": "Описание",
    "status": "в ожидании",
    "priority": "средний",
    "author": "Danik",
    "executor": "Danik"
}
```
8) http://localhost:8080/task/get-all - запрос на получение всех задач
* Пример выходных данных:
```json
[
    {
        "id": 1,
        "header": "Задача1",
        "description": "Описание",
        "status": "в ожидании",
        "priority": "средний",
        "author": "Danik",
        "executor": "Danik"
    },
    {
        "id": 2,
        "header": "Задача2",
        "description": "Описание",
        "status": "выполнена",
        "priority": "высокий",
        "author": "Danik",
        "executor": "Alex"
  }
]
```
9) http://localhost:8080/task/update - запрос на обновления полей задачи 
* Пример входных данных для админа:
```json
{
    "id": 1,
    "header": "Новый заголовок",
    "description": "Новое описание",
    "executor": "Alex"
}
```
* Пример входных данных для исполнителя:
```json
{
    "id": 1,
    "status": "в ожидании"
}
```
10) http://localhost:8080/task/delete - запрос на удаление задачи 
* Пример входных данных:
```json
{
    "id": 1
}
```
# Документация Swagger
* http://localhost:8080/swagger-ui/index.html. 