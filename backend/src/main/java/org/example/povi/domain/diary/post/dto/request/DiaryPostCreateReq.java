package org.example.povi.domain.diary.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.example.povi.domain.diary.enums.MoodEmoji;
import org.example.povi.domain.diary.enums.Visibility;

import java.util.List;


@Schema(description = "다이어리 게시글 작성 요청 DTO")
public record DiaryPostCreateReq(
        @Schema(example = "오늘의 일기")
        @NotBlank @Size(min = 1, max = 50)
        String title,
        @Schema(example = "오늘은 날씨가 맑고 기분이 좋았다.")
        @NotBlank @Size(min = 1, max = 3000)
        String content,
        @Schema(example = "HAPPY")
        @NotNull
        MoodEmoji moodEmoji,
        @Schema(example = "PUBLIC")
        @NotNull
        Visibility visibility,
        @Schema(example = "[]")
        @Size(max = 3)
        List<String> imageUrls
) {}