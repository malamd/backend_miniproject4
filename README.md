# 🧠 AIVLE WebProject - Backend

**걷다가 서재** 백엔드 저장소입니다.  
프론트엔드와 연동하여 도서 등록, 사용자 인증 등을 처리하는 백엔드 서버입니다.

---

## 🚀 프로젝트 개요

- **프로젝트명:** AIVLE WebProject
- **파트:** Backend (서버 API 개발)
- **기술 스택:** Java, Spring Boot (`build.gradle` 기반), h2 database
- **주요 기능:**  
  - 사용자 회원가입 & 로그인 처리  
  - 도서 등록, 조회 등의 API 제공  
  - 프론트엔드와 데이터 통신 (axios 기반 요청 처리)

---

## 📁 폴더 구조

backend/
└─ src/
   ├─ main/
   │  ├─ java/
   │  │  └─ com/example/demo/
   │  │     ├─ config/         # Spring Security, CORS, 기타 설정 클래스
   │  │     ├─ controller/     # REST API 엔드포인트 (User, Book 등)
   │  │     ├─ domain/         # 엔티티(Entity) 클래스 - DB 테이블 매핑
   │  │     ├─ dto/            # 요청/응답 DTO 클래스 (데이터 전달 객체)
   │  │     ├─ repository/     # JPA Repository 인터페이스 (DB 접근 계층)
   │  │     ├─ service/        # 비즈니스 로직 구현 클래스
   │  │     ├─ util/           # 공용 기능(암호화, 토큰, 유틸 함수 등)
   │  │     └─ DemoApplication # Spring Boot 메인 실행 클래스 (main 메서드)
   │  │
   │  └─ resources/
   │     ├─ application.properties  # Spring 기본 설정 파일
   │     └─ application.yaml        # YAML 형식 설정 파일 (properties 대체 가능)
   │
   └─ test/                         # 테스트 코드 파일

