package org.example.povi.domain.diary.post.assembler;

import org.example.povi.domain.diary.enums.MoodEmoji;
import org.example.povi.domain.diary.post.dto.response.MoodSummaryRes;
import org.example.povi.domain.diary.post.dto.response.MyDiaryCardRes;
import org.example.povi.domain.diary.post.dto.response.MyDiaryListRes;
import org.example.povi.domain.diary.post.entity.DiaryPost;
import org.example.povi.domain.diary.post.view.PostViewStats;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class MyDiaryAssembler {
    private MyDiaryAssembler() {}

    public static MyDiaryListRes build(
            Page<DiaryPost> cardPage,
            List<DiaryPost> thisWeekPosts,
            Set<Long> likedSetForCards,
            Map<Long, Long> likeCntForCards,
            Map<Long, Long> cmtCntForCards
    ) {
        List<MyDiaryCardRes> cards = cardPage.getContent().stream()
                .map(p -> MyDiaryCardRes.from(
                        p,
                        PostViewStats.of(likedSetForCards, likeCntForCards, cmtCntForCards, p.getId())
                ))
                .toList();

        long weeklyCount = thisWeekPosts.size();
        double avgValence = thisWeekPosts.stream()
                .mapToInt(p -> p.getMoodEmoji().valence())
                .average().orElse(0.0);

        return new MyDiaryListRes(
                cardPage.getTotalElements(),
                weeklyCount,
                new MoodSummaryRes(avgValence, MoodEmoji.fromValence(avgValence)),
                cards
        );
    }
}
