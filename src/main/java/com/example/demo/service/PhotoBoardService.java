package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Board;
import com.example.demo.model.PhotoBoard;
import com.example.demo.repository.PhotoBoardRepository;
import com.example.demo.util.PagingUtil;
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

    // get paging boards data
    public ResponseEntity<Map> getPagingPhoto(Integer p_num) {
        Map result = null;

        PagingUtil pu = new PagingUtil(p_num, 6, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
        List<PhotoBoard> list = photoBoardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());
        pu.setObjectCountTotal(findAllCount());
        pu.setCalcForPaging();

        System.out.println("p_num : "+p_num);
        System.out.println(pu.toString());

        if (list == null || list.size() == 0) {
            return null;
        }

        result = new HashMap<>();
        result.put("pagingData", pu);
        result.put("list", list);

        return ResponseEntity.ok(result);
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
