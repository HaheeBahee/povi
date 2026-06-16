package org.example.povi.domain.diary.like.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.povi.domain.diary.like.dto.DiaryPostLikeRes;
import org.example.povi.domain.diary.like.service.DiaryPostLikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "03. 다이어리 좋아요 API ⭐")
@RestController
@RequiredArgsConstructor
@RequestMapping("/diary-posts/{postId}/likes")
public class DiaryPostLikeController {

    private final DiaryPostLikeService diaryPostLikeService;

    @PostMapping("/toggle")
    @Operation(summary = "좋아요 토글", description = "이미 눌렀다면 취소, 아니면 추가합니다.")
    public ResponseEntity<DiaryPostLikeRes> toggleLike(
            @Parameter(description = "게시글 ID", example = "1") @PathVariable Long postId,
            @AuthenticationPrincipal(expression = "id") Long userId
    ) {
        DiaryPostLikeRes response = diaryPostLikeService.toggle(postId, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    @Operation(summary = "좋아요 여부 조회")
    public ResponseEntity<DiaryPostLikeRes> isLiked(
            @Parameter(description = "게시글 ID", example = "1") @PathVariable Long postId,
            @AuthenticationPrincipal(expression = "id") Long userId
    ) {
        DiaryPostLikeRes response = diaryPostLikeService.me(postId, userId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/count")
    @Operation(summary = "좋아요 수 조회")
    @ApiResponse(responseCode = "200", description = "좋아요 수 조회 성공",
            content = @Content(schema = @Schema(type = "integer", format = "int64", example = "4")))
    public ResponseEntity<Long> countLikes(
            @Parameter(description = "게시글 ID", example = "1") @PathVariable Long postId
    ) {
        long count = diaryPostLikeService.count(postId);
        return ResponseEntity.ok(count);
    }
}
