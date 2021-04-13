package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.PhotoComment;
import com.example.demo.repository.PhotoCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhotoCommentService {

    @Autowired
    private PhotoCommentRepository photoCommentRepository;

    // 글번호 id인 글의 댓글들 가져오기
    public List<PhotoComment> getPhotoCommentById(Integer pboard_no) {
        return photoCommentRepository.findAllByPboardNo(pboard_no);
    }

    // create comment
    public PhotoComment createPhotoComment(PhotoComment photoComment) {
        return photoCommentRepository.save(photoComment);
    }

    // update comment
    public ResponseEntity<PhotoComment> updatePhotoComment(Integer pcommentNo, Integer pboardNo, PhotoComment updatedComment) {
        PhotoComment photoComment = photoCommentRepository.findByPcommentNoAndPboardNo(pcommentNo, pboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Comment Data by idx : ["+pcommentNo+"]"));
        photoComment.setWriter(updatedComment.getWriter());
        photoComment.setContent(updatedComment.getContent());
        photoComment.setUpdateTime(LocalDateTime.now());

        PhotoComment endUpdatedPhotoComment = photoCommentRepository.save(photoComment);
        return ResponseEntity.ok(endUpdatedPhotoComment);
    }

    // delete comment 댓글번호를 통해
    public ResponseEntity<Map<String, Boolean>> deletePhotoComment(Integer pcommentNo, Integer pboardNo) {
        PhotoComment photoComment = photoCommentRepository.findByPcommentNoAndPboardNo(pcommentNo, pboardNo)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Comment Data by idx : ["+pcommentNo+"]"));

        photoCommentRepository.delete(photoComment);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Comment Data by id : ["+pcommentNo+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }


}
