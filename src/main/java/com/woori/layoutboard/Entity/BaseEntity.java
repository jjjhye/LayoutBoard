package com.woori.layoutboard.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(value = {EntityListeners.class})
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false)//수정불가능
    private LocalDateTime regDate;

    @LastModifiedDate
    private LocalDateTime modDate;
}
