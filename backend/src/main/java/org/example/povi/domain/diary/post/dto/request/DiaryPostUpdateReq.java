package org.example.povi.domain.diary.post.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.povi.domain.diary.enums.MoodEmoji;
import org.example.povi.domain.diary.enums.Visibility;

import java.util.List;

@Schema(description = "다이어리 게시글 수정 요청 DTO")
public record DiaryPostUpdateReq(
        @Schema(example = "수정된 제목")
        @Size(min = 1, max = 50)
        String title,
        @Schema(example = "수정된 내용입니다.")
        @Size(min = 1, max = 3000)
        String content,
        @Schema(example = "SAD")
        MoodEmoji moodEmoji,
        @Schema(example = "PRIVATE")
        Visibility visibility,
        @Schema(example = "[]")
        @Size(max = 3) List<@NotBlank @Size(max = 2048)
                String> imageUrls

) {
}
