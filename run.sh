#!/bin/bash

set -e
trap 'docker compose down --volumes --rmi local --remove-orphans' EXIT
docker-compose up --build --abort-on-container-exit \
  --no-attach app-postgres \
  --no-attach prometheus \
  --no-attach grafana \
  --no-attach loki \
  --no-attach zipkin
# docker-compose logs app-server app-postgres
