package com.example.demo.repository;

import com.example.demo.model.PhotoComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoCommentRepository extends JpaRepository<PhotoComment, Integer> {

    public List<PhotoComment> findAllByPboardNo(Integer pboardNo);
    public abstract java.util.Optional<PhotoComment> findByPcommentNoAndPboardNo(Integer pcommentNo, Integer pboardNo);

}
