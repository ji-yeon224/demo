package com.example.demo.repository;

import com.example.demo.model.PhotoBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoBoardRepository extends JpaRepository<PhotoBoard, Integer> {
    public List<PhotoBoard> findAllByOrderByIdxDesc();
    public List<PhotoBoard> findAllByTitleIgnoreCaseContaining(String searchKeyword);
    public List<PhotoBoard> findAllByContentIgnoreCaseContaining(String searchKeyword);
    public List<PhotoBoard> findAllByWriterIgnoreCaseContaining(String searchKeyword);
    public List<PhotoBoard> findAllByTitleOrContentOrWriterIgnoreCaseContaining(String searchKeyword1, String searchKeyword2, String searchKeyword3);




}
