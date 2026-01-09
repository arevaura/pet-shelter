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
├── .github/workflows/  # CI/CD pipeline definitions
├── openapi.yaml        # API Blueprint (Source of Truth)
├── models.py           # Auto-generated Pydantic models
├── main.py             # Application logic
├── test_main.py        # Integration tests
├── Dockerfile           # Container configuration
└── .gitignore          # Prevents venv/pycache from being uploaded
```

# ⚙️ Local Setup

## 1. Clone & Environment

```bash
git clone [https://github.com/arevaura/pet-shelter.git](https://github.com/arevaura/pet-shelter.git)
cd pet-shelter
python -m venv venv
# Windows:
.\venv\Scripts\activate
# Mac/Linux:
source venv/bin/activate
```

## 2. Install Dependencies

```bash
pip install fastapi uvicorn pytest httpx datamodel-code-generator
```

## 3. Generate Models (Optional)

If you update the `openapi.yaml`, refresh the models using:

```bash
python -m datamodel_code_generator --input openapi.yaml --output models.py
```

## 4. Run Locally

```bash
uvicorn main:app --reload
```

View the interactive API docs at: [http://127.0.0.1:8000/docs](http://127.0.0.1:8000/docs)

# 🧪 Testing

Run the test suite to ensure the Pet entity logic is correct:

```bash
pytest
```

# 🐳 Docker & Deployment

**Build Image**

```bash
docker build -t pet-shelter .
```

**Run Container**

```bash
docker run -p 8000:8000 pet-shelter
```

# 🤖 CI/CD Workflow

This repo is configured with **GitHub Actions**. Every time you push to `main`:

1. The code is checked out.
2. Dependencies are installed and **Pytest** is executed.
3. If tests pass, a Docker image is built and pushed to: `ghcr.io/arevaura/pet-shelter:latest`

---

Developed as a part of a FastAPI & DevOps training project.