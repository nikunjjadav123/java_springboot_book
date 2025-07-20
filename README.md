# Spring Boot Project

This is a Spring Boot application that provides Simple Book REST API using H2 Database.

## Prerequisites

Before you run the project, make sure you have the following installed:

- Java 17 (or Java 11 depending on your project)
- Maven 3.6+ or Gradle
- Git (optional, if you're cloning the project)
- IDE like IntelliJ IDEA or Eclipse (optional but recommended) , VS Code ( Recommanded ).

### Set Up Spring Boot Backend
  1. Create Spring Boot Project
  2. Use [Spring Initializr](https://start.spring.io/) or your IDE.
  3. Add dependencies
     - Spring Web
     - Spring Boot DevTools
     - Spring Data JPA (optional)
     - MySQL/PostgreSQL (if using DB)
     - Spring Security (optional)

## Getting Started

1. Clone the repository
git clone https://github.com/nikunjjadav123/java_springboot_book.git
cd java_springboot_book

2. Build the project
  A) Using Maven
      ./mvnw clean install
  B) Using Gradle
      ./gradlew build
   
4. Run the application
  A) Using Maven
      ./mvnw spring-boot:run
  B) Using JAR file
      java -jar target/your-project-name-0.0.1-SNAPSHOT.jar

5. Access the application
   http://localhost:8080
   
### Configuration
You can modify the application.properties or application.yml file in src/main/resources to customize configurations like:

1. server.port=8080
2. spring.datasource.url=jdbc:mysql://localhost:3306/db_name
3. spring.datasource.username=root
4. spring.datasource.password=your_password


   
