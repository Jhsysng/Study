from pydantic import BaseModel
from typing import Optional, List
from sqlmodel import JSON, SQLModel, Field, Column
from beanie import Document


class Event(Document):
    creator: Optional[str]
    title: str
    image: str
    description: str
    tags: List[str]
    location: str

    class Config:
        arbitrary_types_allowed = True
        schema_extra = {
            "example": {
                "title": "FastAPI",
                "image": "https://linktomyimage.com/image.png",
                "description": "ll",
                "tags": ["python", "fastapi", "launch"],
                "location": "noe"
            }
        }

    class Settings:
        name = "events"


class EventUpdate(BaseModel):
    title: Optional[str]
    image: Optional[str]
    description: Optional[str]
    tags: Optional[List[str]]
    location: Optional[str]

    class Config:
        schema_extra = {
            "example": {"title": "FastAPI",
                        "image": "https://linktomyimage.com/image.png",
                        "description": "We will be ds..",
                        "tags": ["python","fastapi", "book","launch"],
                        "location":"GM"
                        }
        }