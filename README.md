# Santander Tecnologia Challenge

[![N|Solid](https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTgTf4R8_Un5bL4JFXVo1sakNGPJPsrU7xL2sdsLqIYtw&usqp=CAU&ec=45690268)]()

## Technology used
The challenge was created using the following tools.

  - Java 8
  - Spring Boot
  - Gradle
  - Spring Security
  - Swagger
  - H2 database

## Installation

Follow the next steps to use

```sh
$ git clone
$ cd santanderChallenge
$ ./gradlew build
$ ./gradlew bootRun
```

 - Default port 8080.


## Authentication

The app by deafult counts with two users:
 - User 1 (Admin)
   - user: user1
    - password: password1
- User 3 (Normal)
  - user: user3
  - password: password3

 1 - Log with the user 
  - request
    - localhost:8080/login
  - body
     ```sh
    {
	  "user":"user1",
	  "password": "password1"
    }
    ```
 2 - Take the "Bearer Token" from headers


## Swagger
[![N|Solid](https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTwdFKqBMgBqxCu2kPtfWAPyWtU4OObc_igMQ&usqp=CAU)](http://localhost:8080/swagger-ui.html)


## Using
 - Use swagger UI + Bearer Token
 
 
   [![N|Solid](https://miro.medium.com/max/672/1*wZZzz51HAzoA1cUQrlkC_w.png)]()
    -  Value: Bearer + JwtToken
 - Use postman collection (Santander.postman_collection.json) placed in resources

### Enjoy :)

 
 
