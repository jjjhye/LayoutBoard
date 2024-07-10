package com.woori.layoutboard.Entity;
//개발자가 설계한 테이블을 가지고 작업

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "board")
public class BoardEntity extends BaseEntity {
    //기본1부터 증가
    //서버 새로 시작+50 새로 번호시작
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private  Integer id;//일련번호
    @Column(name = "subject", length = 100, nullable = false)
    private String subject;//제목
    @Column(name = "content", length = 200)
    private String content;//내용
    //length를 이용해서 길이의 제약, input태그에서 길이 제약(필수)
}
