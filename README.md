![Build Status](https://github.com/arevaura/pet-shelter/actions/workflows/deploy.yml/badge.svg)

# 🐾 Pet Shelter API

A modern, production-ready polyglot service for managing pet shelter records. This project demonstrates **Contract-First** development, automated testing, and CI/CD integration with both **Python FastAPI** and **Java Spring Boot** implementations.

## 🚀 Key Features

- **Design-First:** Built using an `openapi.yaml` specification.
- **Polyglot Architecture:** Both Python FastAPI and Java Spring Boot implementations.
- **Auto-Generated Models:** Python Pydantic models and Java POJOs generated from the OpenAPI schema.
- **Fully Containerized:** Separate Dockerfiles for Python and Java applications.
- **CI/CD Pipeline:** GitHub Actions automatically run tests and push Docker images to **GitHub Container Registry (GHCR)** on every push to `main`.
- **Interactive Docs:** Built-in Swagger UI for testing endpoints.

## 🛠️ Tech Stack

### Python Implementation
- **Framework:** [FastAPI](https://fastapi.tiangolo.com/)
- **Data Validation:** [Pydantic](https://docs.pydantic.dev/)
- **Testing:** [Pytest](https://docs.pytest.org/)
- **Containerization:** [Docker](https://www.docker.com/)

### Java Implementation
- **Framework:** [Spring Boot](https://spring.io/projects/spring-boot)
- **Build Tool:** [Maven](https://maven.apache.org/)
- **Java Version:** OpenJDK 17
- **Testing:** [JUnit 5](https://junit.org/junit5/)
- **Validation:** [Spring Boot Validation](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#web.servlet.spring.mvc)

### CI/CD
- **Pipeline:** [GitHub Actions](https://github.com/features/actions)

## 📁 Project Structure

```text
├── .github/workflows/
│   └── deploy.yml          # CI/CD: Tests and builds both applications
├── openapi.yaml            # API Blueprint (The Single Source of Truth)
├── python-app/             # FastAPI implementation
│   ├── main.py             # Application logic (Imports from models.py)
│   ├── test_main.py        # Integration tests
│   ├── requirements.txt    # Python dependencies (FastAPI, Uvicorn, testing tools)
│   ├── models.py           # Generated Pydantic models (git-ignored)
│   └── Dockerfile          # Multi-stage build (Generates models.py in-container)
├── java-app/               # Spring Boot implementation
│   ├── pom.xml             # Maven configuration
│   ├── Dockerfile          # Multi-stage build (Builds JAR from existing source)
│   └── src/
│       ├── main/
│       │   └── java/com/petshelter/
│       │       ├── PetShelterApplication.java
│       │       ├── api/PetController.java
│       │       └── model/
│       │           ├── Pet.java
│       │           └── Sex.java
│       └── test/
│           └── java/com/petshelter/
│               └── PetShelterApplicationTests.java
└── .gitignore              # Prevents generated files, venv, and caches from being tracked
```

# ⚙️ Local Setup

## Choose Your Implementation

You can run either the Python FastAPI or Java Spring Boot implementation. Both use the same OpenAPI specification.

## Python FastAPI Setup

### 1. Environment Setup

```bash
cd python-app

python -m venv venv
source venv/bin/activate  # Or .\venv\Scripts\activate on Windows
pip install -r requirements.txt
pip install datamodel-code-generator ruff pytest httpx
```

### 2. Generate Models for Local Development

Since `models.py` is ignored by Git, generate it locally for IDE support:

```bash
python -m datamodel_code_generator --input ../openapi.yaml --output models.py
```

### 3. Run & Test Locally

```bash
uvicorn main:app --reload
pytest
```

View the interactive API docs at: [http://127.0.0.1:8000/docs](http://127.0.0.1:8000/docs)

## Java Spring Boot Setup

### 1. Prerequisites

- **Java 17** or higher
- **Maven 3.6+** (system Maven, no wrapper included)

### 2. Build & Run

```bash
cd java-app

# Using system Maven
mvn spring-boot:run
```

### 3. Run Tests

```bash
./mvnw test
```

The Java application runs on port 8080 by default. View the interactive API docs at: [http://127.0.0.1:8080/swagger-ui.html](http://127.0.0.1:8080/swagger-ui.html)

# 🐳 Docker & Deployment

Both applications have separate Dockerfiles for containerized deployment.

## Python FastAPI Docker

**Build Image**

```bash
cd python-app
docker build -t pet-shelter-python .
```

**Run Container**

```bash
docker run -p 8000:8000 pet-shelter-python
```

## Java Spring Boot Docker

**Build Image**

```bash
cd java-app
docker build -t pet-shelter-java .
```

**Run Container**

```bash
docker run -p 8080:8080 pet-shelter-java
```

# 🤖 CI/CD Workflow

The pipeline is fully automated via **GitHub Actions**:

0. **Model Generation:** Generates Pydantic models for Python from the OpenAPI specification.
1. **Python Testing:** Executes **Pytest** to verify the FastAPI API contracts.
2. **Python Linting:** Runs **Ruff** to ensure code quality and standard formatting.
3. **Java Build:** Compiles and packages the Spring Boot application.
4. **Containerization:** Builds Docker images for both applications and pushes them to the **GHCR**.

```bash
# Pull Python image
docker pull ghcr.io/arevaura/pet-shelter/python-pet-shelter:latest

# Pull Java image  
docker pull ghcr.io/arevaura/pet-shelter/java-pet-shelter:latest
```

---

Developed as a part of a training project demonstrating contract-first development with both Python FastAPI and Java Spring Boot implementations.