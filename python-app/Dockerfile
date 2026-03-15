FROM python:3.12-slim AS generator
WORKDIR /app
COPY openapi.yaml .
RUN pip install --no-cache-dir datamodel-code-generator==0.25.0
RUN python -m datamodel_code_generator --input openapi.yaml --output models.py

FROM python:3.12-slim
WORKDIR /app

RUN apt-get update && apt-get install -y --no-install-recommends curl \
    && rm -rf /var/lib/apt/lists/*

RUN groupadd -r app && useradd -r -g app app

COPY python-app/requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY --from=generator /app/models.py .
COPY python-app/main.py .

USER app:app

EXPOSE 8000

HEALTHCHECK --interval=30s --timeout=3s --start-period=5s --retries=3 \
  CMD curl -f http://localhost:8000/health || exit 1

CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]