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

    @Id
    @Column(name="photoboard_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(name = "photoboard_title")
    private String title;

    @Column(name = "photoboard_writer")
    private String writer;

    @Column(name = "photoboard_insertTime")
    private LocalDateTime insertTime;

    @Column(name = "photoboard_views")
    private int viewCnt;

    @Column(name = "photoboard_content")
    private String content;

    @Column(name = "photoboard_updateTime")
    private LocalDateTime updateTime;

    @Column(name = "photoboard_fileUrl")
    private String fileUrl;



}
