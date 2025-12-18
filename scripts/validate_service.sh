#!/bin/bash
echo "Validating service..."
for i in {1..12}; do
  if lsof -i:8080 -sTCP:LISTEN -t >/dev/null ; then
    echo "Service is up and running on port 8080."
    exit 0
  fi
  echo "Waiting for service to start... ($i/12)"
  sleep 5
done
echo "Service did not start on port 8080."
exit 1
