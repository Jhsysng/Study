from fastapi import FastAPI
from routes.users import user_router
from routes.events import event_router
import uvicorn
from database.connection import Settings
from fastapi.middleware.cors import CORSMiddleware

origins = ["*"]

app = FastAPI()
settings = Settings()

app.include_router(user_router, prefix="/user")
app.include_router(event_router, prefix="/event")
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


@app.on_event("startup")
async def init_db():
    await settings.initialize_database()


if __name__ == "__main__":
    uvicorn.run("main:app", host="127.0.0.1", port=8010, reload=True)