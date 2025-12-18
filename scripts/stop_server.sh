#!/bin/bash
echo "Stopping server..."
PID=$(pgrep -f 'java -jar')
if [ -n "$PID" ]; then
  echo "Found running server with PID: $PID. Killing it."
  kill -15 $PID
  sleep 5
else
  echo "No running server found."
fi
