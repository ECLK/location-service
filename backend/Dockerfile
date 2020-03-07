FROM python:3.7.4-buster

# set environment variables
ENV PYTHONDONTWRITEBYTECODE 1
ENV PYTHONUNBUFFERED 1

RUN mkdir -p /app/src
WORKDIR /app
COPY . /app

RUN pip install -r requirements.txt
RUN pip3 install django-rest-swagger
RUN pip3 install django-filter
RUN pip3 install djangorestframework_simplejwt
RUN pip3 install djoser

EXPOSE 8000

CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]
