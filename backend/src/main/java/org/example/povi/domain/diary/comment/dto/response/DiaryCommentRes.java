package org.example.povi.domain.diary.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.povi.domain.diary.comment.entity.DiaryComment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Schema(description = "다이어리 댓글 응답 DTO")
public record DiaryCommentRes(
        @Schema(description = "댓글 ID", example = "1")
        Long commentId,

        @Schema(description = "댓글 작성자 ID", example = "1")
        Long authorId,

        @Schema(description = "댓글 작성자 닉네임", example = "홍길동")
        String authorName,

        @Schema(description = "댓글 내용", example = "수정된 댓글입니다.")
        String content,

        @Schema(description = "댓글 작성 시각", example = "2026-06-16T13:27:56")
        LocalDateTime createdAt,

        @Schema(description = "현재 사용자가 작성한 댓글인지 여부", example = "true")
        boolean isMine
) {

    /**
     * Entity → DTO (단건 변환)
     */
    public static DiaryCommentRes from(DiaryComment comment, Long currentUserId) {
        Long authorId = comment.getAuthor().getId();
        return new DiaryCommentRes(
                comment.getId(),
                authorId,
                comment.getAuthor().getNickname(),
                comment.getContent(),
                comment.getCreatedAt(),
                Objects.equals(authorId, currentUserId)  // null-safe 비교
        );
    }

    /**
     * Entity List → DTO List (목록 변환)
     */
    public static List<DiaryCommentRes> fromList(List<DiaryComment> comments, Long currentUserId) {
        return comments.stream()
                .map(c -> from(c, currentUserId))
                .toList();
    }
}
