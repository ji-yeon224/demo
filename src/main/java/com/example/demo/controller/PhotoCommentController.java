package com.example.demo.controller;

import com.example.demo.model.PhotoComment;
import com.example.demo.service.PhotoCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class PhotoCommentController {

    @Autowired
    private PhotoCommentService photoCommentService;


    // 글 번호에 따라 해당 글의 댓글 가져오기
    @GetMapping("/photo_cmt/{pboard_no}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<PhotoComment> getAllPhotoComments(@PathVariable Integer pboard_no) {

        return photoCommentService.getPhotoCommentById(pboard_no);
    }

    // 댓글 작성
    @PostMapping("/photo_cmt")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public PhotoComment createPhotoComment(@RequestBody PhotoComment photoComment) {
        return photoCommentService.createPhotoComment(photoComment);
    }

    // update comment
    @PutMapping("/photo_cmt/{pboard_no}/{pcomment_no}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<PhotoComment> updatePhotoCommentByNo(
            @PathVariable Integer pcomment_no, @PathVariable Integer pboard_no, @RequestBody PhotoComment photoComment){

        return photoCommentService.updatePhotoComment(pcomment_no, pboard_no, photoComment);
    }

    // delete board by 댓글 id
    @DeleteMapping("/photo_cmt/{pboard_no}/{pcomment_no}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deletePhotoCommentByNo(
            @PathVariable Integer pcomment_no, @PathVariable Integer pboard_no) {

        return photoCommentService.deletePhotoComment(pcomment_no, pboard_no);
    }


}
