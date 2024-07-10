package com.woori.layoutboard.DTO;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BoardDTO {
    private  Integer id;//일련번호
    private String subject;//제목
    private String content;//내용
    //extend BaseEntity
    private LocalDateTime modDate;
}
