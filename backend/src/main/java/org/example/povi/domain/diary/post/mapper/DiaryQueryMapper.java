package org.example.povi.domain.diary.post.mapper;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public final class DiaryQueryMapper {

    private DiaryQueryMapper() {
    }

    public record LikeStats(Map<Long, Long> likeCounts, Set<Long> likedByUser) {
        public static LikeStats empty() {
            return new LikeStats(Map.of(), Set.of());
        }
    }

    public static Map<Long, Long> toCountMap(List<Object[]> rows) {
        Map<Long, Long> m = new HashMap<>();
        if (rows == null) return m;
        for (Object[] r : rows) {
            m.put((Long) r[0], (Long) r[1]);
        }
        return m;
    }

    // postId → 첫 번째 이미지 URL (없는 게시글은 맵에 없음)
    public static Map<Long, String> toFirstImageUrlMap(List<Object[]> rows) {
        Map<Long, String> m = new HashMap<>();
        if (rows == null) return m;
        for (Object[] r : rows) {
            m.putIfAbsent((Long) r[0], (String) r[1]);
        }
        return m;
    }

    // findLikeStatsByPostIds 결과를 (likeCounts, likedByUser) 로 파싱
    public static LikeStats toLikeStats(List<Object[]> rows) {
        Map<Long, Long> likeCounts = new HashMap<>();
        Set<Long> likedByUser = new HashSet<>();
        if (rows != null) {
            for (Object[] r : rows) {
                Long postId = (Long) r[0];
                Long count = (Long) r[1];
                Number liked = (Number) r[2];
                likeCounts.put(postId, count);
                if (liked != null && liked.longValue() > 0) {
                    likedByUser.add(postId);
                }
            }
        }
        return new LikeStats(likeCounts, likedByUser);
    }
}