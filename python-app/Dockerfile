# Stage 1: Generator
FROM python:3.12-slim AS generator
WORKDIR /app
COPY openapi.yaml .
RUN pip install datamodel-code-generator
# Generate models.py dynamically:
RUN mkdir -p python-app && python -m datamodel_code_generator --input openapi.yaml --output python-app/models.py

# Stage 2: Final Production Image
FROM python:3.12-slim
WORKDIR /app
# 1. Copy the generated model from the first stage:
COPY --from=generator /app/python-app/models.py .
# 2. Copy dependencies and install:
COPY python-app/requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
# 3. CRITICAL: Copy the application code (main.py):
COPY python-app/ .
# Run the app:
EXPOSE 8000
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]