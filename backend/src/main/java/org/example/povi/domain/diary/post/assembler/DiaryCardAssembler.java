package org.example.povi.domain.diary.post.assembler;

import org.example.povi.domain.diary.post.dto.response.DiaryPostCardRes;
import org.example.povi.domain.diary.post.entity.DiaryPost;
import org.example.povi.domain.diary.post.view.PostViewStats;

import java.util.List;
import java.util.Map;
import java.util.Set;

public final class DiaryCardAssembler {

    private DiaryCardAssembler() {
    }

    public static List<DiaryPostCardRes> toCards(
            List<DiaryPost> posts,
            Set<Long> likedSet,
            Map<Long, Long> likeCnt,
            Map<Long, Long> commentCnt
    ) {
        return posts.stream()
                .map(p -> DiaryPostCardRes.from(
                        p,
                        PostViewStats.of(likedSet, likeCnt, commentCnt, p.getId())
                ))
                .toList();
    }
}
