# Запускаем PostgreSQL в Docker

1) Надо подготовить образ Postgres https://hub.docker.com/_/postgres необходимо скачать версию 14.15-alpine3.21

2) И запустить контейнер с помощью команды docker run --name danik-pg -p 5432:5432 -e POSTGRES_USER=danik -e
   POSTGRES_PASSWORD=danikmay -e POSTGRES_DB=danik_db -d postgres:14.15-alpine3.21

# Запускаем проект в IntelliJ IDEA

1) В каталоге danik/may/app находится SpringBootApplication
2) Для запуска приложения понадобится java17

# Примеры запросов с данными на вход и выход

1) http://localhost:8080/auth/sign-up - запрос на регистрацию пользователя и получение JWT токена

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

3) http://localhost:8080/check/get-admin - запрос на получения роли админа
4) http://localhost:8080/task/create - запрос на добавление задачи

* Пример входных данных:

```json
{
    "header": "Задача1",
    "description": "Описание",
    "status": "в ожидании",
    "priority": "средний",
    "author": "Danik",
    "implementer": "Danik"
}
```

* Пример выходных данных:

```json
{
    "operationStatus": {
        "success": true,
        "error": null
    }
}
```

5) http://localhost:8080/task/get - запрос на получение задачи по id

* Пример входных данных:

```json
{
    "id": 1
}
```

* Пример выходных данных 1:

```json
{
    "operationStatus": {
        "success": true,
        "error": null
    },
    "body": {
        "id": 1,
        "header": "Задача1",
        "description": "Описание",
        "status": "в ожидании",
        "priority": "средний",
        "author": "Danik",
        "implementer": "Danik"
    }
}
```

* Пример выходных данных 2:

```json
{
    "operationStatus": {
        "success": false,
        "error": {
            "title": "Ошибка базы данных",
            "text": "Задача с id: 1 не найдена"
        }
    }
}
```

6) http://localhost:8080/task/get-all - запрос на получение всех задач

* Пример выходных данных:

```json
{
    "operationStatus": {
        "success": true,
        "error": null
    },
    "body": [
        {
            "id": 1,
            "header": "Задача1",
            "description": "Описание",
            "status": "в ожидании",
            "priority": "средний",
            "author": "Danik",
            "implementer": "Danik"
        },
        {
            "id": 2,
            "header": "Задача2",
            "description": "Описание",
            "status": "выполнена",
            "priority": "высокий",
            "author": "Danik",
            "implementer": "Alex"
        }
    ]
}
```

7) http://localhost:8080/task/update - запрос на обновления полей задачи

* Пример входных данных для админа:

```json
{
    "id": 1,
    "header": "Новый заголовок",
    "description": "Новое описание",
    "implementer": "Alex"
}
```

* Пример входных данных для исполнителя:

```json
{
    "id": 1,
    "status": "в ожидании"
}
```

* Пример выходных данных 1:

```json
{
    "operationStatus": {
        "success": true,
        "error": null
    }
}
```

* Пример выходных данных 2:

```json
{
    "operationStatus": {
        "success": false,
        "error": {
            "title": "Ошибка базы данных",
            "text": "Задача с id: 1 не найдена"
        }
    }
}
```

* Пример выходных данных 3:

```json
{
    "operationStatus": {
        "success": false,
        "error": {
            "title": "Ошибка валидации",
            "text": "У пользователя с именем: danik нет прав на обновление полей: header, description, priority, author, implementer"
        }
    }
}
```

* Пример выходных данных 4:

```json
{
    "operationStatus": {
        "success": false,
        "error": {
            "title": "Ошибка доступа",
            "text": "У пользователя с именем: danik нет прав на доступ к задаче с id: 1"
        }
    }
}
```

8) http://localhost:8080/task/delete - запрос на удаление задачи

* Пример входных данных:

```json
{
    "id": 1
}
```

* Пример выходных данных 1:

```json
{
    "operationStatus": {
        "success": true,
        "error": null
    }
}
```

* Пример выходных данных 2:

```json
{
    "operationStatus": {
        "success": false,
        "error": {
            "title": "Ошибка базы данных",
            "text": "Задача с id: 1 не найдена"
        }
    }
}
```

# Документация Swagger

* http://localhost:8080/swagger-ui/index.html. 