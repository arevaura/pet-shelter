![Build Status](https://github.com/arevaura/pet-shelter/actions/workflows/deploy.yml/badge.svg)

# 🐾 Pet Shelter API

A modern, production-ready FastAPI service for managing pet shelter records. This project demonstrates a **Contract-First** development workflow, automated testing, and CI/CD integration.

## 🚀 Key Features

- **Design-First:** Built using an `openapi.yaml` specification.
- **Auto-Generated Models:** Python Pydantic models generated directly from the OpenAPI schema.
- **Fully Containerized:** Includes a `Dockerfile` for consistent deployment.
- **CI/CD Pipeline:** GitHub Actions automatically run tests and push the Docker image to **GitHub Container Registry (GHCR)** on every push to `main`.
- **Interactive Docs:** Built-in Swagger UI for testing endpoints.

## 🛠️ Tech Stack

- **Framework:** [FastAPI](https://fastapi.tiangolo.com/)
- **Data Validation:** [Pydantic](https://docs.pydantic.dev/)
- **Testing:** [Pytest](https://docs.pytest.org/)
- **Containerization:** [Docker](https://www.docker.com/)
- **CI/CD:** [GitHub Actions](https://github.com/features/actions)

## 📁 Project Structure

```text
├── .github/workflows/
│   └── deploy.yml      # CI/CD: Generates models, lints, and runs tests
├── openapi.yaml        # API Blueprint (The Single Source of Truth)
├── main.py             # Application logic (Imports from models.py)
├── test_main.py        # Integration tests
├── requirements.txt    # Production dependencies (FastAPI, Uvicorn)
├── Dockerfile          # Multi-stage build (Generates models.py in-container)
└── .gitignore          # Prevents models.py, venv, and caches from being tracked
```

# ⚙️ Local Setup

## 1. Clone & Environment

```bash
git clone [https://github.com/arevaura/pet-shelter.git](https://github.com/arevaura/pet-shelter.git)
cd pet-shelter

python -m venv venv
source venv/bin/activate  # Or .\venv\Scripts\activate on Windows
pip install -r requirements.txt
pip install datamodel-code-generator ruff pytest httpx
```

## 2. Generate Models for Local Development

Since `models.py` is ignored by Git, generate it locally for IDE support:

```bash
python -m datamodel_code_generator --input openapi.yaml --output models.py
```

## 3. Run & Test Locally

```bash
uvicorn main:app --reload
pytest
```

View the interactive API docs at: [http://127.0.0.1:8000/docs](http://127.0.0.1:8000/docs)

# 🐳 Docker & Deployment

The Dockerfile automatically handles model generation via a multi-stage build:

**Build Image**

```bash
docker build -t pet-shelter .
```

**Run Container**

```bash
docker run -p 8000:8000 pet-shelter
```

# 🤖 CI/CD Workflow

The pipeline is fully automated via **GitHub Actions**:

1. **Model Generation:** Dynamically creates `models.py` from `openapi.yaml`.
2. **Linting:** Runs **Ruff** to ensure code quality and standard formatting.
3. **Automated Testing:** Executes **Pytest** to verify the API contracts.
4. **Containerization:** Builds a Docker image and pushes it to the **GHCR**.
```bash
$ docker pull ghcr.io/arevaura/pet-shelter/pet-shelter:latest
```

---

Developed as a part of a FastAPI & DevOps training project.