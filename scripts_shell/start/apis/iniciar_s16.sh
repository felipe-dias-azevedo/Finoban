#!/bin/bash

cd ~/Finoban/apis-externas/s16-bank-api/
export FLASK_APP=main.py
python3 -m flask run --host=0.0.0.0