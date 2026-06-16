package org.example.povi.domain.diary.post.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;
import org.example.povi.domain.diary.post.entity.DiaryPost;
import org.example.povi.domain.diary.post.entity.DiaryImage;
import org.example.povi.domain.diary.enums.MoodEmoji;
import org.example.povi.domain.diary.enums.Visibility;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "다이어리 게시글 상세 응답 DTO")
public record DiaryDetailRes(
        @Schema(description = "다이어리 게시글 ID", example = "2")
        Long postId,

        @Schema(description = "다이어리 제목", example = "오늘의 일기")
        String title,

        @Schema(description = "다이어리 본문", example = "오늘은 날씨가 맑고 기분이 좋았다.")
        String content,

        @Schema(description = "기분 이모지", example = "HAPPY")
        MoodEmoji moodEmoji,

        @Schema(description = "공개 범위", example = "PUBLIC")
        Visibility visibility,

        @Schema(description = "첨부 이미지 URL 목록", example = "[\"https://povi-bucket.s3.ap-northeast-2.amazonaws.com/diary/2026/06/sample.jpg\"]")
        List<String> imageUrls,

        @Schema(description = "작성 시각", example = "2026-06-16T13:25:30")
        LocalDateTime createdAt,

        @Schema(description = "현재 사용자의 좋아요 여부", example = "false")
        boolean liked,

        @Schema(description = "현재 게시글의 총 좋아요 수", example = "4")
        long likeCount,

        @Schema(description = "현재 게시글의 총 댓글 수", example = "2")
        long commentCount
) {
    public static DiaryDetailRes of(
            DiaryPost post, boolean liked, long likeCount, long commentCount
    ) {
        return new DiaryDetailRes(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getMoodEmoji(),
                post.getVisibility(),
                post.getImages().stream().map(DiaryImage::getImageUrl).toList(),
                post.getCreatedAt(),
                liked,
                likeCount,
                commentCount
        );
    }
}
