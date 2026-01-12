# Stage 1: Generator
FROM python:3.12-slim AS generator
WORKDIR /app
COPY openapi.yaml .
RUN pip install datamodel-code-generator
# Generate models.py dynamically
RUN datamodel-code-generator --input openapi.yaml --output models.py

# Stage 2: Final Production Image
FROM python:3.12-slim
WORKDIR /app
# Copy the generated model from the first stage
COPY --from=generator /app/models.py .
COPY . .
RUN pip install fastapi uvicorn
EXPOSE 8000
# Run the app
CMD ["uvicorn", "main:app", "--host", "0.0.0.0", "--port", "8000"]