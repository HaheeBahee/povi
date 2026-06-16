package org.example.povi.domain.diary.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.povi.domain.diary.post.entity.DiaryPost;
import org.example.povi.domain.diary.post.entity.DiaryImage;
import org.example.povi.domain.diary.enums.MoodEmoji;
import org.example.povi.domain.diary.enums.Visibility;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "다이어리 게시글 수정 응답 DTO")
public record DiaryPostUpdateRes(
        @Schema(description = "수정된 다이어리 게시글 ID", example = "2")
        Long postId,

        @Schema(description = "수정된 다이어리 제목", example = "수정된 제목")
        String title,

        @Schema(description = "수정된 다이어리 본문", example = "수정된 내용입니다.")
        String content,

        @Schema(description = "수정된 기분 이모지", example = "SAD")
        MoodEmoji moodEmoji,

        @Schema(description = "수정된 공개 범위", example = "PRIVATE")
        Visibility visibility,

        @Schema(description = "첨부 이미지 URL 목록", example = "[]")
        List<String> imageUrls,

        @Schema(description = "작성 시각", example = "2026-06-16T13:25:30")
        LocalDateTime createdAt,

        @Schema(description = "마지막 수정 시각", example = "2026-06-16T13:40:12")
        LocalDateTime updatedAt
) {
    public static DiaryPostUpdateRes from(DiaryPost post) {
        return new DiaryPostUpdateRes(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getMoodEmoji(),
                post.getVisibility(),
                post.getImages().stream()
                        .map(DiaryImage::getImageUrl)
                        .toList(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
