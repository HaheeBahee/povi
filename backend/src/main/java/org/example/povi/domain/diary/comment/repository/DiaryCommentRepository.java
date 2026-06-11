package org.example.povi.domain.diary.comment.repository;

import org.example.povi.domain.diary.comment.entity.DiaryComment;
import org.example.povi.domain.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DiaryCommentRepository extends JpaRepository<DiaryComment, Long> {
    void deleteAllByAuthor(User user);

    Optional<DiaryComment> findByIdAndPostId(Long commentId, Long postId);

    Page<DiaryComment> findByPostId(Long postId, Pageable pageable);

    // 여러 게시글 댓글 수 한 번에
    @Query("select c.post.id, count(c) from DiaryComment c where c.post.id in :postIds group by c.post.id")
    List<Object[]> countByPostIds(@Param("postIds") List<Long> postIds);

    // 특정 게시글의 댓글 수 (단건 상세 조회용)
    long countByPostId(Long postId);
}


