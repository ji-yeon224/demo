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
@Table(name = "PhotoComment")
@DynamicInsert
@DynamicUpdate
public class PhotoComment {

    //번호
    @Id
    @Column(name = "photocomment_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pcommentNo;

    //글번호
    @Column(name="photoboard_no")
    private int pboardNo;

    //내용
    @Column(name = "photocomment_content")
    private String content;

    //작성자
    @Column(name = "photocomment_writer")
    private String writer;

    //등록일
    @Column(name = "photocomment_insertTime", columnDefinition = "datetime default now()")
    private LocalDateTime insertTime;

    /*수정일*/
    @Column(name = "photocomment_updateTime")
    private LocalDateTime updateTime;
}
