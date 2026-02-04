# Test: Java Model Generation Workflow

## Expected Generated Models (from openapi.yaml):

### Pet.java - Should contain:
- Integer id
- String name  
- LocalDate dateOfBirth (@JsonProperty("date_of_birth"))
- String species
- String breed
- String colour
- Integer age
- String sex (enum: male/female)
- Double weight
- Boolean neutered
- Boolean vaccinated
- String adoptionStatus (enum: available/pending/adopted)

### Sex.java - Should contain:
- Enum with MALE, FEMALE values

## Workflow Steps:
1. ✅ Added OpenAPI generator plugin to pom.xml
2. ✅ Added generated models to .gitignore
3. ✅ Deleted manual Pet.java and Sex.java
4. ⏳ Test generation (blocked by Java 8 vs Java 11+ requirement)
5. ⏳ Update PetController imports

## Current Status:
- Setup is ready for CI/CD
- Local testing blocked by Java version mismatch
- GitHub Actions workflow will work (uses Java 17)
