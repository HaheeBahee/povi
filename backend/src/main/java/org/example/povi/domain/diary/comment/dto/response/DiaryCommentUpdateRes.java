package org.example.povi.domain.diary.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.povi.domain.diary.comment.entity.DiaryComment;

import java.time.LocalDateTime;

@Schema(description = "다이어리 댓글 수정 응답 DTO")
public record DiaryCommentUpdateRes(
        @Schema(description = "수정된 댓글 ID", example = "1")
        Long commentId,

        @Schema(description = "댓글이 작성된 다이어리 게시글 ID", example = "2")
        Long postId,

        @Schema(description = "댓글 작성자 ID", example = "1")
        Long authorId,

        @Schema(description = "댓글 작성자 닉네임", example = "홍길동")
        String authorName,

        @Schema(description = "수정된 댓글 내용", example = "수정된 댓글입니다.")
        String content,

        @Schema(description = "댓글 수정 시각", example = "2026-06-16T13:30:10")
        LocalDateTime updatedAt
) {
    public static DiaryCommentUpdateRes from(DiaryComment entity) {
        return new DiaryCommentUpdateRes(
                entity.getId(),
                entity.getPost().getId(),
                entity.getAuthor().getId(),
                entity.getAuthor().getNickname(),
                entity.getContent(),
                entity.getUpdatedAt()
        );
    }
}
