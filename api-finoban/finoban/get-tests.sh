#!/bin/bash

mvn clean test

cp threat.py target/allure-results/
cd target/allure-results/
python3 threat.py
