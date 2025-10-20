# Resume System Backend API - Trial Task Submission

## Project Overview
[cite_start]This project implements the core backend component for a next-generation Resume Building & Career Ecosystem[cite: 2]. [cite_start]The goal was to build robust APIs for managing user data, resume components, and authentication[cite: 9, 12].

## Technical Stack
| Category | Technology | Version / Framework |
| :--- | :--- | :--- |
| **Primary Framework** | Spring Boot | 3.x (Java 21) |
| **Persistence** | Spring Data JPA / Hibernate | Integrated |
| **Database** | MySQL | Configured via application.properties |
| **Build Tool** | Maven | Latest |
| **Security** | Spring Security | BCryptPasswordEncoder for hashing |
| **Utilities** | Lombok | Boilerplate reduction |

## Setup and Running the Application

1.  **Clone the Repository:** `git clone [Your GitHub Repo URL]`
2.  **Database:** Ensure a MySQL instance is running and you have created the `resume_system_db`.
3.  **Configuration:** Update `src/main/resources/application.properties` with your MySQL credentials and set `server.port=8090`.
4.  **Run:** Execute the main application class or use Maven: `./mvnw spring-boot:run`

## Key API Endpoints (Tested on Port 8090)

| Controller | Method | Endpoint | Purpose | DTO Used |
| :--- | :--- | :--- | :--- | :--- |
| **Auth** | POST | `/api/v1/auth/register` | Securely create a new user and an initial empty resume. | `UserRegisterRequest` |
| **Auth** | POST | `/api/v1/auth/login` | Validate user credentials (using `BCryptPasswordEncoder`). | `UserRegisterRequest` |
| **Resume** | GET | `/api/v1/resumes/user/{userId}` | Retrieve a user's full resume details. | *None* (Returns Entity) |
| **Resume** | PUT | `/api/v1/resumes/{resumeId}` | Update editable fields like the `professionalSummary`. | `ResumeUpdateRequest` |
| **Project** | POST | `/api/v1/projects` | **CRUD:** Add a new verified project to a resume. | `ProjectRequest` |
| **Project** | GET | `/api/v1/projects` | **CRUD:** Retrieve all projects. | *None* |
| **Project** | PUT | `/api/v1/projects/{id}` | **CRUD:** Update project details. | `ProjectRequest` |