package org.example.povi.domain.diary.like.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "다이어리 좋아요 상태 응답 DTO")
public record DiaryPostLikeRes(
        @Schema(description = "현재 사용자의 좋아요 여부", example = "true")
        boolean liked,

        @Schema(description = "현재 게시글의 총 좋아요 수", example = "12")
        long likeCount
) {
    public static DiaryPostLikeRes of(boolean liked, long likeCount) {
        return new DiaryPostLikeRes(liked, likeCount);
    }
}
