# WARP.md

This file provides guidance to WARP (warp.dev) when working with code in this repository.

## Project Overview

This is a Spring Boot 3.5.5 web application (Exercise 06 - baitap06) that implements a Category Management System with CRUD operations. The application uses:
- Java 17
- Spring Boot with Spring MVC, Spring Data JPA, and Thymeleaf
- SQL Server database
- Maven for build management
- WAR packaging for deployment

## Build and Development Commands

### Essential Commands

**Build the project:**
```powershell
.\mvnw clean compile
```

**Run the application:**
```powershell
.\mvnw spring-boot:run
```

**Run tests:**
```powershell
.\mvnw test
```

**Package the application:**
```powershell
.\mvnw clean package
```

**Run a specific test class:**
```powershell
.\mvnw test -Dtest=Baitap06ApplicationTests
```

**Skip tests during build:**
```powershell
.\mvnw clean package -DskipTests
```

### Database Requirements

The application requires SQL Server with:
- Server: `localhost:1433`
- Database: `DBUser`
- Default credentials: `sa/1234` (modify in `application.properties`)
- The `Category` table must exist in `dbo` schema

## Architecture Overview

### Package Structure
The application follows Spring Boot's standard layered architecture:

```
org.example.baitap06/
├── entity/          # JPA entities (Category)
├── repository/      # Data access layer (JPA repositories)  
├── service/         # Business logic layer
├── controller/      # Web controllers (MVC)
└── [main classes]   # Application entry point
```

### Key Architectural Patterns

**MVC Pattern:**
- Controllers handle HTTP requests and return view names
- Services contain business logic and coordinate between layers
- Repositories handle data persistence using Spring Data JPA

**Entity Structure:**
- `Category` entity maps to `dbo.Category` table
- Uses JPA annotations with explicit table/schema mapping
- Implements automatic timestamp management via `@PrePersist`/`@PreUpdate`

**Data Flow:**
1. Controller receives HTTP requests
2. Controller calls Service methods
3. Service coordinates business logic and calls Repository
4. Repository interacts with SQL Server database
5. Results flow back up through Service to Controller
6. Controller returns Thymeleaf view name for rendering

### Template Architecture
- Uses Thymeleaf Layout Dialect for template composition
- `layout.html` provides base structure with fragments
- Category-specific templates in `templates/category/`
- Fragment-based header/footer in `templates/fragments/`

### Key Features Implemented
- **CRUD Operations:** Full Create, Read, Update, Delete for categories
- **Search Functionality:** Search by name or description with pagination
- **Pagination:** Server-side pagination with configurable page sizes
- **Form Handling:** Unified form for create/edit operations
- **Delete Confirmation:** Separate confirmation page before deletion

### Database Configuration Notes
- Uses legacy JPA naming strategy to match existing database schema
- Hibernate DDL auto is set to "none" - database schema must exist
- SQL logging is enabled for development debugging

### Important Implementation Details
- Controller uses constructor injection for services
- All database operations are wrapped in service layer
- Error handling uses `IllegalArgumentException` for not-found scenarios
- Home controller redirects root path to `/categories`
- Application runs on port 8080 by default