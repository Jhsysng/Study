from pydantic import BaseModel, EmailStr
from typing import Optional, List
from models.events import Event
from beanie import Document


class User(Document):
    email: EmailStr
    password: str
    events: Optional[List[Event]]

    class Settings:
        name = "users"

    class Config:
        schema_example ={
            "example": {
                "email": "fastapi@packt.com",
                "username": "Strong",
                "events": [],
            }
        }


class TokenResponse(BaseModel):
    access_token: str
    token_type: str
