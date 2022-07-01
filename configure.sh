#!/usr/bin/env bash

set -e

{
  echo "[INFO]: Creating postgres db asteroid-inspector..."
  createdb asteroid-inspector && echo "[INFO]: Successfully created db"
} || {
  echo "[INFO]: Could not create db. Continuing..."
}

echo "[INPUT]: Provide postgres username:"
read -r postgres_username
sed -i.tmp "s/spring.datasource.username.*/spring.datasource.username=${postgres_username}/" src/main/resources/application.example.properties

echo "[INPUT]: Provide postgres password:"
read -r postgres_password
sed -i.tmp "s/spring.datasource.password.*/spring.datasource.password=${postgres_password}/" src/main/resources/application.example.properties

echo "[INPUT]: Provide the NASA API key:"
read -r nasa_api_key
sed -i.tmp "s/nasa.api_key.*/nasa.api_key=${nasa_api_key}/" src/main/resources/application.example.properties
echo "[INFO]: Configure completed"

cp src/main/resources/application.example.properties src/main/resources/application.properties
rm src/main/resources/application.example.properties.tmp
