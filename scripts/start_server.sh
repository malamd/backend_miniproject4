#!/bin/bash

APP_PATH="/home/ubuntu/app/build/libs"
NOW=$(date +%Y-%m-%d" "%H:%M:%S)

echo "[$NOW] >>> 서버 배포 시작"

# 1. 기존 실행 중인 Java 프로세스 종료 (있을 경우만)
CURRENT_PID=$(pgrep -f "demo-.*.jar")
if [ -z "$CURRENT_PID" ]; then
    echo "[$NOW] > 현재 실행 중인 애플리케이션이 없습니다."
else
    echo "[$NOW] > 실행 중인 애플리케이션 종료 (PID: $CURRENT_PID)"
    kill -15 $CURRENT_PID
    sleep 5
fi

# 2. JAR 파일 찾기 (plain 제외)
JAR_NAME=$(ls $APP_PATH/demo-*.jar | grep -v plain | tail -n 1)

if [ -z "$JAR_NAME" ]; then
    echo "[$NOW] > 에러: 실행 가능한 JAR 파일을 찾을 수 없습니다."
    exit 1
fi

echo "[$NOW] > 찾은 JAR 파일: $JAR_NAME"

# 3. 실행 권한 부여 (혹시 모를 상황 대비)
chmod +x $JAR_NAME

# 4. 백그라운드 실행
echo "[$NOW] > 애플리케이션 실행"
nohup java -jar $JAR_NAME > /home/ubuntu/app/nohup.out 2>&1 &

echo "[$NOW] >>> 서버 배포 완료"