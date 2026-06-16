package org.example.povi.domain.diary.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.povi.domain.diary.enums.MoodEmoji;
import org.example.povi.domain.diary.enums.Visibility;
import org.example.povi.domain.diary.post.entity.DiaryPost;
import org.example.povi.domain.diary.post.mapper.DiaryPreviewMapper;
import org.example.povi.domain.diary.post.view.PostViewStats;

import java.time.LocalDate;

@Schema(description = "다이어리 게시글 카드 응답 DTO")
public record DiaryPostCardRes(
        @Schema(description = "다이어리 게시글 ID", example = "2")
        Long postId,

        @Schema(description = "작성자 ID", example = "1")
        Long authorId,

        @Schema(description = "작성자 닉네임", example = "홍길동")
        String authorName,

        @Schema(description = "다이어리 제목", example = "오늘의 일기")
        String title,

        @Schema(description = "목록에서 보여줄 다이어리 미리보기", example = "오늘은 날씨가 맑고 기분이 좋았다.")
        String preview,

        @Schema(description = "대표 이미지 URL", example = "https://povi-bucket.s3.ap-northeast-2.amazonaws.com/diary/2026/06/sample.jpg", nullable = true)
        String thumbnailUrl,

        @Schema(description = "기분 이모지", example = "HAPPY")
        MoodEmoji moodEmoji,

        @Schema(description = "공개 범위", example = "PUBLIC")
        Visibility visibility,

        @Schema(description = "작성 날짜", example = "2026-06-16")
        LocalDate createdDate,

        @Schema(description = "현재 사용자의 좋아요 여부", example = "false")
        boolean liked,

        @Schema(description = "현재 게시글의 총 좋아요 수", example = "4")
        long likeCount,

        @Schema(description = "현재 게시글의 총 댓글 수", example = "2")
        long commentCount

) {
    public static DiaryPostCardRes from(DiaryPost post, PostViewStats stats) {
        return from(post, stats, DiaryPreviewMapper.firstImageUrl(post));
    }

    public static DiaryPostCardRes from(DiaryPost post, PostViewStats stats, String thumbnailUrl) {
        return new DiaryPostCardRes(
                post.getId(),
                post.getUser().getId(),
                post.getUser().getNickname(),
                post.getTitle(),
                DiaryPreviewMapper.buildPreviewText(post.getContent(), 100),
                thumbnailUrl,
                post.getMoodEmoji(),
                post.getVisibility(),
                post.getCreatedAt().toLocalDate(),
                stats.likedByMe(),
                stats.likeCount(),
                stats.commentCount()
        );
    }
}
