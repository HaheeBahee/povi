package org.example.povi.domain.diary.post.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "내 다이어리 게시글 목록 응답 DTO")
public record MyDiaryListRes(
        @Schema(description = "내가 작성한 전체 다이어리 수", example = "12")
        long totalCount,

        @Schema(description = "최근 7일 동안 작성한 다이어리 수", example = "3")
        long thisWeekCount,

        @Schema(description = "최근 7일 감정 요약")
        MoodSummaryRes moodSummary,

        @Schema(description = "내 다이어리 카드 목록")
        List<MyDiaryCardRes> myDiaries

) {
    public MyDiaryListRes {
        myDiaries = (myDiaries == null) ? List.of() : List.copyOf(myDiaries);
    }
}
