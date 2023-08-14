
# Demo spring-boot maven-modular application

---

### Description:

Modular maven-based java spring-boot application, that written to level up knowledge of maven build tool, spring boot, and some libraries.
It is a back-end part of abstract Museum Web Site, and provide REST endpoints with basic CRUD functionality.

---

## App Architecture:

 <!-- ![app-uml-diagram]() -->

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

- Spring Web.
	- REST Controllers for endpoints
	- RestControllerAdvice for exception handling
- Swagger 3 (Springdoc Openapi)
	- Generating openAPI documentation
	- Swagger Web UI by link: [swagger-ui](http://localhost:8080/swagger-ui/index.html)
- Jackson data-bind

#### Persistence:

- Spring Data JPA
	- Hibernate as default ORM framework
	- Records DTO projections for retrieving data 
	- Transaction management by spring @Transactional
- Flyway SQL migration, for managing schema versions
- PostgreSQL as RDBMS

#### Tests:

- JUnit 5
- Spring Boot Test
	- MockMVC for testing API cals
	- SpringBootTest for integration testing
- Testcontainers for integration testing
- Instancio for generating fake test data

#### Other:

- Lombok
- Java Bean Validation (Hibernate Validation)