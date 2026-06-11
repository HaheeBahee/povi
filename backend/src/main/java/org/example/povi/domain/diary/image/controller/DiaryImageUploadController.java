package org.example.povi.domain.diary.image.controller;

import lombok.RequiredArgsConstructor;
import org.example.povi.domain.diary.image.service.DiaryImageUploadService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/diary-images")
public class DiaryImageUploadController implements DiaryImageUploadControllerDocs {

    private final DiaryImageUploadService diaryImageUploadService;

    @PostMapping
    public ResponseEntity<List<String>> uploadDiaryImages(
            @RequestPart("images") List<MultipartFile> images
    ) {
        List<String> uploadedUrls = diaryImageUploadService.upload(images);
        return ResponseEntity.ok(uploadedUrls);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDiaryImage(
            @RequestParam("imageUrl") String imageUrl
    ) {
        diaryImageUploadService.deleteByUrl(imageUrl);
        return ResponseEntity.noContent().build();
    }
}
