# POVI
Java 17 · Spring Boot · MySQL · Redis

일상을 기록하고, 위로를 나누는 **감정 공유 다이어리 서비스**입니다.

> _POV + i → 나의 시점, 나의 일기_

---

## 📌 프로젝트를 만든 이유

현대인은 SNS를 통해 일상을 공유하지만, 진솔한 감정을 표현하기는 점점 어려워지고 있습니다.
POVI는 감정을 기록하고, 친구 또는 익명 커뮤니티에서 위로와 공감을 주고받을 수 있는 공간을 제공합니다.

---

## ✨ 핵심 기능

| 기능 | 설명 |
| --- | --- |
| 회원 인증 | JWT 기반 로그인 · 이메일 인증 · Google/Kakao OAuth2 |
| 다이어리 | 감정 이모지 선택, 공개 범위 설정, 이미지 첨부, 친구/전체 피드 |
| 커뮤니티 | 익명 게시글 · 댓글 · 좋아요 · 북마크 |
| 명언 & 필사 | 매일 명언 제공, 마음에 드는 문장 필사 및 보관 |
| 날씨 | 현재 위치 날씨 조회 (OpenWeather API) |
| 배포 | Docker, Nginx, AWS EC2 기반 배포 환경 구성 |

---

## 🛠 Tech Stack

| 구분 | 기술 |
| --- | --- |
| Backend | Java 17, Spring Boot, Spring Data JPA |
| Auth | Spring Security, JWT, OAuth2 (Google, Kakao) |
| Database | MySQL, Redis |
| Infra | Docker, Nginx, AWS EC2 |

---

## 🏗 시스템 구조

### Architecture

![Architecture](docs/images/architecture.png)

### ERD

![ERD](docs/images/erd.png)

---

## 🔍 기술적 고민 및 해결

### 1. 다이어리 공개 범위 접근 제어

다이어리는 `PRIVATE`, `FRIEND`, `PUBLIC` 세 가지 공개 범위를 가집니다.
서비스 로직 여러 곳에서 접근 권한을 판단해야 했기 때문에, 이를 `DiaryPostAccessPolicy`로 분리해 단일 책임을 유지했습니다.

* 본인 → 항상 허용
* FRIEND → 상호 팔로우(맞팔) 여부 확인
* PRIVATE → 본인 외 접근 불가

### 2. 피드 조회 시 N+1 문제 해결

친구 피드, 전체 피드에서 게시글 목록을 조회할 때 좋아요 수 · 댓글 수 · 좋아요 여부를 함께 보여줘야 했습니다.
게시글마다 개별 쿼리를 날리면 N+1 문제가 발생하므로, `IN` 절 배치 쿼리로 한 번에 집계해 쿼리 수를 3개로 고정했습니다.

* `countByPostIds` — 좋아요 수 일괄 집계
* `countByPostIds` — 댓글 수 일괄 집계
* `findPostIdsLikedByUser` — 내가 좋아요한 게시글 ID 일괄 조회

### 3. 맞팔 여부에 따른 피드 분기 처리

친구 피드와 전체 탐색 피드에서 맞팔 친구는 `FRIEND + PUBLIC`, 단방향 팔로우 대상은 `PUBLIC`만 노출해야 했습니다.
맞팔 ID 집합이 비어 있는 경우와 아닌 경우를 분기해 최적화된 쿼리를 선택적으로 실행했습니다.

### 4. 운영 환경 구성

Docker Compose와 Nginx를 이용해 운영 환경을 구성했습니다.

* Spring Boot, MySQL, Redis, Nginx를 Docker Compose로 구성
* 외부에는 Nginx 포트만 노출, DB와 Redis는 내부 네트워크로 격리
* 로컬 개발 환경은 `docker-compose.local.yml`로 분리

---

## 🚀 실행 방법

```bash
git clone https://github.com/HaheeBahee/povi.git
cd backend

# 환경변수 설정
cp src/main/resources/application-dev.yml.example src/main/resources/application-dev.yml
# application-dev.yml 파일을 열어 값을 채워주세요

# 로컬 DB · Redis 실행
docker compose -f docker-compose.local.yml up -d

# 앱 실행
./gradlew bootRun
```

---

## 📄 API 문서

### Local

http://localhost:8080/swagger-ui/index.html

### Deployment

http://13.124.129.7:81/swagger-ui/index.html

> 개인 AWS EC2 환경에서 운영 중입니다.
> 서버 상태에 따라 일시적으로 접속이 제한될 수 있습니다.

---

## 📂 프로젝트 구조

```text
backend/src/main/java/org/example/povi
 ├── auth
 │   ├── controller       # 로그인, 회원가입, 토큰 재발급
 │   ├── email            # 이메일 인증
 │   ├── token            # JWT 발급 · 검증 · 필터
 │   └── oauthinfo        # OAuth2 사용자 정보
 │
 ├── domain
 │   ├── diary            # 다이어리 (게시글 · 댓글 · 좋아요 · 이미지)
 │   ├── community        # 익명 커뮤니티 (게시글 · 댓글 · 좋아요 · 북마크)
 │   ├── user             # 사용자 · 팔로우 · 프로필
 │   ├── mission          # 오늘의 미션
 │   ├── quote            # 명언
 │   ├── transcription    # 필사
 │   └── weather          # 날씨
 │
 └── global
     ├── config           # Security, Swagger 설정
     ├── exception        # 예외 처리
     └── entity           # 공통 BaseEntity
```
