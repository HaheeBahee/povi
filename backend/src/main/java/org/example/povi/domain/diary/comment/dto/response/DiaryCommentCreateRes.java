package org.example.povi.domain.diary.comment.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.example.povi.domain.diary.comment.entity.DiaryComment;
import org.example.povi.domain.diary.post.entity.DiaryPost;
import org.example.povi.domain.user.entity.User;

import java.time.LocalDateTime;

@Schema(description = "다이어리 댓글 작성 응답 DTO")
public record DiaryCommentCreateRes(
        @Schema(description = "생성된 댓글 ID", example = "1")
        Long commentId,

        @Schema(description = "댓글이 작성된 다이어리 게시글 ID", example = "2")
        Long postId,

        @Schema(description = "댓글 작성자 ID", example = "1")
        Long authorId,

        @Schema(description = "댓글 작성자 닉네임", example = "홍길동")
        String authorName,

        @Schema(description = "댓글 내용", example = "좋은 일기네요!")
        String content,

        @Schema(description = "댓글 작성 시각", example = "2026-06-16T13:27:56")
        LocalDateTime createdAt
) {
    public static DiaryCommentCreateRes from(DiaryComment comment) {
        DiaryPost post = comment.getPost();
        User author = comment.getAuthor();

        return new DiaryCommentCreateRes(
                comment.getId(),
                post.getId(),
                author.getId(),
                author.getNickname(),
                comment.getContent(),
                comment.getCreatedAt()
        );
    }
}
