FROM python:3.10.5-alpine

#set work directory
WORKDIR /usr/src/app

#set environment variables
ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1

RUN apk update
RUN apk add postgresql-dev gcc python3-dev musl-dev zlib-dev jpeg-dev

COPY . /usr/src/app

#install dependencies
RUN pip install --upgrade pip
RUN apk add libffi-dev
RUN pip install -r requirements.txt


#RUN python manage.py collectstatic
