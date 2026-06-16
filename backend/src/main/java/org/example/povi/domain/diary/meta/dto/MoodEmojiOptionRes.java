package org.example.povi.domain.diary.meta.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "기분 이모지 옵션 응답 DTO")
public record MoodEmojiOptionRes(
        @Schema(description = "기분 이모지 코드", example = "HAPPY")
        String code,

        @Schema(description = "화면에 표시할 기분 이름", example = "행복")
        String label
) {}
