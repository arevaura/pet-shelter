from fastapi.testclient import TestClient
from main import app

client = TestClient(app)

def test_create_pet():
    pet_data = {
        "id": 1,
        "name": "Buddy",
        "species": "Dog",
        "breed": "Golden Retriever",
        "date_of_birth": "2020-01-01",
        "colour": "Gold",
        "age": 4,
        "sex": "male",
        "weight": 30.5,
        "neutered": True,
        "vaccinated": True,
        "adoption_status": "available"
    }
    response = client.post("/pets", json=pet_data)
    assert response.status_code == 201
    assert response.json()["name"] == "Buddy"