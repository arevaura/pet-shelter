from fastapi import FastAPI
from pydantic import BaseModel
from typing import List

from models import Pet # This imports the class generated from the YAML

app = FastAPI()

# In-memory database
pets_db = []

@app.get("/pets", response_model=List[Pet])
def get_pets():
    return pets_db

@app.post("/pets", status_code=201)
def create_pet(pet: Pet):
    pets_db.append(pet)
    return pet