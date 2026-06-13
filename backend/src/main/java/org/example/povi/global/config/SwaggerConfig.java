package org.example.povi.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("POVI - 감정 공유 다이어리 서비스 API")
                .description("""
                        감정 기록과 공유를 중심으로 한 다이어리 서비스의 백엔드 API입니다.

                        🔑 사용 방법
                        1. 회원가입
                        2. 로그인
                        3. Access Token을 Authorize에 입력

                        ⭐ 담당 영역
                        - 다이어리 CRUD
                        - 공개 범위 정책(PUBLIC / FRIEND / PRIVATE)
                        - 댓글 / 좋아요
                        - 이미지 업로드
                        - 주간 감정 통계
                        """)
                .version("1.0");

        SecurityScheme securityScheme = new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityRequirement securityRequirement = new SecurityRequirement()
                .addList("BearerAuth");

        // 태그명의 번호 prefix + springdoc tags-sorter(alpha) 조합으로 표시 순서를 고정한다
        List<Tag> tags = List.of(
                new Tag().name("01. 인증 API").description("회원가입, 로그인, 토큰 재발급, 로그아웃 — 먼저 여기서 토큰을 발급받으세요"),
                new Tag().name("02. 다이어리 API ⭐").description("일기 CRUD + 공개범위/팔로우 기반 접근 제어 + 주간 감정 통계 (담당 파트)"),
                new Tag().name("03. 다이어리 좋아요 API ⭐").description("일기 좋아요 토글/조회 (담당 파트)"),
                new Tag().name("04. 다이어리 댓글 API ⭐").description("일기 댓글 CRUD (담당 파트)"),
                new Tag().name("05. 다이어리 이미지 API ⭐").description("일기 이미지 업로드/삭제 (담당 파트)"),
                new Tag().name("06. 다이어리 메타 API").description("감정 이모지 옵션 조회"),
                new Tag().name("07. 커뮤니티 API").description("커뮤니티 글/댓글/좋아요/북마크"),
                new Tag().name("08. 미션 API").description("오늘의 미션 생성/조회/상태 변경"),
                new Tag().name("09. 명언 API").description("오늘의 명언 조회"),
                new Tag().name("10. 명언 필사 API").description("명언 필사 작성/조회/삭제"),
                new Tag().name("11. 사용자 API").description("마이페이지 조회, 프로필 수정"),
                new Tag().name("12. 이메일 인증 API").description("이메일 인증 요청/검증"),
                new Tag().name("13. 날씨 API").description("좌표 기반 현재 날씨 조회")
        );

        return new OpenAPI()
                .info(info)
                .tags(tags)
                .addSecurityItem(securityRequirement)
                .schemaRequirement("BearerAuth", securityScheme);
    }
}
