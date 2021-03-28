#!/bin/bash

deactivate
source ./env/bin/activate
export FLASK_APP=main.py
python3 -m flask run
deactivate

