package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Board;
import com.example.demo.model.PhotoBoard;
import com.example.demo.repository.PhotoBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PhotoBoardService {

    @Autowired
    private PhotoBoardRepository photoBoardRepository;

    public int findAllCount() {
        return (int) photoBoardRepository.count();
    }
    public List<PhotoBoard> getAllPhoto() {
        return photoBoardRepository.findAllByOrderByIdxDesc();
    }

    //create
    public PhotoBoard createPhoto(PhotoBoard photo) {
        return photoBoardRepository.save(photo);
    }

    // get photo one by id
    public ResponseEntity<PhotoBoard> getPhoto(Integer idx) {
        PhotoBoard photo = photoBoardRepository.findById(idx)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Data by idx : ["+idx+"]"));
        return ResponseEntity.ok(photo);
    }


    //update
    public ResponseEntity<PhotoBoard> updatePhoto(Integer idx, PhotoBoard updatedPhoto) {
        PhotoBoard photo = photoBoardRepository.findById(idx)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by idx : ["+idx+"]"));

        photo.setTitle(updatedPhoto.getTitle());
        photo.setWriter(updatedPhoto.getWriter());
        photo.setContent(updatedPhoto.getContent());
        photo.setUpdateTime(LocalDateTime.now());
        photo.setFileUrl(updatedPhoto.getFileUrl());

        PhotoBoard endUpdatedPhoto = photoBoardRepository.save(photo);
        return ResponseEntity.ok(endUpdatedPhoto);
    }

    // delete
    public ResponseEntity<Map<String, Boolean>> deletePhoto(
            Integer idx) {
        PhotoBoard photo = photoBoardRepository.findById(idx)
                .orElseThrow(() -> new ResourceNotFoundException("Not exist Photo Board Data by idx : ["+idx+"]"));

        photoBoardRepository.delete(photo);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Deleted Photo Board Data by id : ["+idx+"]", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    // search
    public List<PhotoBoard> getCertainPhoto(String searchType, String searchKeyword) {
        if(searchType.equals("title")){
            return photoBoardRepository.findAllByTitleIgnoreCaseContaining(searchKeyword);
        }
        else if(searchType.equals("content")){
            return photoBoardRepository.findAllByContentIgnoreCaseContaining(searchKeyword);
        }
        else if(searchType.equals("writer")){
            return photoBoardRepository.findAllByWriterIgnoreCaseContaining(searchKeyword);
        }
        else{
            return photoBoardRepository.findAllByTitleOrContentOrWriterIgnoreCaseContaining(searchKeyword, searchKeyword, searchKeyword);
        }

    }

}
