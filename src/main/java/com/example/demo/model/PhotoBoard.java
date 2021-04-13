package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name="PhotoBoard")
@DynamicInsert
@DynamicUpdate
public class PhotoBoard {

    //번호
    @Id
    @Column(name="photoboard_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    //제목
    @Column(name = "photoboard_title")
    private String title;

    //작성자
    @Column(name = "photoboard_writer")
    private String writer;

    //등록일
    @Column(name = "photoboard_insertTime", columnDefinition = "datetime default now()")
    private LocalDateTime insertTime;

    //조회수
    @Column(name = "photoboard_views", columnDefinition = "integer default 0")
    private int viewCnt;

    //내용
    @Column(name = "photoboard_content")
    private String content;

    //수정일
    @Column(name = "photoboard_updateTime")
    private LocalDateTime updateTime;

    //파일
    @Column(name = "photoboard_fileUrl")
    private String fileUrl;



}
