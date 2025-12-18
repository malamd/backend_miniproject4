#!/bin/bash
echo "Starting server..."
JAR_FILE=$(find /home/ec2-user/app/build/libs -name "*.jar")
if [ -z "$JAR_FILE" ]; then
  echo "Error: No JAR file found."
  exit 1
fi
echo "Found JAR file: $JAR_FILE"
nohup java -jar $JAR_FILE > /home/ec2-user/app/application.log 2>&1 &
echo "Server started."
