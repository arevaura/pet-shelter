# Pet Shelter API

Contract-first polyglot service for managing pet shelter records.
Both implementations (Python and Java) generate their models from a single `openapi.yaml`.

## Architecture

```
openapi.yaml  ← Single source of truth
  ├─► python-app/  (FastAPI + Pydantic, models generated at build time)
  └─► java-app/    (Spring Boot 3.2 + openapi-generator-maven-plugin)
```

**No hand-written model classes.** Both languages generate models from the spec.

## Project Structure

```
openapi.yaml                    # API specification (single source of truth)
python-app/
  ├── main.py                   # FastAPI application
  ├── test_main.py              # Tests
  ├── requirements.txt          # Runtime dependencies (pinned)
  ├── requirements-dev.txt      # Dev/test dependencies (pinned)
  ├── models.py                 # Generated (git-ignored)
  └── Dockerfile
java-app/
  ├── pom.xml                   # Maven config + openapi-generator plugin
  ├── src/main/java/com/petshelter/
  │   ├── PetShelterApplication.java
  │   └── api/
  │       ├── PetController.java
  │       └── HealthController.java
  ├── src/test/java/com/petshelter/
  │   └── PetShelterApplicationTests.java
  ├── target/generated-sources/  # Generated models (git-ignored)
  └── Dockerfile
.github/workflows/deploy.yml    # CI/CD pipeline
```

## Tech Stack

| | Python | Java |
|---|---|---|
| **Framework** | FastAPI 0.109 | Spring Boot 3.2 |
| **Language** | Python 3.12 | Java 17 |
| **Model Gen** | datamodel-code-generator | openapi-generator-maven-plugin |
| **Validation** | Pydantic | Jakarta Bean Validation |
| **Testing** | Pytest | JUnit 5 + MockMvc |
| **API Docs** | Built-in `/docs` | springdoc `/swagger-ui.html` |

## Local Development

### Python

```bash
cd python-app
python -m venv venv && source venv/bin/activate
pip install -r requirements-dev.txt

# Generate models (git-ignored, must generate locally)
python -m datamodel_code_generator --input ../openapi.yaml --output models.py

uvicorn main:app --reload        # http://127.0.0.1:8000/docs
pytest                           # Run tests
```

### Java

```bash
cd java-app

# Build (generates models from openapi.yaml automatically via Maven plugin)
mvn clean package

mvn spring-boot:run              # http://127.0.0.1:8080/swagger-ui.html
mvn test                         # Run tests
```

## Docker

Both Dockerfiles use multi-stage builds, non-root users, and health checks.

```bash
# Python (build context is repo root)
docker build -f python-app/Dockerfile -t pet-shelter-python .
docker run -p 8000:8000 pet-shelter-python

# Java (build context is repo root)
docker build -f java-app/Dockerfile -t pet-shelter-java .
docker run -p 8080:8080 pet-shelter-java
```

## CI/CD

GitHub Actions pipeline (`.github/workflows/deploy.yml`):

1. **Python** — generate models → lint (ruff) → test (pytest) → Docker smoke test → push to GHCR
2. **Java** — build + test (`mvn verify`, models generated automatically) → Docker smoke test → push to GHCR

Images are pushed to GHCR on `main` branch only. Pull requests run tests without pushing.