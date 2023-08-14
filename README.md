
# Demo spring-boot maven-modular application

---

### Description:

Modular maven-based java spring-boot application, written to level up knowledge of maven build tool, spring boot, and some libraries.
It is a back-end part of the abstract Museum Website and provides REST endpoints with basic CRUD functionality.

---

## App Architecture:

> The project has basic 'onion' or three-layer architecture.

 ![app-uml-diagram](Project-simple-diagram.png)
#### Modules:

1. Domain module:
	Contains domain models of the project
2. DTO module:
	Contains Data Transfer Objects as forms for creating domain models, and retrieving them via REST API
3. Service module:
	Contains classes with transactional logic, business logic, and validation. 
4. Web module:
	 Contains REST Controllers and RestControllerAdvice classes for handling API calls and processing exceptions 
5. Libraries module:
	Shares constraints, constants and utility classes. 


---

## Project structure:

```
															on 2023-08-14
.
├── docker-compose.yml
├── Dockerfile
├── pom.xml
├── domain
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── domain
│       │   │               ├── museum
│       │   │               │   ├── Article.java
│       │   │               │   ├── Author.java
│       │   │               │   └── Event.java
│       │   │               └── users
│       │   │                   ├── Address.java
│       │   │                   └── User.java
│       │   └── resources
│       │       ├── application.yml
│       │       └── db
│       │           └── migration
│       │               └── V1__initial_schema.sql
│       └── test
│           ├── java
│           │   └── com
│           │       └── example
│           │           ├── domain
│           │           │   ├── museum
│           │           │   │   ├── ArticleTest.java
│           │           │   │   ├── AuthorTest.java
│           │           │   │   └── EventTest.java
│           │           │   └── users
│           │           │       └── UserTest.java
│           │           └── utils
│           │               └── InstancioDomainModels.java
│           └── resources
│               └── application.yml
├── dto
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── dto
│       │   │               ├── museum
│       │   │               │   ├── article
│       │   │               │   │   ├── ArticlePublishingForm.java
│       │   │               │   │   ├── ArticleWithContent.java
│       │   │               │   │   └── ArticleWithoutContent.java
│       │   │               │   ├── author
│       │   │               │   │   ├── AuthorRegistrationForm.java
│       │   │               │   │   └── AuthorShortResponse.java
│       │   │               │   └── event
│       │   │               │       ├── EventPublishingForm.java
│       │   │               │       ├── EventWithContent.java
│       │   │               │       └── EventWithoutContent.java
│       │   │               └── users
│       │   │                   ├── UserRegistrationForm.java
│       │   │                   └── UserShortResponse.java
│       │   └── resources
│       │       └── application.yml
│       └── test
│           └── java
│               └── com
│                   └── example
│                       └── dto
│                           ├── users
│                           │   └── UserRegistrationFormTest.java
│                           └── utils
│                               └── InstancioDTOModels.java
├── libraries
│   ├── pom.xml
│   └── src
│       └── main
│           ├── java
│           │   └── com
│           │       └── example
│           │           ├── constants
│           │           │   └── TestConstants.java
│           │           └── constraints
│           │               ├── domain
│           │               ├── museum
│           │               │   ├── ArticleConstraints.java
│           │               │   ├── AuthorConstraints.java
│           │               │   └── EventConstraints.java
│           │               ├── SharedConstraints.java
│           │               └── users
│           │                   └── UserConstraints.java
│           └── resources
├── repository
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── repositories
│       │   │               ├── museum
│       │   │               │   ├── ArticleRepository.java
│       │   │               │   ├── AuthorRepository.java
│       │   │               │   └── EventRepository.java
│       │   │               └── users
│       │   │                   └── UserRepository.java
│       │   └── resources
│       │       └── application.yml
│       └── test
│           └── java
│               └── com
│                   └── example
├── service
│   ├── pom.xml
│   └── src
│       ├── main
│       │   ├── java
│       │   │   └── com
│       │   │       └── example
│       │   │           └── services
│       │   │               ├── museum
│       │   │               │   ├── ArticleService.java
│       │   │               │   ├── AuthorService.java
│       │   │               │   ├── EventService.java
│       │   │               │   ├── exceptions
│       │   │               │   │   ├── ArticleNotFoundException.java
│       │   │               │   │   ├── AuthorAlreadyExistException.java
│       │   │               │   │   ├── AuthorNotFoundException.java
│       │   │               │   │   └── EventNotFoundException.java
│       │   │               │   └── impl
│       │   │               │       ├── ArticleServiceImpl.java
│       │   │               │       ├── AuthorServiceImpl.java
│       │   │               │       └── EventServiceImpl.java
│       │   │               └── users
│       │   │                   ├── exceptions
│       │   │                   │   ├── UserAlreadyExistsException.java
│       │   │                   │   └── UserNotFoundException.java
│       │   │                   ├── impl
│       │   │                   │   └── UserServiceImpl.java
│       │   │                   └── UserService.java
│       │   └── resources
│       │       └── application.yml
│       └── test
│           └── java
│               └── com
│                   └── example
└── web
    ├── pom.xml
    └── src
        ├── main
        │   ├── java
        │   │   └── com
        │   │       └── example
        │   │           └── web
        │   │               ├── ApiApplication.java
        │   │               ├── config
        │   │               │   └── AppConfig.java
        │   │               ├── exceptionhandler
        │   │               │   └── RestExceptionHandler.java
        │   │               ├── museum
        │   │               │   └── controllers
        │   │               │       ├── ArticleController.java
        │   │               │       ├── AuthorController.java
        │   │               │       └── EventController.java
        │   │               └── users
        │   │                   └── UserController.java
        │   └── resources
        │       └── application.yml
        └── test
            └── java

```

