package org.example.povi.domain.diary.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.povi.domain.diary.enums.MoodEmoji;

@Schema(description = "기분 요약 응답 DTO")
public record MoodSummaryRes(
        @Schema(description = "최근 7일 감정 점수 평균", example = "8.5")
        double averageScore,

        @Schema(description = "최근 7일 대표 감정", example = "HAPPY")
        MoodEmoji representative
) { }
