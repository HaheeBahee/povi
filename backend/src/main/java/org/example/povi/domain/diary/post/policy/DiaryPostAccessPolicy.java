package org.example.povi.domain.diary.post.policy;


import lombok.RequiredArgsConstructor;
import org.example.povi.domain.diary.enums.Visibility;
import org.example.povi.domain.diary.post.entity.DiaryPost;
import org.example.povi.domain.user.follow.service.FollowService;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DiaryPostAccessPolicy {

    private final FollowService followService;

    public boolean hasReadPermission(Long currentUserId, DiaryPost post) {
        Long authorId = post.getUser().getId();
        Visibility visibility = post.getVisibility();

        // 비로그인 → 접근 불가
        if (currentUserId == null) {
            return false;
        }

        // 본인 → 항상 허용
        if (currentUserId.equals(authorId)) {
            return true;
        }

        return isVisibleToUser(currentUserId, authorId, visibility);
    }

    private boolean isVisibleToUser(Long userId, Long authorId, Visibility visibility) {
        return switch (visibility) {
            case PUBLIC -> true;
            case FRIEND -> followService.isMutualFollow(userId, authorId);
            case PRIVATE -> false;
        };
    }
}