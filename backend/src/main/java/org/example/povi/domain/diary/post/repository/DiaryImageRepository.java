package org.example.povi.domain.diary.post.repository;

import org.example.povi.domain.diary.post.entity.DiaryImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DiaryImageRepository extends JpaRepository<DiaryImage, Long> {

    // 여러 게시글의 이미지 URL 한 번에 조회
    @Query("select i.post.id, i.imageUrl from DiaryImage i where i.post.id in :postIds")
    List<Object[]> findImageUrlsByPostIds(@Param("postIds") List<Long> postIds);
}