---
## Features and technology stack:

#### Main properties:

- Language: Java-17
- Build tool: Apache Maven (Multi-modular project structure)
- Main framework: Spring Boot 3.1.2
- CI: GitHub Actions
- Deployment: Docker with Docker Compose plugin

#### WEB:

- [Spring Web](https://spring.io/projects/spring-framework).
	- REST Controllers for endpoints
	- RestControllerAdvice for exception handling
- Swagger ([Springdoc OpenAPI](https://springdoc.org/))
	- Generating openAPI documentation
	- Swagger Web UI by link: [swagger-ui](http://localhost:8080/swagger-ui/index.html)
- [Jackson databind](https://github.com/FasterXML/jackson-databind)

#### Persistence:

- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
	- Hibernate as default ORM framework
	- Records DTO projections for retrieving data 
	- Transaction management by spring @Transactional
- [Flyway](https://flywaydb.org/) SQL migration, for managing schemes versions
- [PostgreSQL](https://www.postgresql.org/) as RDBMS

#### Tests:

- [JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
- [Mockito](https://site.mockito.org/)
- [Spring Boot Test](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/context/SpringBootTest.html)
	- MockMVC for testing API cals
	- SpringBootTest for integration testing
- [Testcontainers](https://testcontainers.com/guides/getting-started-with-testcontainers-for-java/) for integration testing
- [Instancio](https://www.instancio.org/getting-started/) for generating fake test data

#### Other:

- [Lombok](https://projectlombok.org/features/)
- Java Bean Validation ([Hibernate Validation](https://hibernate.org/validator/))

### How you can try it:

> Now the project is in development, and not everything should work perfectly fine 😊.  
> But I fix all issues as soon as possible 😅.  

__What do you need:__

1. You should have Docker with Docker compose plugin (or Docker desktop) installed on your machine. Link with instructions: [Get Docker](https://docs.docker.com/get-docker/)
2. Java 17 installed. I recommend using [SDK-man](https://sdkman.io/) for this. It is not so easy on Windows, but it is worth it. (Link on GitHub post: [Using SDKMAN! with git for Windows shell (git bash)](https://gist.github.com/gavvvr/7d90014adefa8b27fe3b0e1c2a0e6485#using-sdkman-with-git-for-windows-shell-git-bash)).
3. And that\`s it 😎.

__Installation steps:__

1. Clone this repository.

3. Run this command in your terminal (on Linux and Mac), Power-Shell or Git-bash (on Windows), but first of all move to the root of the project:
```bash
docker compose up -d
```

3. Run another command:
```bash
./mvnw clean install
```

4. And the last:
```bash
java -jar web/target/*.jar 
```

5. Open link in your browser: [swagger-ui](http://localhost:8080/swagger-ui/index.html)

6. Test endpoints with the Swagger UI 😀

> In the future I will change `docker-compose.yml` file, and steps 3 and 4 would be unnecessary. Image of the application would be pulled from GitHub packages 🚀 by docker-compose script.

