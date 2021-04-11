package com.example.demo.controller;

import com.example.demo.model.Board;
import com.example.demo.model.PhotoBoard;
import com.example.demo.service.PhotoBoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PhotoBoardController {

    private PhotoBoardService photoBoardService;

//    public ResponseEntity<Map> getAllPhotoBoards(){
//        return photoBoardService.
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
