package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.model.PhotoBoard;
import com.example.demo.service.PhotoBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PhotoBoardController {

    @Autowired
    private PhotoBoardService photoBoardService;

    // get paging board # 페이징 처리를 할 수 있도록 수정
    @GetMapping("/photo")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Map> getAllPhotos(@RequestParam(value = "p_num", required=false) Integer p_num) {
        if (p_num == null || p_num <= 0) p_num = 1;
        //System.out.println(p_num);
        return photoBoardService.getPagingPhoto(p_num);
    }
    //get all board
//    @GetMapping("/photos")
//    List<PhotoBoard> getAllPhotos() {
//
//        return photoBoardService.getAllPhoto();
//    }

    // create
    @PostMapping("/photo")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public PhotoBoard createBoard(@RequestBody PhotoBoard photo) {
        return photoBoardService.createPhoto(photo);
    }

    // get board
    @GetMapping("/photo/{idx}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<PhotoBoard> getPhotoByNo(
            @PathVariable Integer idx) {

        return photoBoardService.getPhoto(idx);
    }

    // delete board
    @DeleteMapping("/photo/{idx}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<Map<String, Boolean>> deletePhotoByNo(
            @PathVariable Integer idx) {

        return photoBoardService.deletePhoto(idx);
    }

    // search board
    @GetMapping("/photo/search")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public List<PhotoBoard> getCertainBoards(@RequestParam(value="type") String searchType,
                                        @RequestParam(value="keyword") String searchKeyword) {
        return photoBoardService.getCertainPhoto(searchType, searchKeyword);
    }


}
