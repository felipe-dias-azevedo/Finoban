FROM python:3.8-slim-buster

WORKDIR /app

COPY requirements.txt requirements.txt

RUN pip3 install -r requirements.txt

COPY . .

# Passar no enviroment o FLASK_APP
# Ex: Docker run -e FLASK_APP=main.py --name s16_app2 -p 5000:5000 -d s16_image

CMD [ "python3", "-m" , "flask", "run", "--host=0.0.0.0"]
